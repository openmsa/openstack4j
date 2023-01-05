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
package org.openstack4j.openstack.telemetry.domain;

import org.openstack4j.model.common.builder.BasicResourceBuilder;
import org.openstack4j.model.telemetry.gnocchi.MetricCreate;
import org.openstack4j.model.telemetry.gnocchi.builder.GnocchiCreateMetricBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GnocchiCreateMetric implements MetricCreate {
    /** Serial. */
    private static final long serialVersionUID = 1L;

    private String id;

    @JsonProperty("archive_policy_name")
    private String archivePolicyName;

    @JsonProperty("resource_id")
    private String resourceId;

    private String name;

    private String unit;

    public static GnocchiCreateMetricBuilder builder() {
        return new ConcreteGnocchiCreateMetricBuilder();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(final String id) {
        this.id = id;
    }

    @Override
    public GnocchiCreateMetricBuilder toBuilder() {
        return new ConcreteGnocchiCreateMetricBuilder(this);
    }

    @Override
    public String getArchivePolicyName() {
        return archivePolicyName;
    }

    public void setArchivePolicyName(final String archivePolicyName) {
        this.archivePolicyName = archivePolicyName;
    }

    @Override
    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(final String resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String getUnit() {
        return unit;
    }

    public void setUnit(final String unit) {
        this.unit = unit;
    }

    public static class ConcreteGnocchiCreateMetricBuilder
            extends BasicResourceBuilder<MetricCreate, ConcreteGnocchiCreateMetricBuilder>
            implements GnocchiCreateMetricBuilder {
        private GnocchiCreateMetric m;

        public ConcreteGnocchiCreateMetricBuilder(final GnocchiCreateMetric gnocchiMetrics) {
            m = gnocchiMetrics;
        }

        public ConcreteGnocchiCreateMetricBuilder() {
            this(new GnocchiCreateMetric());
        }

        @Override
        public ConcreteGnocchiCreateMetricBuilder archivePolicyName(final String archivePolicyName) {
            m.archivePolicyName = archivePolicyName;
            return this;
        }

        @Override
        public ConcreteGnocchiCreateMetricBuilder resourceId(final String resourceId) {
            m.resourceId = resourceId;
            return this;
        }

        @Override
        public ConcreteGnocchiCreateMetricBuilder name(final String name) {
            m.name = name;
            return this;
        }

        @Override
        public ConcreteGnocchiCreateMetricBuilder unit(final String unit) {
            m.unit = unit;
            return this;
        }

        @Override
        protected MetricCreate reference() {
            return m;
        }

        @Override
        public MetricCreate build() {
            return m;
        }

        @Override
        public GnocchiCreateMetricBuilder from(final MetricCreate in) {
            m = (GnocchiCreateMetric) in;
            return this;
        }
    }

}
