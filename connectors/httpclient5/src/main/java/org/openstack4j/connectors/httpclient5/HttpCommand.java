package org.openstack4j.connectors.httpclient5;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import org.apache.hc.client5.http.classic.methods.HttpDelete;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpHead;
import org.apache.hc.client5.http.classic.methods.HttpPatch;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpPut;
import org.apache.hc.client5.http.classic.methods.HttpUriRequest;
import org.apache.hc.client5.http.entity.EntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.InputStreamEntity;
import org.apache.hc.core5.http.message.BasicClassicHttpRequest;
import org.apache.hc.core5.net.URIBuilder;
import org.openstack4j.api.exceptions.ConnectionException;
import org.openstack4j.core.transport.HttpRequest;
import org.openstack4j.core.transport.ObjectMapperSingleton;
import org.openstack4j.core.transport.functions.EndpointURIFromRequestFunction;

/**
 * HttpCommand is responsible for executing the actual request driven by the
 * HttpExecutor.
 */
public final class HttpCommand<R> {

	HttpUriRequest clientReq;
	private final HttpRequest<R> request;
	private CloseableHttpClient client;
	private int retries;

	private HttpCommand(final HttpRequest<R> request) {
		this.request = request;
	}

	/**
	 * Creates a new HttpCommand from the given request
	 *
	 * @param request the request
	 * @return the command
	 */
	public static <R> HttpCommand<R> create(final HttpRequest<R> request) {
		final HttpCommand<R> command = new HttpCommand<>(request);
		command.initialize();
		return command;
	}

	private void initialize() {
		URI url = null;
		try {
			url = populateQueryParams(request);
		} catch (final URISyntaxException e) {
			throw new ConnectionException(e.getMessage(), e.getIndex(), e);
		}
		client = HttpClientFactory.INSTANCE.getClient(request.getConfig());

		switch (request.getMethod()) {
		case POST:
			clientReq = new HttpPost(url);
			break;
		case PUT:
			clientReq = new HttpPut(url);
			break;
		case DELETE:
			clientReq = new HttpDelete(url);
			break;
		case HEAD:
			clientReq = new HttpHead(url);
			break;
		case PATCH:
			clientReq = new HttpPatch(url);
			break;
		case GET:
			clientReq = new HttpGet(url);
			break;
		default:
			throw new IllegalArgumentException("Unsupported http method: " + request.getMethod());
		}
		clientReq.setHeader("Accept", "application/json");
		populateHeaders(request);
	}

	/**
	 * Executes the command and returns the Response
	 *
	 * @return the response
	 */
	public CloseableHttpResponse execute() throws Exception {

		EntityBuilder builder = null;

		if (request.getEntity() != null) {
			if (InputStream.class.isAssignableFrom(request.getEntity().getClass())) {
				final InputStreamEntity ise = new InputStreamEntity((InputStream) request.getEntity(),
						ContentType.create(request.getContentType()));
				((BasicClassicHttpRequest) clientReq).setEntity(ise);
			} else {
				builder = EntityBuilder.create().setContentType(ContentType.create(request.getContentType(), "UTF-8"))
						.setText(ObjectMapperSingleton.getContext(request.getEntity().getClass()).writer()
								.writeValueAsString(request.getEntity()));
			}
		} else if (request.hasJson()) {
			builder = EntityBuilder.create().setContentType(ContentType.APPLICATION_JSON).setText(request.getJson());
		}
		if ((builder != null) && (clientReq instanceof BasicClassicHttpRequest)) {
			((BasicClassicHttpRequest) clientReq).setEntity(builder.build());
		}

		return client.execute(clientReq);
	}

	/**
	 * @return true if a request entity has been set
	 */
	public boolean hasEntity() {
		return request.getEntity() != null;
	}

	/**
	 * @return current retry execution count for this command
	 */
	public int getRetries() {
		return retries;
	}

	/**
	 * @return incremement's the retry count and returns self
	 */
	public HttpCommand<R> incrementRetriesAndReturn() {
		initialize();
		retries++;
		return this;
	}

	public HttpRequest<R> getRequest() {
		return request;
	}

	private URI populateQueryParams(final HttpRequest<R> request) throws URISyntaxException {

		final URIBuilder uri = new URIBuilder(new EndpointURIFromRequestFunction().apply(request));

		if (!request.hasQueryParams()) {
			return uri.build();
		}

		for (final Map.Entry<String, List<Object>> entry : request.getQueryParams().entrySet()) {
			for (final Object o : entry.getValue()) {
				uri.addParameter(entry.getKey(), String.valueOf(o));
			}
		}
		return uri.build();
	}

	private void populateHeaders(final HttpRequest<R> request) {

		if (!request.hasHeaders()) {
			return;
		}

		for (final Map.Entry<String, Object> h : request.getHeaders().entrySet()) {
			clientReq.addHeader(h.getKey(), String.valueOf(h.getValue()));
		}
	}
}
