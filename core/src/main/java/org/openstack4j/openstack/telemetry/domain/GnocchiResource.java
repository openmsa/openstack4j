package org.openstack4j.openstack.telemetry.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.openstack4j.api.MoreObjects;
import org.openstack4j.model.common.builder.BasicResourceBuilder;
import org.openstack4j.model.telemetry.gnocchi.Resource;
import org.openstack4j.model.telemetry.gnocchi.builder.ResourceBuilder;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GnocchiResource implements Resource {
    private static final long serialVersionUID = 1L;

    @JsonProperty("created_by_project_id")
    private String createdByProjectId;

    @JsonProperty("created_by_user_id")
    private String createdByUser;

    private String creator;

    @JsonProperty("display_name")
    private String name;

    @JsonProperty("ended_at")
    private Date endedAt;

    private String id;

    @JsonProperty("launched_at")
    private Date launchedAt;

    private GnocchiMetric metrics;

    @JsonProperty("original_resource_id")
    private String originalResourceId;

    @JsonProperty("project_id")
    private String projectId;

    @JsonProperty("revision_end")
    private Date revisionEnd;

    @JsonProperty("revision_start")
    private Date revisionStart;

    @JsonProperty("server_group")
    private String serverGroup;

    @JsonProperty("started_at")
    private Date startedAt;

    private String type;

    @JsonProperty("user_id")
    private String userId;

    private final Map<String, String> additionalProperties = new HashMap<>();

    public static ResourceBuilder build() {
        return new ConcreteResourceBuilder();
    }

    @Override
    public String getCreatedByProjectId() {
        return createdByProjectId;
    }

    public void setCreatedByProjectId(final String createdByProjectId) {
        this.createdByProjectId = createdByProjectId;
    }

    @Override
    public String getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(final String createdByUser) {
        this.createdByUser = createdByUser;
    }

    @Override
    public String getCreator() {
        return creator;
    }

    public void setCreator(final String creator) {
        this.creator = creator;
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
    public Date getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(final Date endedAt) {
        this.endedAt = endedAt;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(final String id) {
        this.id = id;
    }

    public Date getLaunchedAt() {
        return launchedAt;
    }

    public void setLaunchedAt(final Date launchedAt) {
        this.launchedAt = launchedAt;
    }

    @Override
    public String getMetric(final String key) {
        return metrics.getKeyPair().get(key);
    }

    @Override
    public Map<String, String> getMetrics() {
        return metrics.getKeyPair();
    }

    public void setMetrics(final GnocchiMetric metrics) {
        this.metrics = metrics;
    }

    @Override
    public String getOriginalResourceId() {
        return originalResourceId;
    }

    public void setOriginalResourceId(final String originalResourceId) {
        this.originalResourceId = originalResourceId;
    }

    @Override
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(final String projectId) {
        this.projectId = projectId;
    }

    @Override
    public Date getRevisionEnd() {
        return revisionEnd;
    }

    public void setRevisionEnd(final Date revisionEnd) {
        this.revisionEnd = revisionEnd;
    }

    @Override
    public Date getRevisionStart() {
        return revisionStart;
    }

    public void setRevisionStart(final Date revisionStart) {
        this.revisionStart = revisionStart;
    }

    @Override
    public String getServerGroup() {
        return serverGroup;
    }

    public void setServerGroup(final String serverGroup) {
        this.serverGroup = serverGroup;
    }

    @Override
    public Date getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(final Date startedAt) {
        this.startedAt = startedAt;
    }

    @Override
    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    public void setUserId(final String userId) {
        this.userId = userId;
    }

    @JsonAnyGetter
    public Map<String, String> getAdditionalProperties() {
        return additionalProperties;
    }

    @Override
    public String getAdditionalPropertyValue(final String key) {
        return additionalProperties.get(key);
    }

    @JsonAnySetter
    public void setAdditionalProperty(final String key, final String value) {
        additionalProperties.put(key, value);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues().add("id", id)
                .add("createdByProjectId", createdByProjectId).add("createdByUser", createdByUser)
                .add("creator", creator).add("endedAt", endedAt).add("metrics", metrics).add("launchedAt", launchedAt)
                .add("originalResourceId", originalResourceId).add("projectId", projectId)
                .add("revisionEnd", revisionEnd).add("revisionStart", revisionStart).add("serverGroup", serverGroup)
                .add("startedAt", startedAt).add("type", type).add("userId", userId)
                .add("additionalProperties", additionalProperties).toString();
    }

    @Override
    public ResourceBuilder toBuilder() {
        return new ConcreteResourceBuilder(this);
    }

    /**
     * ConcreteResourceBuilder to build {@link GnocchiResource}.
     */
    public static final class ConcreteResourceBuilder extends BasicResourceBuilder<Resource, ConcreteResourceBuilder>
            implements ResourceBuilder {
        private GnocchiResource m;

        public ConcreteResourceBuilder(final GnocchiResource gnocchiResource) {
            m = gnocchiResource;
        }

        public ConcreteResourceBuilder() {
            this(new GnocchiResource());
        }

        @Override
        protected Resource reference() {
            return m;
        }

        @Override
        public ResourceBuilder createdByProjectId(final String createdByProjectId) {
            m.createdByProjectId = createdByProjectId;
            return this;
        }

        @Override
        public ResourceBuilder createdByUser(final String createdByUser) {
            m.createdByUser = createdByUser;
            return this;
        }

        @Override
        public ResourceBuilder creator(final String creator) {
            m.creator = creator;
            return this;
        }

        @Override
        public ResourceBuilder endedAt(final Date endedAt) {
            m.endedAt = endedAt;
            return this;
        }

        @Override
        public ResourceBuilder launchedAt(final Date launchedAt) {
            m.launchedAt = launchedAt;
            return this;
        }

        @Override
        public ResourceBuilder originalResourceId(final String originalResourceId) {
            m.originalResourceId = originalResourceId;
            return this;
        }

        @Override
        public ResourceBuilder projectId(final String projectId) {
            m.projectId = projectId;
            return this;
        }

        @Override
        public ResourceBuilder revisionEnd(final Date revisionEnd) {
            m.revisionEnd = revisionEnd;
            return this;
        }

        @Override
        public ResourceBuilder revisionStart(final Date revisionStart) {
            m.revisionStart = revisionStart;
            return this;
        }

        @Override
        public ResourceBuilder startedAt(final Date startedAt) {
            m.startedAt = startedAt;
            return this;
        }

        @Override
        public ResourceBuilder type(final String type) {
            m.type = type;
            return this;
        }

        @Override
        public ResourceBuilder userId(final String userId) {
            m.userId = userId;
            return this;
        }

        @Override
        public Resource build() {
            return m;
        }

        @Override
        public ResourceBuilder additionalProperty(final String key, final String value) {
            m.setAdditionalProperty(key, value);
            return this;
        }

        @Override
        public ResourceBuilder from(final Resource in) {
            m = (GnocchiResource) in;
            return this;
        }
    }
}
