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

import org.openstack4j.api.exceptions.ResponseException;
import org.openstack4j.core.transport.ClientConstants;
import org.openstack4j.core.transport.HttpExecutorService;
import org.openstack4j.core.transport.HttpRequest;
import org.openstack4j.core.transport.HttpResponse;
import org.openstack4j.openstack.internal.OSAuthenticator;
import org.openstack4j.openstack.internal.OSClientSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class HttpExecutorServiceImpl implements HttpExecutorService {

	private static final String NAME = "Apache WebClient Connector";
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
	 * Invokes the given requestr
	 *
	 * @param <R>     the return type
	 * @param request the request to invoke
	 * @return the response
	 * @throws Exception the exception
	 */
	private <R> HttpResponse invoke(final HttpRequest<R> request) throws Exception {
		final HttpCommand<R> command = HttpCommand.create(request);
		final ResponseEntity<R> res = invokeRequest(command);
		return map(res);
	}

	private <R> HttpResponse map(final ResponseEntity<R> body) {
		return new HttpResponseImpl(body);
	}

	private <R> ResponseEntity<R> invokeRequest(final HttpCommand<R> command) throws Exception {
		final ResponseEntity<R> res = command.execute();
		if (res.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
			OSAuthenticator.reAuthenticate();
			command.addOrReplaceHeader(ClientConstants.HEADER_X_AUTH_TOKEN, OSClientSession.getCurrent().getTokenId());
			return command.execute();
		}
		return res;
	}

	@Override
	public String getExecutorDisplayName() {
		return NAME;
	}

}
