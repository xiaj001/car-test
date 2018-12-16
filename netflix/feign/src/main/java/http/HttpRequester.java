package http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 银企直联http/https通信公共类
 * User: suxx
 * Date: 13-11-7
 * Time: 下午2:53
 * To change this template use File | Settings | File Templates.
 */
public class HttpRequester {

   // private static final Logger logger = LoggerFactory.getLogger(HttpRequester.class);

    /**
     * 连接池
     */
    private PoolingHttpClientConnectionManager connectionManager;

    /**
     * 请求配置信息
     */
    private RequestConfig requestConfig;

    /**
     * httpCfg
     */
    private HttpCfg httpCfg;

    /**
     * 构造函数
     */
    public HttpRequester(HttpCfg httpCfg) {
        try {
            this.httpCfg = httpCfg;
            connectionManager = getConnManager(httpCfg);
            requestConfig = RequestConfig.custom()
                    .setSocketTimeout(httpCfg.getReadTimeOut())
                    .setConnectionRequestTimeout(httpCfg.getReadTimeOut())
                    .setConnectTimeout(httpCfg.getConnTimeOut())
                    .build();
        } catch (Exception e) {
            //logger.error("##########初始化通信类错误" + e);
        }
    }

    private HttpClient getConnection() {
        PoolingHttpClientConnectionManager connManager = this.connectionManager;
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setConnectionManager(connManager)
                .setDefaultRequestConfig(requestConfig).build();
        return httpClient;
    }

    /**
     * 获得连接管理
     *
     * @return
     * @throws Exception
     */
    private static PoolingHttpClientConnectionManager getConnManager(HttpCfg httpCfg) throws Exception {
        System.setProperty("jsse.enableSNIExtension", "false");
        RegistryBuilder<ConnectionSocketFactory> registryBuilder = RegistryBuilder.create();

        if (HttpCfg.HTTPS.equals(httpCfg.getProtocol())) {
            ConnectionSocketFactory connectionSocketFactory = getSSLFactory(httpCfg);
            registryBuilder.register("https", connectionSocketFactory);
        } else {
            ConnectionSocketFactory plainSF = new PlainConnectionSocketFactory();
            registryBuilder.register("http", plainSF);
        }

        try {
            Registry<ConnectionSocketFactory> registry = registryBuilder.build();
            return new PoolingHttpClientConnectionManager(registry);
        } catch (Exception e) {
            //logger.error("创建的连接失败", e);
            throw e;
        }
    }

    /**
     * 创建ssl工厂
     *
     * @param httpCfg
     * @return
     * @throws Exception
     */
    private static ConnectionSocketFactory getSSLFactory(HttpCfg httpCfg) throws Exception {

        //构建信任库
        TrustManager[] tm = {new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        }};

        //构建SSL上下文
        SSLContext sslContext = SSLContext.getInstance("SSL");

        //区别单向和双向认证
        if (!httpCfg.isHttps2way()) {
            sslContext.init(null, tm, null);//单向认证
        } else {
            //加载私钥
            KeyStore keyStore = KeyStore.getInstance(httpCfg.getKeyStoreType());
            keyStore.load(new FileInputStream(httpCfg.getPfxPath()), httpCfg.getPfxPass().toCharArray());

            //构建私钥算法
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(keyStore, httpCfg.getPfxPass().toCharArray());

            sslContext.init(kmf.getKeyManagers(), tm, null);//双向认证
        }

       // ConnectionSocketFactory sslSF = new SSLConnectionSocketFactory(sslContext);
        SSLConnectionSocketFactory sslSF = new SSLConnectionSocketFactory(sslContext, new String[] { "TLSv1.2"},
                null, new HostnameVerifier(){
            @Override
            public boolean verify(String hostname, SSLSession session) {
                hostname = "*.ke.com";
                return SSLConnectionSocketFactory.getDefaultHostnameVerifier().verify(hostname, session);
            }

        });
        return sslSF;
    }

    /**
     * 标准post表单请求
     *
     * @param httpRequest
     * @return
     * @throws IOException
     */
    public HttpRep sendPostForm(HttpReq httpRequest) throws UnknownHostException, HttpHostConnectException,
            ConnectTimeoutException, SocketTimeoutException, Exception {
        HttpPost httpPost = new HttpPost(httpCfg.getUrl());
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : httpRequest.getParamMap().entrySet()) {
            params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        HttpEntity formEntity = new UrlEncodedFormEntity(params, httpCfg.getCharset());
        httpPost.setEntity(formEntity);                                  //添加请求post数据
        return this.send(httpRequest, httpPost);
    }

    /**
     * 标准get表单请求
     *
     * @param httpRequest
     * @return
     * @throws IOException
     */
    public HttpRep sendGetForm(HttpReq httpRequest) throws UnknownHostException, HttpHostConnectException,
            ConnectTimeoutException, SocketTimeoutException, Exception {
        HttpGet httpGet = new HttpGet(httpCfg.getUrl());
        return this.send(httpRequest, httpGet);
    }

    /**
     * post提交文本请求
     *
     * @param httpRequest
     * @return
     * @throws IOException
     */
    public HttpRep sendPostString(HttpReq httpRequest) throws UnknownHostException, HttpHostConnectException,
            ConnectTimeoutException, SocketTimeoutException, Exception {
        HttpPost httpPost = new HttpPost(httpCfg.getUrl());
        StringEntity formEntity = new StringEntity(httpRequest.getRequestBody(), httpCfg.getCharset());
        httpPost.setEntity(formEntity);                                  //添加请求post数据
        return this.send(httpRequest, httpPost);
    }

    /**
     * http核心方法
     *
     * @param httpRequest
     * @param httpMethod
     * @return
     * @throws IOException
     */
    private HttpRep send(HttpReq httpRequest, HttpRequestBase httpMethod)
            throws UnknownHostException, HttpHostConnectException,
            ConnectTimeoutException, SocketTimeoutException, Exception {
        HttpClient httpclient = this.getConnection();

        for (Map.Entry<String, String> entry : httpRequest.getHeadMap().entrySet()) {        //添加报文头
            httpMethod.addHeader(entry.getKey(), entry.getValue());
        }

        HttpRep respons = new HttpRep();
        try {
            HttpResponse response = httpclient.execute(httpMethod);
            if (HttpStatus.SC_OK != response.getStatusLine().getStatusCode())  //如果web服务器
            {
                throw new IOException("服务器应答失败,Http返回码为" + response.getStatusLine().getStatusCode());
            }
            String rspMsg = EntityUtils.toString(response.getEntity(), httpCfg.getCharset());
            respons.setContent(rspMsg);
        } catch (UnknownHostException e) {
            throw e;
        } catch (HttpHostConnectException e) {
            throw e;
        } catch (ConnectTimeoutException e) {
            throw e;
        } catch (SocketTimeoutException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
        return respons;
    }

//    public static void main(String[] args) throws Exception {
//        HttpReq httpReq = new HttpReq();
//        httpReq.setUrl("https://127.0.0.1:8443/");
//        httpReq.setRequestBody("test");
//
//        HttpCfg httpCfg = new HttpCfg();
//        httpCfg.setConnTimeOut(10000);
//        httpCfg.setReadTimeOut(2000);
//        httpCfg.setPort(8443);
//        httpCfg.setHttpsFlag(2);
//        httpCfg.setProtocol(HttpCfg.HTTPS);
//        httpCfg.setPfxPath("/export/safefile/test/custom.jks");
//        httpCfg.setPfxPass("password");
//        HttpRequester httpRequester = new HttpRequester(httpCfg);
//
//        HttpRep httpRep = httpRequester.sendPostString(httpReq);
//        System.out.println(httpRep.getContent());
//    }

    public static void main(String[] args) throws UnknownHostException, MalformedURLException {
        String req = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><MasMessage xmlns=\"http://www.99bill.com/mas_cnp_merchant_interface\"><version>1.0</version><QryTxnMsgContent><externalRefNumber>1111114</externalRefNumber><txnType>PUR</txnType><merchantId>812310060110178</merchantId><terminalId>01800875</terminalId></QryTxnMsgContent></MasMessage>";


        URL url = new URL("https://gs.zufangzi.com/reflesh?version=1");
        System.err.println(url.getHost());
        System.err.println(url.getPath());
        System.err.println(url.getPort());
        System.err.println(url.getDefaultPort());

        HttpReq httpReq = new HttpReq();

//        Map<String, String> map = new HashMap<String, String>();
//        String authString = "812310060110178:vpos123";
//        String auth = "Basic " + Base64.encodeBase64String(authString.getBytes());
//        map.put("Authorization", auth);
//        httpReq.setHeadMap(map);


        httpReq.setRequestBody(req);

        HttpCfg httpCfg = new HttpCfg();
        httpCfg.setConnTimeOut(10000);
        httpCfg.setReadTimeOut(2000);
       /* httpCfg.setPort(443);
        httpCfg.setProtocol(HttpCfg.HTTPS);*/
        httpCfg.setHttps2way(true);
        httpCfg.setUrl("gs.zufangzi.com/reflesh?version=1");

        HttpRequester httpRequester = new HttpRequester(httpCfg);

        try {
            HttpRep httpRep = httpRequester.sendPostString(httpReq);
            System.out.println(httpRep.getContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
