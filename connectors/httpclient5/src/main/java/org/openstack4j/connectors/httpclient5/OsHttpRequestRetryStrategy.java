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
package org.openstack4j.connectors.httpclient5;

import java.io.IOException;
import java.util.List;

import org.apache.hc.client5.http.impl.DefaultHttpRequestRetryStrategy;
import org.apache.hc.core5.http.HttpRequest;
import org.apache.hc.core5.http.protocol.HttpContext;
import org.apache.hc.core5.util.TimeValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OsHttpRequestRetryStrategy extends DefaultHttpRequestRetryStrategy {
	/** Logger. */
	private static final Logger LOG = LoggerFactory.getLogger(OsHttpRequestRetryStrategy.class);

	public OsHttpRequestRetryStrategy(final int retryCount) {
		super(retryCount, TimeValue.ofSeconds(1L), List.of(), List.of());
	}

	@Override
	public boolean retryRequest(
			final HttpRequest request,
			final IOException exception,
			final int execCount,
			final HttpContext context) {
		LOG.warn("Retry #{}.", execCount);
		return super.retryRequest(request, exception, execCount, context);
	}

}
