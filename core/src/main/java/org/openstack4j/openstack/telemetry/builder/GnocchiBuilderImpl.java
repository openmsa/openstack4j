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
package org.openstack4j.openstack.telemetry.builder;

import org.openstack4j.model.telemetry.gnocchi.builder.GnocchiBuilder;
import org.openstack4j.model.telemetry.gnocchi.builder.GnocchiCreateMetricBuilder;
import org.openstack4j.model.telemetry.gnocchi.builder.ResourceBuilder;
import org.openstack4j.openstack.telemetry.domain.GnocchiCreateMetric;
import org.openstack4j.openstack.telemetry.domain.GnocchiResource;

public class GnocchiBuilderImpl implements GnocchiBuilder {

    @Override
    public GnocchiCreateMetricBuilder metric() {
        return GnocchiCreateMetric.builder();
    }

    @Override
    public ResourceBuilder resource() {
        return GnocchiResource.build();
    }

}
