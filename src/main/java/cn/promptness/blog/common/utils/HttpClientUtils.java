package cn.promptness.blog.common.utils;

import cn.promptness.blog.vo.HttpResult;
import com.alibaba.fastjson.JSON;
import org.apache.http.Header;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Lynn
 */
public class HttpClientUtils {

    private static final Charset UTF_8 = StandardCharsets.UTF_8;

    private final CloseableHttpClient httpClient;

    private final RequestConfig requestConfig;

    public HttpClientUtils(CloseableHttpClient httpClient, RequestConfig requestConfig) {
        this.httpClient = httpClient;
        this.requestConfig = requestConfig;
    }

    public HttpResult doGet(String url, Map<String, String> param, List<Header> headers) throws Exception {
        return doGet(url, param, null, headers, null);
    }

    public HttpResult doGet(String url, Map<String, String> param, List<Cookie> cookies, List<Header> headers, FileOutputStream fileOutputStream) throws Exception {

        URIBuilder builder = new URIBuilder(url);
        if (param != null && !param.isEmpty()) {
            for (Map.Entry<String, String> entry : param.entrySet()) {
                builder.setParameter(entry.getKey(), entry.getValue());
            }
        }

        URI uri = builder.build();

        HttpGet httpGet = new HttpGet(uri.toString());

        setCookies(cookies, httpGet);
        setHead(headers, httpGet);

        httpGet.setConfig(requestConfig);

        if (fileOutputStream == null) {
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                return new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(response.getEntity(), UTF_8));
            }
        } else {
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    outFile(fileOutputStream, response);
                }
            }
            return HttpResult.SUCCESS;
        }
    }

    public HttpResult doPost(String url, Map<String, String> param, List<Cookie> cookies, FileOutputStream fileOutputStream) throws Exception {

        HttpPost httpPost = new HttpPost(url);
        setCookies(cookies, httpPost);
        setEntityData(param, httpPost);

        httpPost.setConfig(requestConfig);

        if (fileOutputStream == null) {
            return getHttpResult(httpPost);
        } else {
            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    outFile(fileOutputStream, response);
                    return HttpResult.SUCCESS;
                }
                return HttpResult.getErrorHttpResult(response.getStatusLine().getStatusCode());
            }
        }
    }

    public HttpResult doPostFile(String url, Map<String, String> params, List<Cookie> cookies, Map<String, File> files) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        setCookies(cookies, httpPost);

        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();

        if (files != null && !files.isEmpty()) {
            for (Map.Entry<String, File> entry : files.entrySet()) {
                multipartEntityBuilder.addBinaryBody(entry.getKey(), entry.getValue());
            }
        }

        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                multipartEntityBuilder.addTextBody(entry.getKey(), entry.getValue(), ContentType.TEXT_PLAIN.withCharset(UTF_8));
            }
        }
        httpPost.setEntity(multipartEntityBuilder.build());

        return getHttpResult(httpPost);

    }


    public HttpResult doPostJson(String url, Map<String, String> param, List<Cookie> cookies) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(this.requestConfig);
        setCookies(cookies, httpPost);
        setJsonEntity(param, httpPost);
        return getHttpResult(httpPost);
    }


    /**
     * 设置Entity数据
     *
     * @param param                          参数
     * @param httpEntityEnclosingRequestBase httpclient
     */
    private void setEntityData(Map<String, String> param, HttpEntityEnclosingRequestBase httpEntityEnclosingRequestBase) {
        if (param == null || param.isEmpty()) {
            return;
        }
        List<NameValuePair> parameters = new ArrayList<>();

        for (Map.Entry<String, String> entry : param.entrySet()) {
            parameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        // 构造一个form表单式的实体
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters, UTF_8);
        // 将请求实体设置到httpPost对象中
        httpEntityEnclosingRequestBase.setEntity(formEntity);
    }

    private void setCookies(List<Cookie> cookies, HttpRequestBase httpRequestBase) {
        if (cookies == null || cookies.isEmpty()) {
            return;
        }
        StringBuilder cookieStr = new StringBuilder();
        for (Cookie cookie : cookies) {
            cookieStr.append("; ").append(cookie.getName()).append("=").append(cookie.getValue());
        }
        httpRequestBase.setHeader("Cookie", cookieStr.substring(2));

    }

    private void setHead(List<Header> headers, HttpRequestBase httpRequestBase) {
        if (headers == null || headers.isEmpty()) {
            return;
        }
        for (Header header : headers) {
            httpRequestBase.setHeader(header);
        }
    }

    private void setJsonEntity(Object param, HttpEntityEnclosingRequestBase httpEntityEnclosingRequestBase) {
        if (param != null) {
            // 构造一个字符串的实体
            StringEntity stringEntity = new StringEntity(JSON.toJSONString(param), ContentType.APPLICATION_JSON.withCharset(UTF_8));
            // 将请求实体设置到httpPost对象中
            httpEntityEnclosingRequestBase.setEntity(stringEntity);
        }
    }

    private void outFile(FileOutputStream fileOutputStream, CloseableHttpResponse response) throws IOException {
        InputStream inputStream = response.getEntity().getContent();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        byte[] bs = new byte[1024];
        int len;
        while ((len = inputStream.read(bs)) != -1) {
            bufferedOutputStream.write(bs, 0, len);
        }
        bufferedOutputStream.close();
        inputStream.close();
    }

    private HttpResult getHttpResult(HttpRequestBase httpRequestBase) throws IOException {
        try (CloseableHttpResponse response = httpClient.execute(httpRequestBase)) {
            // 执行请求
            if (response.getEntity() != null) {
                return new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(response.getEntity(), UTF_8));
            }
            return HttpResult.ENTITY_EMPTY;
        }
    }
}
