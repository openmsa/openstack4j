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

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.openstack4j.core.transport.ExecutionOptions;
import org.openstack4j.core.transport.HttpResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class HttpResponseImpl implements HttpResponse {
	ResponseEntity<?> resp;

	public HttpResponseImpl(final ResponseEntity<?> resp) {
		this.resp = resp;
	}

	@Override
	public void close() throws IOException {
		//
	}

	@Override
	public <T> T getEntity(final Class<T> returnType) {
		return (T) resp.getBody();
	}

	@Override
	public <T> T getEntity(final Class<T> returnType, final ExecutionOptions<T> options) {
		return (T) resp.getBody();
	}

	@Override
	public <T> T readEntity(final Class<T> typeToReadAs) {
		return (T) resp.getBody();
	}

	@Override
	public int getStatus() {
		return resp.getStatusCode().value();
	}

	@Override
	public String getContentType() {
		return Optional.ofNullable(resp.getHeaders()).map(HttpHeaders::getContentType).map(MediaType::toString).orElse(null);
	}

	@Override
	public String getStatusMessage() {
		return "";
	}

	@Override
	public InputStream getInputStream() {
		return null;
	}

	@Override
	public String header(final String name) {
		final List<String> r = resp.getHeaders().getOrEmpty(name);
		if (r.isEmpty()) {
			return null;
		}
		return r.get(0);
	}

	@Override
	public Map<String, String> headers() {
		// TODO Auto-generated method stub
		return Map.of();
	}

}
