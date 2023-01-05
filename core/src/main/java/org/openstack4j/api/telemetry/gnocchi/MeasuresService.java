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
package org.openstack4j.api.telemetry.gnocchi;

import java.util.List;

import org.openstack4j.model.telemetry.gnocchi.Measure;
import org.openstack4j.model.telemetry.gnocchi.MeasureFilter;

public interface MeasuresService {

    // void push(String id, Measure measure);

    // void push(Map<String, List<? extends Measure>> batch);

    List<? extends Measure> read(String id);

    List<? extends Measure> read(final String id, final MeasureFilter filter);
}
