package org.openstack4j.connectors.httpclient5;

import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.openstack4j.api.exceptions.ConnectionException;
import org.openstack4j.api.exceptions.ResponseException;
import org.openstack4j.core.transport.ClientConstants;
import org.openstack4j.core.transport.HttpExecutorService;
import org.openstack4j.core.transport.HttpRequest;
import org.openstack4j.core.transport.HttpResponse;
import org.openstack4j.openstack.internal.OSAuthenticator;
import org.openstack4j.openstack.internal.OSClientSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HttpExecutor is the default implementation for HttpExecutorService which is
 * responsible for interfacing with HttpClient and mapping common status codes,
 * requests and responses back to the common API
 *
 * @author Jeremy Unruh
 */
public class HttpExecutorServiceImpl implements HttpExecutorService {

	private static final String NAME = "Apache HttpClient Connector";
	private static final Logger LOG = LoggerFactory.getLogger(HttpExecutorServiceImpl.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <R> HttpResponse execute(final HttpRequest<R> request) {
		try {
			return invoke(request);
		} catch (final ResponseException re) {
			throw re;
		} catch (final Exception e) {
			LOG.error(e.getMessage(), e);
			return null;
		}
	}

	/**
	 * Invokes the given request
	 *
	 * @param <R>     the return type
	 * @param request the request to invoke
	 * @return the response
	 * @throws Exception the exception
	 */
	private <R> HttpResponse invoke(final HttpRequest<R> request) throws Exception {

		final HttpCommand<R> command = HttpCommand.create(request);

		try {
			return invokeRequest(command);
		} catch (final ResponseException re) {
			throw re;
		} catch (final Exception pe) {
			throw new ConnectionException(pe.getMessage(), 0, pe);
		}
	}

	private <R> HttpResponse invokeRequest(final HttpCommand<R> command) throws Exception {
		final CloseableHttpResponse response = command.execute();

		if ((command.getRetries() == 0) && (response.getCode() == 401) && !command.getRequest().getHeaders().containsKey(ClientConstants.HEADER_OS4J_AUTH)) {
			try {
				OSAuthenticator.reAuthenticate();
				command.getRequest().getHeaders().put(ClientConstants.HEADER_X_AUTH_TOKEN, OSClientSession.getCurrent().getTokenId());
			} finally {
				response.close();
			}
			return invokeRequest(command.incrementRetriesAndReturn());
		}

		return HttpResponseImpl.wrap(response);
	}

	@Override
	public String getExecutorDisplayName() {
		return NAME;
	}
}
