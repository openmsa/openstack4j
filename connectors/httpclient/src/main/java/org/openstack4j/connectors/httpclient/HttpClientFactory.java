package org.openstack4j.connectors.httpclient;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openstack4j.core.transport.Config;
import org.openstack4j.core.transport.UntrustedSSL;
import org.openstack4j.core.transport.internal.HttpExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Creates the initial HttpClient and keeps it as a singleton to preserve
 * pooling strategies within the Http Client
 *
 * @author Jeremy Unruh
 */
public class HttpClientFactory {

	public static final HttpClientFactory INSTANCE = new HttpClientFactory();
	private static final String USER_AGENT = "OpenStack4j-Agent";
	private static final Logger LOG = LoggerFactory.getLogger(HttpExecutor.class);
	private CloseableHttpClient client;

	/**
	 * Creates or Returns an existing HttpClient
	 *
	 * @param config the configuration
	 * @return CloseableHttpClient
	 */
	CloseableHttpClient getClient(final Config config) {
		return buildClient(config);
	}

	private CloseableHttpClient buildClient(final Config config) {
		final HttpClientBuilder cb = HttpClientBuilder.create().setUserAgent(USER_AGENT);

		if (config.getProxy() != null) {
			try {
				final URL url = new URL(config.getProxy().getHost());
				final HttpHost proxy = new HttpHost(url.getHost(), config.getProxy().getPort(), url.getProtocol());
				cb.setProxy(proxy);
			} catch (final MalformedURLException e) {
				LOG.error(e.getMessage(), e);
			}
		}

		if (config.isIgnoreSSLVerification()) {
			cb.setSslcontext(UntrustedSSL.getSSLContext());
			cb.setHostnameVerifier(new AllowAllHostnameVerifier());
		}

		if (config.getSslContext() != null) {
			cb.setSslcontext(config.getSslContext());
		}

		if (config.getMaxConnections() > 0) {
			cb.setMaxConnTotal(config.getMaxConnections());
		}

		if (config.getMaxConnectionsPerRoute() > 0) {
			cb.setMaxConnPerRoute(config.getMaxConnectionsPerRoute());
		}

		final RequestConfig.Builder rcb = RequestConfig.custom();

		if (config.getConnectTimeout() > 0) {
			rcb.setConnectTimeout(config.getConnectTimeout());
		}

		if (config.getReadTimeout() > 0) {
			rcb.setSocketTimeout(config.getReadTimeout());
		}

		if (config.getRetry() > 0) {
			cb.setRetryHandler(new OsHttpRequestRetryHandler(config.getRetry()));
		}

		return cb.setDefaultRequestConfig(rcb.build()).build();
	}
}
