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
package org.openstack4j.model.telemetry.gnocchi;

import java.time.OffsetDateTime;
import java.util.Date;

import org.openstack4j.model.common.BaseFilter;
import org.openstack4j.openstack.internal.Parser;

public class MeasureFilter extends BaseFilter {

    public static MeasureFilter create() {
        return new MeasureFilter();
    }

    public MeasureFilter resample(final Integer resample) {
        filter("resample", resample);
        return this;
    }

    public MeasureFilter refresh(final Boolean refresh) {
        filter("refresh", refresh);
        return this;
    }

    public MeasureFilter resourceId(final String resourceId) {
        filter("resource_id", resourceId);
        return this;
    }

    public MeasureFilter granularity(final Integer granularity) {
        filter("granularity", granularity);
        return this;
    }

    public MeasureFilter aggregation(final String aggregation) {
        filter("aggregation", aggregation);
        return this;
    }

    public MeasureFilter start(final OffsetDateTime offsetDateTime) {
        filter("start", offsetDateTime.toString());
        return this;
    }

    public MeasureFilter end(final Date end) {
        filter("end", Parser.toISO8601DateFormat(end));
        return this;
    }
}
