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
package org.openstack4j.openstack.telemetry.internal.gnocchi;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.openstack4j.api.telemetry.gnocchi.MetricsService;
import org.openstack4j.model.telemetry.gnocchi.MetricCreate;
import org.openstack4j.model.telemetry.gnocchi.Metrics;
import org.openstack4j.openstack.telemetry.domain.GnocchiMetrics;

public class MetricsServiceImpl extends BaseGnocchiServices implements MetricsService {

    @Override
    public Metrics create(final MetricCreate metrics) {
        checkNotNull(metrics);
        return post(GnocchiMetrics.class, uri("/metric")).entity((metrics)).execute();
    }

    @Override
    public Metrics read(final String id) {
        checkNotNull(id);
        return get(GnocchiMetrics.class, uri("/metric/%s", id)).execute();
    }

    @Override
    public List<? extends Metrics> list() {
        final GnocchiMetrics[] alarms = get(GnocchiMetrics[].class, uri("/metric")).execute();
        return wrapList(alarms);
    }

    @Override
    public void delete(final String id) {
        checkNotNull(id);
        super.delete(Void.class, uri("metric/%s", id)).execute();
    }

}
