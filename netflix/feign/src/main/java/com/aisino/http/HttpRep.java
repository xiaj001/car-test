package com.aisino.http;

import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: suxx
 * Date: 13-11-7
 * Time: 下午2:39
 * To change this template use File | Settings | File Templates.
 */
public class HttpRep {
    /**
     * 响应地址
     */
    private String urlString;

    /**
     * 默认端口号
     */
    private int defaultPort;

    /**
     * 文件
     */
    private String file;

    /**
     * 主机
     */
    private String host;

    /**
     * 路径
     */
    private String path;

    /**
     * 端口
     */
    private int port;

    /**
     * 协议
     */
    private String protocol;

    /**
     * 请求参数
     */
    private String query;

    /**
     * 请求
     */
    private String ref;

    /**
     * 浏览器信息
     */
    private String userInfo;

    /**
     * 编码
     */
    private String contentEncoding;

    /**
     * 信息
     */
    private String content;

    /**
     * 信息类型
     */
    private String contentType;

    /**
     * 响应码
     */
    private int code;

    /**
     * 消息
     */
    private String message;
    /**
     * 方法
     */
    private String method;

    /**
     * 连接超时
     */
    private int connectTimeout;

    /**
     * 读超时
     */
    private int readTimeout;

    /**
     * 其他数据
     */
    private Vector<String> contentCollection;

    public String getUrlString() {
        return urlString;
    }

    public void setUrlString(String urlString) {
        this.urlString = urlString;
    }

    public int getDefaultPort() {
        return defaultPort;
    }

    public void setDefaultPort(int defaultPort) {
        this.defaultPort = defaultPort;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    public String getContentEncoding() {
        return contentEncoding;
    }

    public void setContentEncoding(String contentEncoding) {
        this.contentEncoding = contentEncoding;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    public Vector<String> getContentCollection() {
        return contentCollection;
    }

    public void setContentCollection(Vector<String> contentCollection) {
        this.contentCollection = contentCollection;
    }
}
