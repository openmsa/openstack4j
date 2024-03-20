package org.openstack4j.connectors.httpclient5;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpEntity;
import org.openstack4j.api.exceptions.ClientResponseException;
import org.openstack4j.core.transport.ClientConstants;
import org.openstack4j.core.transport.ExecutionOptions;
import org.openstack4j.core.transport.HttpEntityHandler;
import org.openstack4j.core.transport.HttpResponse;
import org.openstack4j.core.transport.ObjectMapperSingleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpResponseImpl implements HttpResponse {

	private static final Logger LOG = LoggerFactory.getLogger(HttpResponseImpl.class);
	private final CloseableHttpResponse response;

	private HttpResponseImpl(final CloseableHttpResponse response) {
		this.response = response;
	}

	/**
	 * Wrap the given Response
	 *
	 * @param response the response
	 * @return the HttpResponse
	 */
	public static HttpResponseImpl wrap(final CloseableHttpResponse response) {
		return new HttpResponseImpl(response);
	}

	/**
	 * Unwrap and return the original Response
	 *
	 * @return the response
	 */
	public CloseableHttpResponse unwrap() {
		return response;
	}

	/**
	 * Gets the entity and Maps any errors which will result in a ResponseException
	 *
	 * @param <T>        the generic type
	 * @param returnType the return type
	 * @return the entity
	 */
	@Override
	public <T> T getEntity(final Class<T> returnType) {
		return getEntity(returnType, null);
	}

	/**
	 * Gets the entity and Maps any errors which will result in a ResponseException
	 *
	 * @param <T>        the generic type
	 * @param returnType the return type
	 * @param options    execution options
	 * @return the entity
	 */
	@Override
	public <T> T getEntity(final Class<T> returnType, final ExecutionOptions<T> options) {
		return HttpEntityHandler.handle(this, returnType, options, Boolean.TRUE);
	}

	/**
	 * Gets the status from the previous Request
	 *
	 * @return the status code
	 */
	@Override
	public int getStatus() {
		return response.getCode();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getStatusMessage() {
		return response.getReasonPhrase();
	}

	/**
	 * @return the input stream
	 */
	@Override
	public InputStream getInputStream() {
		final HttpEntity entity = response.getEntity();
		try {
			if (entity != null) {
				return entity.getContent();
			}
		} catch (final Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * Returns a Header value from the specified name key
	 *
	 * @param name the name of the header to query for
	 * @return the header as a String or null if not found
	 */
	@Override
	public String header(final String name) {
		final Header header = response.getFirstHeader(name);
		return (header != null) ? header.getValue() : null;
	}

	/**
	 * @return the a Map of Header Name to Header Value
	 */
	@Override
	public Map<String, String> headers() {
		final Map<String, String> retHeaders = new HashMap<>();
		final Header[] headers = response.getHeaders();

		for (final Header h : headers) {
			retHeaders.put(h.getName(), h.getValue());
		}
		return retHeaders;
	}

	@Override
	public <T> T readEntity(final Class<T> typeToReadAs) {
		final HttpEntity entity = response.getEntity();
		if (entity == null) {
			// Normal case if the response has no content, e.g. for a HEAD request
			return null;
		}
		try {
			final InputStream content = Objects.requireNonNull(entity.getContent(), "Entity content should not be null.");
			return ObjectMapperSingleton.getContext(typeToReadAs).readerFor(typeToReadAs).readValue(content);
		} catch (final Exception e) {
			LOG.error(e.getMessage(), e);
			throw new ClientResponseException(e.getMessage(), 0, e);
		}
	}

	@Override
	public void close() throws IOException {
		if (response != null) {
			response.close();
		}
	}

	@Override
	public String getContentType() {
		return header(ClientConstants.HEADER_CONTENT_TYPE);
	}
}
