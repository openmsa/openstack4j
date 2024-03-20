package org.openstack4j.connectors.httpclient5;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.ssl.NoopHostnameVerifier;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactoryBuilder;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.io.SocketConfig;
import org.apache.hc.core5.util.Timeout;
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
	private static HttpClientConfigInterceptor INTERCEPTOR;
	private CloseableHttpClient client;

	/**
	 * Registers a HttpClientConfigInterceptor that is invoked prior to a new
	 * HttpClient being created.
	 *
	 * @param interceptor the http config interceptor
	 */
	public static void registerInterceptor(final HttpClientConfigInterceptor interceptor) {
		INTERCEPTOR = interceptor;
	}

	/**
	 * Creates or Returns an existing HttpClient
	 *
	 * @param config the configuration
	 * @return CloseableHttpClient
	 */
	CloseableHttpClient getClient(final Config config) {
		if (client == null) {
			synchronized (this) {
				if (client == null) {
					client = buildClient(config);
				}
			}
		}
		return client;
	}

	private CloseableHttpClient buildClient(final Config config) {
		final HttpClientBuilder cb = HttpClientBuilder.create().setUserAgent(USER_AGENT);

		if (config.getProxy() != null) {
			try {
				final URL url = new URL(config.getProxy().getHost());
				final HttpHost proxy = new HttpHost(url.getHost(), url.getProtocol(), config.getProxy().getPort());
				cb.setProxy(proxy);
			} catch (final MalformedURLException e) {
				LOG.error(e.getMessage(), e);
			}
		}

		final PoolingHttpClientConnectionManagerBuilder phccmb = PoolingHttpClientConnectionManagerBuilder.create();
		if (config.isIgnoreSSLVerification()) {
			final SSLConnectionSocketFactory sslSocketFactory = SSLConnectionSocketFactoryBuilder.create()
					.setSslContext(UntrustedSSL.getSSLContext())
					.setHostnameVerifier(NoopHostnameVerifier.INSTANCE)
					.build();
			phccmb.setSSLSocketFactory(sslSocketFactory);
		}

		if (config.getSslContext() != null) {
			final SSLConnectionSocketFactory sslSocketFactory = SSLConnectionSocketFactoryBuilder.create()
					.setSslContext(config.getSslContext())
					.build();
			phccmb.setSSLSocketFactory(sslSocketFactory);
		}

		if (config.getMaxConnections() > 0) {
			phccmb.setMaxConnTotal(config.getMaxConnections());
		}

		if (config.getMaxConnectionsPerRoute() > 0) {
			phccmb.setMaxConnPerRoute(config.getMaxConnectionsPerRoute());
		}

		final RequestConfig.Builder rcb = RequestConfig.custom();

		if (config.getConnectTimeout() > 0) {
			rcb.setConnectTimeout(Timeout.ofSeconds(config.getConnectTimeout()));
		}

		if (config.getReadTimeout() > 0) {
			phccmb.setDefaultSocketConfig(SocketConfig.custom()
					.setSoTimeout(Timeout.ofSeconds(config.getReadTimeout()))
					.build());
		}

		if (INTERCEPTOR != null) {
			INTERCEPTOR.onClientCreate(cb, rcb, config);
		}
		cb.setConnectionManager(phccmb.build());
		return cb.setDefaultRequestConfig(rcb.build()).build();
	}
}
