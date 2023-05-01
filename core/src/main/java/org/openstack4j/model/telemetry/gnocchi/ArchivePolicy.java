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

import java.util.List;

import org.openstack4j.api.MoreObjects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ArchivePolicy {

    @JsonProperty("aggregation_methods")
    private List<String> aggregationMethods;

    @JsonProperty("back_window")
    private int backWindow;

    private List<? extends Definition> definition;

    private String name;

    public List<String> getAggregationMethods() {
        return aggregationMethods;
    }

    public void setAggregationMethods(final List<String> aggregationMethods) {
        this.aggregationMethods = aggregationMethods;
    }

    public int getBackWindow() {
        return backWindow;
    }

    public void setBackWindow(final int backWindow) {
        this.backWindow = backWindow;
    }

    public List<? extends Definition> getDefinition() {
        return definition;
    }

    public void setDefinition(final List<? extends Definition> definition) {
        this.definition = definition;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues().add("aggregationMethods", aggregationMethods)
                .add("backWindow", backWindow).add("definition", definition).add("name", name).toString();
    }

}
