/**
 *     Copyright (C) 2019-2024 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package org.openstack4j.connectors.webclient;

import java.net.URI;

import javax.net.ssl.SSLException;

import org.openstack4j.core.transport.Config;
import org.openstack4j.core.transport.HttpRequest;
import org.openstack4j.core.transport.ObjectMapperSingleton;
import org.openstack4j.core.transport.ProxyHost;
import org.openstack4j.core.transport.functions.EndpointURIFromRequestFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import jakarta.annotation.Nullable;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.ProxyProvider;
import reactor.netty.transport.ProxyProvider.Proxy;

public class HttpClientFactory {
	private static final String USER_AGENT = "OpenStack4j-Agent";
	private static final Logger LOG = LoggerFactory.getLogger(HttpClientFactory.class);

	<R> WebClient getClient(final HttpRequest<R> request) {
		final Config config = request.getConfig();
		final String url = new EndpointURIFromRequestFunction().apply(request);
		final HttpClient httpClient = getHttpClient(URI.create(url), config.isIgnoreSSLVerification());
		if (config.getProxy() != null) {
			applyProxy(httpClient, config);
		}
		final Builder wcb = WebClient.builder();
		final Object entity = request.getEntity();
		if (entity != null) {
			final ObjectMapper mapper = ObjectMapperSingleton.getContext(entity.getClass());
			final ExchangeStrategies strategies = ExchangeStrategies
					.builder()
					.codecs(clientDefaultCodecsConfigurer -> {
						clientDefaultCodecsConfigurer.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder(mapper, MediaType.APPLICATION_JSON));
						clientDefaultCodecsConfigurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(mapper, MediaType.APPLICATION_JSON));

					}).build();
			wcb.exchangeStrategies(strategies);
		}
		return applyBasicWebClientBuilder(wcb, httpClient, url).build();
	}

	private void applyProxy(final HttpClient httpClient, final Config config) {
		final ProxyHost pxy = config.getProxy();
		httpClient.tcpConfiguration(tcpClient -> tcpClient
				.proxy(proxy -> proxySet(proxy, pxy)));
		pxy.getHost();
	}

	void proxySet(final ProxyProvider.TypeSpec proxy, final ProxyHost pxy) {
		final reactor.netty.transport.ProxyProvider.Builder res = proxy
				.type(Proxy.HTTP)
				.host(pxy.getHost())
				.port(pxy.getPort());
		if (pxy.getUsername() != null) {
			res.username(pxy.getUsername());
		}
		if (pxy.getPassword() != null) {
			res.password(x -> pxy.getPassword());
		}
	}

	private Builder applyBasicWebClientBuilder(final Builder wcb, final HttpClient httpClient, final String url) {
		final ClientHttpConnector conn = new ReactorClientHttpConnector(httpClient);
		wcb.clientConnector(conn);
		wcb.baseUrl(url);
		return wcb;
	}

	private static HttpClient getHttpClient(final URI url, final Boolean ignoreSsl) {
		final SslContext sslContext = buildSslContext(url, ignoreSsl);
		HttpClient client = HttpClient.create();
		if (null != sslContext) {
			// The copy is needed, because secure return another instance of client.
			client = client.secure(t -> t.sslContext(sslContext));
		}
		return client;
	}

	private static SslContext buildSslContext(final URI url, final Boolean ignoreSsl) {
		if (url.getScheme().equals("http")) {
			return null;
		}
		if ((ignoreSsl != null) && ignoreSsl) {
			return bypassAllSsl();
		}
		return defaultSslContext();
	}

	private static @Nullable SslContext defaultSslContext() {
		try {
			return SslContextBuilder.forClient().build();
		} catch (final SSLException e) {
			throw new WebClientException(e);
		}
	}

	private static SslContext bypassAllSsl() {
		try {
			return SslContextBuilder.forClient()
					.trustManager(InsecureTrustManagerFactory.INSTANCE)
					.build();
		} catch (final SSLException e) {
			throw new WebClientException(e);
		}
	}

}
