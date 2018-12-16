package http;

import okhttp3.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author 程天亮
 * @Created
 */
public class HttpUtils {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static OkHttpClient okHttpImageClient = null;

    static {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.readTimeout(120, TimeUnit.SECONDS);
        clientBuilder.connectTimeout(60, TimeUnit.SECONDS);
        okHttpImageClient = clientBuilder.build();
    }

    public static String get(String url, Map<String, String> params, Map<String, String> head) {
        if (null == url) throw new NullPointerException("Url == null");
        String getUrl = buildGetUrl(url, params);
        Request.Builder reqBuilder = new Request.Builder();
        if (null != head) {
            head.forEach((k, v) -> reqBuilder.addHeader(k, v));
        }
        reqBuilder.url(getUrl);
        return doCall(reqBuilder.build(), getUrl);
    }

    public static byte[] getImage(String url) {
        if (null == url) {
            throw new NullPointerException("Url == null");
        }

        String getUrl = buildGetUrl(url, null);
        Request.Builder reqBuilder = new Request.Builder();
        reqBuilder.url(getUrl);

        Call call = okHttpImageClient.newCall(reqBuilder.build());
        try {
            Response response = call.execute();
            if (!response.isSuccessful()) {
               // throw new NetException("getImage " + url + " return error http status " + response.code(),500000);
            }
            return response.body().bytes();
        } catch (IOException e) {
            //throw new NetException("getImage " + url + " error", e,50000);
        }
        return null;
    }

    public static String postForm(String url, Map<String, String> params, Map<String, String> head) {
        if (null == url) throw new NullPointerException("Url == null");
        Request.Builder reqBuilder = new Request.Builder();
        if (null != head) {
            head.forEach((k, v) -> reqBuilder.addHeader(k, v));
        }
        FormBody.Builder formBuilder = new FormBody.Builder();
        if (null != params) {
            params.forEach((k, v) -> formBuilder.add(k, v));
        }
        reqBuilder.url(url).post(formBuilder.build());
        String result = doCall(reqBuilder.build(), url);
        return result;
    }

    public static String postBody(String url, String postBody) {
        if (null == url) throw new NullPointerException("Url == null");
        Request.Builder reqBuilder = new Request.Builder();

        RequestBody requestBody = RequestBody.create(JSON, postBody);
        reqBuilder.url(url).post(requestBody);
        String result = doCall(reqBuilder.build(), url);
        return result;
    }

    private static String doCall(Request request, String url) {
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            if (!response.isSuccessful()) {
               // throw new NetException("post " + url + " return error http status " + response.code(),500000);
            }
            return response.body().string();
        } catch (IOException e) {
            //throw new NetException("post " + url + " error", e,50000);
        }

        return null;
    }

    private static String buildGetUrl(String url, Map<String, String> params) {
        if (null == params || url == null) return url;
        StringBuilder sb = new StringBuilder(url);
        int index = url.indexOf("?");
        if (index == -1) {
            sb.append("?");
        } else if (index > 0 && index < url.length() - 1) {
            if (!url.endsWith("&")) {
                sb.append("&");
            }
        } else {
            //?结尾，不做处理
        }
        params.forEach((k, v) -> sb.append(k).append("=").append(v).append("&"));
        if (params.size() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        String s = HttpUtils.get("https://gs.zufangzi.com/reflesh?version=1", null, null);
        System.err.println(s);
    }
}
