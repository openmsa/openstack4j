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

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.openstack4j.api.telemetry.gnocchi.MeasuresService;
import org.openstack4j.model.telemetry.gnocchi.Measure;
import org.openstack4j.model.telemetry.gnocchi.MeasureFilter;
import org.openstack4j.openstack.telemetry.domain.GnocchiMeasure;

public class MeasuresServiceImpl extends BaseGnocchiServices implements MeasuresService {

    @Override
    public List<? extends Measure> read(final String id) {
        return read(id, null);
    }

    @Override
    public List<? extends Measure> read(final String id, final MeasureFilter filter) {
        Objects.requireNonNull(id);
        final Invocation<Object[][]> inv = get(Object[][].class, uri("/metric/%s/measures", id));
        if (filter != null) {
            inv.params(filter.getConstraints());
        }
        final Object[][] measuresRaw = inv.execute();
        return Arrays.stream(measuresRaw).map(this::newMeasure).collect(Collectors.toList());
    }

    private GnocchiMeasure newMeasure(final Object[] obj) {
        final String strDate = (String) obj[0];
        final Double gran = (Double) obj[1];
        final Double value = (Double) obj[2];
        return new GnocchiMeasure(OffsetDateTime.parse(strDate), gran.intValue(), value);
    }
}
