package org.openstack4j.openstack.telemetry.domain;

import org.openstack4j.model.common.builder.BasicResourceBuilder;
import org.openstack4j.model.telemetry.gnocchi.ArchivePolicy;
import org.openstack4j.model.telemetry.gnocchi.Metrics;
import org.openstack4j.model.telemetry.gnocchi.builder.MetricsBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

public class GnocchiMetrics implements Metrics {

    private static final long serialVersionUID = 1L;

    @JsonProperty("archive_policy")
    private ArchivePolicy archivePolicy;

    @JsonProperty("created_by_project_id")
    private String createdByProjectId;

    @JsonProperty("created_by_user_id")
    private String createdByUserId;

    private String id;

    private String name;

    @JsonProperty("resource_id")
    private String resourceId;

    private String unit;

    public static MetricsConcreteBuilder builder() {
        return new MetricsConcreteBuilder();
    }

    @Override
    public ArchivePolicy getArchivePolicy() {
        return archivePolicy;
    }

    @Override
    public void setArchivePolicy(final ArchivePolicy archivePolicy) {
        this.archivePolicy = archivePolicy;
    }

    @Override
    public String getCreatedByProjectId() {
        return createdByProjectId;
    }

    @Override
    public void setCreatedByProjectId(final String createdByProjectId) {
        this.createdByProjectId = createdByProjectId;
    }

    @Override
    public String getCreatedByUserId() {
        return createdByUserId;
    }

    @Override
    public void setCreatedByUserId(final String createdByUserId) {
        this.createdByUserId = createdByUserId;
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
    public String getName() {
        return name;
    }

    @Override
    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String getResourceId() {
        return resourceId;
    }

    @Override
    public void setResourceId(final String resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public String getUnit() {
        return unit;
    }

    @Override
    public void setUnit(final String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues().add("archivePolicy", archivePolicy)
                .add("createdByProjectId", createdByProjectId).add("", createdByUserId).add("id", id).add("name", name)
                .add("resourceId", resourceId).add("unit", unit).toString();
    }

    @Override
    public MetricsBuilder toBuilder() {
        return new MetricsConcreteBuilder(this);
    }

    /**
     * Builder to build {@link GnocchiMetrics}.
     */
    public static final class MetricsConcreteBuilder extends BasicResourceBuilder<Metrics, MetricsConcreteBuilder>
            implements MetricsBuilder {
        private GnocchiMetrics m;

        public MetricsConcreteBuilder(final GnocchiMetrics gnocchiMetrics) {
            m = gnocchiMetrics;
        }

        public MetricsConcreteBuilder() {
            this(new GnocchiMetrics());
        }

        @Override
        public MetricsBuilder archivePolicy(final ArchivePolicy archivePolicy) {
            m.archivePolicy = archivePolicy;
            return this;
        }

        @Override
        public MetricsBuilder createdByProjectId(final String createdByProjectId) {
            m.createdByProjectId = createdByProjectId;
            return this;
        }

        @Override
        public MetricsBuilder createdByUserId(final String createdByUserId) {
            m.createdByUserId = createdByUserId;
            return this;
        }

        @Override
        public MetricsBuilder resourceId(final String resourceId) {
            m.resourceId = resourceId;
            return this;
        }

        @Override
        public MetricsBuilder unit(final String unit) {
            m.unit = unit;
            return this;
        }

        @Override
        protected Metrics reference() {
            return m;
        }

        @Override
        public Metrics build() {
            return m;
        }

        @Override
        public MetricsBuilder from(final Metrics in) {
            m = (GnocchiMetrics) in;
            return this;
        }

    }
}
