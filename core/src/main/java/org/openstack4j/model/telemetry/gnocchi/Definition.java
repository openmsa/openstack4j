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

import com.google.common.base.MoreObjects;

public class Definition {
    private String granularity;

    private int points;

    private String timespan;

    public String getGranularity() {
        return granularity;
    }

    public void setGranularity(final String granularity) {
        this.granularity = granularity;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(final int points) {
        this.points = points;
    }

    public String getTimespan() {
        return timespan;
    }

    public void setTimespan(final String timespan) {
        this.timespan = timespan;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues().add("granularity", granularity).add("points", points)
                .add("timestamp", timespan).toString();
    }

}
