/**
 *     Copyright (C) 2019-2020 Ubiqube.
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
package org.openstack4j.model.telemetry.gnocchi.builder;

import java.util.Date;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.telemetry.gnocchi.Resource;

public interface ResourceBuilder extends Builder<ResourceBuilder, Resource> {

    ResourceBuilder createdByProjectId(final String createdByProjectId);

    ResourceBuilder createdByUser(final String createdByUser);

    ResourceBuilder creator(final String creator);

    ResourceBuilder name(final String name);

    ResourceBuilder endedAt(final Date endedAt);

    ResourceBuilder id(final String id);

    ResourceBuilder launchedAt(final Date launchedAt);

    ResourceBuilder originalResourceId(final String originalResourceId);

    ResourceBuilder projectId(final String projectId);

    ResourceBuilder revisionEnd(final Date revisionEnd);

    ResourceBuilder revisionStart(final Date revisionStart);

    ResourceBuilder startedAt(final Date startedAt);

    ResourceBuilder type(final String type);

    ResourceBuilder userId(final String userId);

    ResourceBuilder additionalProperty(final String key, final String value);

}
