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

import java.util.List;
import java.util.Map;

import org.openstack4j.api.exceptions.AuthenticationException;
import org.openstack4j.core.transport.HttpRequest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodyUriSpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;

import reactor.core.publisher.Mono;

public class HttpCommand<R> {
	private static final String USER_AGENT = "OpenStack4j-Agent";
	private final WebClient wc;
	private final HttpRequest<R> request;
	private RequestBodyUriSpec spec;

	private HttpCommand(final WebClient real, final HttpRequest<R> request) {
		this.wc = real;
		this.request = request;
		initialize(request);
	}

	public static <R> HttpCommand<R> create(final HttpRequest<R> request) {
		final HttpClientFactory cli = new HttpClientFactory();
		final WebClient real = cli.getClient(request);

		return new HttpCommand<>(real, request);
	}

	public ResponseEntity<R> execute() {
		if (request.hasJson()) {
			spec.bodyValue(request.getJson());
		}
		if (request.getEntity() != null) {
			spec.bodyValue(request.getEntity());
		}
		return spec.retrieve()
				.onStatus(HttpStatusCode::is4xxClientError, clientResponse -> clientResponse.bodyToMono(String.class)
						.flatMap(error -> Mono.error(new AuthenticationException(error, clientResponse.statusCode().value()))))
				.toEntity(request.getReturnType()).block();
	}

	void initialize(final HttpRequest<R> request) {
		this.spec = wc.method(toMethod(request.getMethod()));
		spec.accept(MediaType.APPLICATION_JSON);
		spec.header("User-Agent", USER_AGENT);
		populateHeaders(spec, request);
		populateQueryParams(spec, request);
	}

	private static HttpMethod toMethod(final org.openstack4j.core.transport.HttpMethod method) {
		return switch (method) {
		case HEAD -> HttpMethod.HEAD;
		case GET -> HttpMethod.GET;
		case POST -> HttpMethod.POST;
		case PUT -> HttpMethod.PUT;
		case PATCH -> HttpMethod.PATCH;
		case DELETE -> HttpMethod.DELETE;
		case OPTIONS -> HttpMethod.OPTIONS;
		case TRACE -> HttpMethod.TRACE;
		default -> throw new IllegalArgumentException("Unexpected value: " + method);
		};
	}

	private void populateQueryParams(final RequestHeadersUriSpec<?> spec, final HttpRequest<R> request) {
		if (null == request.getQueryParams()) {
			return;
		}
		spec.uri(x -> {
			for (final Map.Entry<String, List<Object>> entry : request.getQueryParams().entrySet()) {
				final List<String> values = entry.getValue().stream().map(String::valueOf).toList();
				x.queryParam(entry.getKey(), values);
			}
			return x.build();
		});
	}

	private void populateHeaders(final RequestBodyUriSpec spec, final HttpRequest<R> request) {
		if (!request.hasHeaders()) {
			return;
		}
		for (final Map.Entry<String, Object> h : request.getHeaders().entrySet()) {
			spec.header(h.getKey(), String.valueOf(h.getValue()));
		}
	}

	public void addOrReplaceHeader(final String headerXAuthToken, final String tokenId) {

	}

}
