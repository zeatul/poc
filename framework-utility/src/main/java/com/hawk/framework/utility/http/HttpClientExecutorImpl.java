package com.hawk.framework.utility.http;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.socket.ConnectionSocketFactory;
//import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import com.hawk.framework.utility.tools.JsonTools;
import com.hawk.framework.utility.tools.StringTools;


@SuppressWarnings("deprecation")
public class HttpClientExecutorImpl implements HttpExecutor {

	

	

	private String userAgent = "httpclient4.5.1";

	private final PoolingHttpClientConnectionManager connMgr;

	private final RequestConfig requestConfig;

	private void config(HttpRequestBase httpRequestBase) {
		// httpRequestBase.setHeader("User-Agent", "Mozilla/5.0");
		// httpRequestBase.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		// httpRequestBase.setHeader("Accept-Language",
		// "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");//"en-US,en;q=0.5");
		// httpRequestBase.setHeader("Accept-Charset",
		// "ISO-8859-1,utf-8,gbk,gb2312;q=0.7,*;q=0.7");
		//

		httpRequestBase.setConfig(requestConfig);
	}

	public HttpClientExecutorImpl() throws Exception {
		

		requestConfig = RequestConfig.custom().setSocketTimeout(3000) // //
																		// 设置读取超时
				.setConnectTimeout(3000) // / 设置连接超时
				.setConnectionRequestTimeout(3000) // // 设置从连接池获取连接实例的超时
				.setProxy(null) // 设置代理
				.setProxyPreferredAuthSchemes(null) // 设置代理验证
				.build();

		// 需要通过以下代码声明对https连接支持
		SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(null, new TrustSelfSignedStrategy()).build();
		HostnameVerifier hostnameVerifier = SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, hostnameVerifier);
		Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory> create()
				.register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", sslsf).build();
		

		// 初始化连接池
		connMgr = new PoolingHttpClientConnectionManager(registry);

		// 设置连接池大小
		connMgr.setMaxTotal(50);

		// 设置每个站点的连接数量
		connMgr.setDefaultMaxPerRoute(10);
		// // 也可以设置指定主机地址
		// HttpHost localhost = new HttpHost("http://blog.csdn.net/gaolu",80);
		// connMgr.setMaxPerRoute(new HttpRoute(localhost), 50);

		// 设置读取超时
		SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(3000).build();
		connMgr.setDefaultSocketConfig(socketConfig);

		// HttpParams httpParams = new BasicHttpParams();
		// String userAgent =
		// "Mozilla/5.0 (iPhone; U; CPU iPhone OS 3_0 like Mac OS X; en-us)
		// AppleWebKit/528.18 (KHTML, like Gecko) Version/4.0 Mobile/7A341
		// Safari/528.16";
		// HttpProtocolParams.setUserAgent(httpParams, userAgent);
		// HttpProtocolParams.setContentCharset(httpParams, "utf-8");
		// HttpConnectionParams.setConnectionTimeout(httpParams, 8000);
		// HttpConnectionParams.setSoTimeout(httpParams, 30000);
	}

	private ConnectionKeepAliveStrategy keepAliveStrategy = new DefaultConnectionKeepAliveStrategy() {

		@Override
		public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
			long keepAlive = super.getKeepAliveDuration(response, context);
			if (keepAlive == -1) {
				// 如果服务器没有设置keep-alive这个参数，我们就把它设置成5秒
				keepAlive = 30000;
			}
			return keepAlive;
		}

	};

	private HttpRequestRetryHandler retryHandler = new HttpRequestRetryHandler() {

		public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
			if (executionCount >= 5) {// 如果已经重试了5次，就放弃
				return false;
			}
			if (exception instanceof NoHttpResponseException) {// 如果服务器丢掉了连接，那么就重试
				return true;
			}
			if (exception instanceof SSLHandshakeException) {// 不要重试SSL握手异常
				return false;
			}
			if (exception instanceof InterruptedIOException) {// 超时
				return false;
			}
			if (exception instanceof UnknownHostException) {// 目标服务器不可达
				return false;
			}
			if (exception instanceof ConnectTimeoutException) {// 连接被拒绝
				return false;
			}
			if (exception instanceof SSLException) {// ssl握手异常
				return false;
			}

			HttpClientContext clientContext = HttpClientContext.adapt(context);
			HttpRequest request = clientContext.getRequest();
			// 如果请求是幂等的，就再次尝试
			if (!(request instanceof HttpEntityEnclosingRequest)) {
				return true;
			}
			return false;
		}
	};

	public URIBuilder generateURIBuilder(String url, List<HttpParam> params) throws URISyntaxException {
		URIBuilder uriBuilder = new URIBuilder(url);

		if (params != null && params.size() > 0) {
			List<NameValuePair> p = new ArrayList<NameValuePair>();
			if (params != null){
				for (HttpParam httpParam : params){
					p.add(new BasicNameValuePair(httpParam.getKey(), httpParam.getValue()));
				}
			}
			
			uriBuilder.setCustomQuery(URLEncodedUtils.format(p, "UTF-8"));
			
			
			
		}
		return uriBuilder;
	}

	private CloseableHttpClient buileHttpClient() {
		CloseableHttpClient client = HttpClients.custom().setUserAgent(userAgent) // userAgent
				.setKeepAliveStrategy(keepAliveStrategy) // 连接保持策略
				.setRetryHandler(retryHandler) // 重试策略
				.setConnectionManager(connMgr) // 设置连接池
				.build();

		return client;

	}

	/**
	 * 校验返回的response是否靠谱
	 * 
	 * @param response
	 * @throws HttpResponseException
	 */
	private void checkResponse(CloseableHttpResponse response) throws HttpResponseException {
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode != HttpStatus.SC_OK)
			throw new HttpResponseException(response.getStatusLine().getStatusCode(),"HttpStatus="+statusCode + ","+ response.getStatusLine().getReasonPhrase());
	}

	public String get(String path, List<HttpParam> params)  {
		CloseableHttpResponse response = null;
		try {
			CloseableHttpClient httpClient = buileHttpClient();

			HttpGet httpGet = new HttpGet(generateURIBuilder(path, params).build());
			config(httpGet);
			response = httpClient.execute(httpGet);
			checkResponse(response);

			HttpEntity entity = response.getEntity();
			return EntityUtils.toString(entity);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (response != null)
					response.close();
			} catch (Exception e) {

			}
		}
	}
	
	public String post(String url, Object object , List<HttpParam> params){
		return post(url,JsonTools.toJsonString(object),params);
	}

	public String post(String url, String content, List<HttpParam> params)  {
		CloseableHttpResponse response = null;
		try {
			CloseableHttpClient httpClient = buileHttpClient();
			
//			List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
//            for (HttpParam httpParam : params) {
//                    pairs.add(new BasicNameValuePair(httpParam.getKey(), httpParam.getValue()));
//            }
//            url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, "utf-8"));
//          HttpPost httpPost = new HttpPost(url);
			
			HttpPost httpPost = new HttpPost(generateURIBuilder(url, params).build());
            

			
			
			
			config(httpPost);
			if (StringTools.isNotNullOrEmpty(content)) {
				StringEntity stringEntity = new StringEntity(content, "utf-8");
				stringEntity.setContentEncoding("UTF-8");
				stringEntity.setContentType("application/json");
				httpPost.setEntity(stringEntity);
			}
			

			response = httpClient.execute(httpPost);
			checkResponse(response);
			HttpEntity entity = response.getEntity();

			return EntityUtils.toString(entity);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (response != null)
					response.close();
			} catch (Exception e) {

			}
		}

	}

	public String post(String url, byte[] b, List<HttpParam> params)  {
		CloseableHttpResponse response = null;
		try {
			CloseableHttpClient httpClient = buileHttpClient();
			HttpPost httpPost = new HttpPost(generateURIBuilder(url, params).build());
			config(httpPost);
			if (b != null && b.length > 0) {
				ByteArrayEntity byteArrayEntity = new ByteArrayEntity(b);
				httpPost.setEntity(byteArrayEntity);
			}

			response = httpClient.execute(httpPost);
			checkResponse(response);
			HttpEntity entity = response.getEntity();

			return EntityUtils.toString(entity);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (response != null)
					response.close();
			} catch (Exception e) {

			}
		}
	}

	public void destroy() {
		connMgr.close();
	}

	@Override
	public String buildUrl(String url, List<HttpParam> params) {
		try {
			return generateURIBuilder(url, params).build().toString();
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String postParamInBody(String url, String content, List<HttpParam> params) {
		CloseableHttpResponse response = null;
		try {
			CloseableHttpClient httpClient = buileHttpClient();
			
			List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
            for (HttpParam httpParam : params) {
                    pairs.add(new BasicNameValuePair(httpParam.getKey(), httpParam.getValue()));
            }
            content = EntityUtils.toString(new UrlEncodedFormEntity(pairs, "utf-8"));
			
//			HttpPost httpPost = new HttpPost(generateURIBuilder(url, null).build());
			
			HttpPost httpPost = new HttpPost(url);
            

			
			
			
			config(httpPost);
			if (StringTools.isNotNullOrEmpty(content)) {
				StringEntity stringEntity = new StringEntity(content, "utf-8");
				stringEntity.setContentEncoding("UTF-8");
				stringEntity.setContentType("application/x-www-form-urlencoded");
				httpPost.setEntity(stringEntity);
			}
			

			response = httpClient.execute(httpPost);
			checkResponse(response);
			HttpEntity entity = response.getEntity();

			return EntityUtils.toString(entity);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (response != null)
					response.close();
			} catch (Exception e) {

			}
		}

	}

	

}
