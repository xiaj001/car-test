package com.aisino.http;

public class HttpCfg {

    /**
     * http提交地址
     */
    private String url;

    /**
     * 字符编码
     */
    private String charset;

    /**
     * 帐号
     */
    private String keyStoreType;

    /**
     * 私钥路径
     */
    private String pfxPath;

    /**
     * 私钥密码
     */
    private String pfxPass;

    /**
     * 连接超时
     */
    private int connTimeOut;

    /**
     * 读超时
     */
    private int readTimeOut;

    /**
     * 协议
     */
    private String protocol = HTTPS;

    /**
     * 标识单向认证还是双向认证
     * 默认单向 = 1
     * 如果为双向，请设置为2或者其它值
     */
    private boolean https2way = true;

    /**
     * 端口
     */
    private int port;

    public static final String HTTPS = "HTTPS";
    public static final String HTTP = "HTTP";


    public String getPfxPath() {
        return pfxPath;
    }

    public void setPfxPath(String pfxPath) {
        this.pfxPath = pfxPath;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPfxPass() {
        return pfxPass;
    }

    public void setPfxPass(String pfxPass) {
        this.pfxPass = pfxPass;
    }

    public int getConnTimeOut() {
        return connTimeOut;
    }

    public void setConnTimeOut(int connTimeOut) {
        this.connTimeOut = connTimeOut;
    }

    public int getReadTimeOut() {
        return readTimeOut;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public void setReadTimeOut(int readTimeOut) {
        this.readTimeOut = readTimeOut;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public boolean isHttps2way() {
        return https2way;
    }

    public void setHttps2way(boolean https2way) {
        this.https2way = https2way;
    }

    public String getKeyStoreType() {
        return keyStoreType;
    }

    public void setKeyStoreType(String keyStoreType) {
        this.keyStoreType = keyStoreType;
    }
}
