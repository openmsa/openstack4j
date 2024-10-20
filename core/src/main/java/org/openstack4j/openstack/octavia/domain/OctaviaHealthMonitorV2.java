package org.openstack4j.openstack.octavia.domain;

import java.util.List;

import org.openstack4j.model.octavia.HealthMonitorType;
import org.openstack4j.model.octavia.HealthMonitorV2;
import org.openstack4j.model.octavia.builder.HealthMonitorV2Builder;
import org.openstack4j.openstack.common.ListResult;
import org.openstack4j.util.ToStringHelper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * A lbaas v2 health monitor entity
 *
 * @author wei
 */
@JsonRootName("healthmonitor")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OctaviaHealthMonitorV2 implements HealthMonitorV2 {
    private String id;

    @JsonProperty("project_id")
    private String projectId;

    private HealthMonitorType type;
    private Integer delay;
    private Integer timeout;

    /**
     * Number of allowed connection failures before changing the status of the member to INACTIVE. A valid value is from 1 to 10.
     */
    @JsonProperty("max_retries")
    private Integer maxRetries;

    /**
     * Number of allowed connection failures before changing the status of the member to INACTIVE. A valid value is from 1 to 10.
     */
    @JsonProperty("max_retries_down")
    private Integer maxRetriesDown;

    /**
     * The HTTP method that the monitor uses for requests.
     */
    @JsonProperty("http_method")
    private String httpMethod;

    /**
     * URL
     */
    @JsonProperty("url_path")
    private String urlPath;

    /**
     * default 200
     */
    @JsonProperty("expected_codes")
    private String expectedCodes;

    /**
     * The administrative state of the health monitor, which is up (true) or down (false)
     */
    @JsonProperty("admin_state_up")
    private boolean adminStateUp = true;

    private List<ListItem> pools;

    @JsonProperty("pool_id")
    private String poolId;

    /**
     * @return HealthMonitorV2Builder
     */
    public static HealthMonitorV2Builder builder() {
        return new HealthMonitorV2Concretebuilder();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getProjectId() {
        return projectId;
    }

    @Override
    public HealthMonitorType getType() {
        return type;
    }

    @Override
    public Integer getDelay() {
        return delay;
    }

    @Override
    public Integer getTimeout() {
        return timeout;
    }

    @Override
    public Integer getMaxRetries() {
        return maxRetries;
    }

    @Override
    public Integer getMaxRetriesDown() {
        return maxRetriesDown;
    }

    @Override
    public String getHttpMethod() {
        return httpMethod;
    }

    @Override
    public String getUrlPath() {
        return urlPath;
    }

    @Override
    public String getExpectedCodes() {
        return expectedCodes;
    }

    @Override
    public boolean isAdminStateUp() {
        return adminStateUp;
    }

    @Override
    public List<ListItem> getPools() {
        return pools;
    }

    /**
     * wrap this healthMonitorV2 to a builder
     *
     * @return HealthMonitorV2Builder
     */
    @Override
    public HealthMonitorV2Builder toBuilder() {
        return new HealthMonitorV2Concretebuilder(this);
    }

    @Override
    public String toString() {
        return new ToStringHelper(this)
                .add("id", id)
                .add("projectId", projectId)
                .add("type", type)
                .add("delay", delay)
                .add("timeout", timeout)
                .add("maxRetries", maxRetries)
                .add("maxRetriesDown", maxRetriesDown)
                .add("httpMethod", httpMethod)
                .add("urlPath", urlPath)
                .add("expectedCodes", expectedCodes)
                .add("adminStateUp", adminStateUp)
                .add("pools", pools)
                .add("poolId", poolId)
                .toString();
    }

    public static class HealthMonitorsV2 extends ListResult<OctaviaHealthMonitorV2> {

        @JsonProperty("healthmonitors")
        List<OctaviaHealthMonitorV2> healthMonitors;

        @Override
        public List<OctaviaHealthMonitorV2> value() {
            return healthMonitors;
        }

        @Override
        public String toString() {
            return new ToStringHelper(this)
                    .add("healthMonitors", healthMonitors).toString();
        }
    }

    public static class HealthMonitorV2Concretebuilder implements HealthMonitorV2Builder {
        OctaviaHealthMonitorV2 m;

        public HealthMonitorV2Concretebuilder() {
            this(new OctaviaHealthMonitorV2());
        }

        public HealthMonitorV2Concretebuilder(OctaviaHealthMonitorV2 m) {
            this.m = m;
        }

        @Override
        public HealthMonitorV2 build() {
            return m;
        }

        @Override
        public HealthMonitorV2Builder from(HealthMonitorV2 in) {
            this.m = (OctaviaHealthMonitorV2) in;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public HealthMonitorV2Builder projectId(String projectId) {
            m.projectId = projectId;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public HealthMonitorV2Builder type(HealthMonitorType type) {
            m.type = type;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public HealthMonitorV2Builder delay(Integer delay) {
            m.delay = delay;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public HealthMonitorV2Builder timeout(Integer timeout) {
            m.timeout = timeout;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public HealthMonitorV2Builder maxRetries(Integer maxRetries) {
            m.maxRetries = maxRetries;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public HealthMonitorV2Builder maxRetriesDown(Integer maxRetriesDown) {
            m.maxRetriesDown = maxRetriesDown;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public HealthMonitorV2Builder urlPath(String urlPath) {
            m.urlPath = urlPath;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public HealthMonitorV2Builder expectedCodes(String expectedCodes) {
            m.expectedCodes = expectedCodes;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public HealthMonitorV2Builder httpMethod(String httpMethod) {
            m.httpMethod = httpMethod;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public HealthMonitorV2Builder adminStateUp(boolean adminStateUp) {
            m.adminStateUp = adminStateUp;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public HealthMonitorV2Builder poolId(String poolId) {
            m.poolId = poolId;
            return this;
        }
    }
}
