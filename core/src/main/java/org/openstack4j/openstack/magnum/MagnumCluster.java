package org.openstack4j.openstack.magnum;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.util.ToStringHelper;
import org.openstack4j.model.magnum.Cluster;
import org.openstack4j.model.magnum.ClusterBuilder;
import org.openstack4j.openstack.common.GenericLink;
import org.openstack4j.openstack.common.ListResult;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MagnumCluster implements Cluster {
    private static final long serialVersionUID = 1L;
    @JsonProperty("status")
    private String status;
    @JsonProperty("cluster_template_id")
    private String clusterTemplateId;
    @JsonProperty("uuid")
    private String uuid;
    @JsonProperty("links")
    private List<GenericLink> links;
    @JsonProperty("stack_id")
    private String stackId;
    @JsonProperty("master_count")
    private Integer masterCount;
    @JsonProperty("create_timeout")
    private Integer createTimeout;
    @JsonProperty("node_count")
    private Integer nodeCount;
    @JsonProperty("discovery_url")
    private String discoveryUrl;
    @JsonProperty("keypair")
    private String keypair;
    @JsonProperty("name")
    private String name;
    @JsonProperty("master_addresses")
    private List<String> masterAddresses;
    @JsonProperty("api_address")
    private String apiAddress;
    @JsonProperty("fixed_network")
    private String fixedNetwork;
    @JsonProperty("fixed_subnet")
    private String fixedSubnet;
    @JsonProperty("coe_version")
    private String coesVersion;
    @JsonProperty("node_addresses")
    private List<String> nodeAddresses;
    @JsonProperty("floating_ip_enabled")
    private Boolean floatingIpEnable;
    @JsonProperty("master_lb_enabled")
    private Boolean masterLbEnable;

    public static ClusterBuilder builder() {
        return new ClusterConcreteBuilder();
    }

    @Override
    public ClusterBuilder toBuilder() {
        return new ClusterConcreteBuilder(this);
    }

    public String getStatus() {
        return status;
    }

    public String getClusterTemplateId() {
        return clusterTemplateId;
    }

    public String getUuid() {
        return uuid;
    }

    public List<GenericLink> getLinks() {
        return links;
    }

    public String getStackId() {
        return stackId;
    }

    public Integer getMasterCount() {
        return masterCount;
    }

    public Integer getCreateTimeout() {
        return createTimeout;
    }

    public Integer getNodeCount() {
        return nodeCount;
    }

    public String getDiscoveryUrl() {
        return discoveryUrl;
    }

    public String getKeypair() {
        return keypair;
    }

    public String getName() {
        return name;
    }

    @Override
    public List<String> getMasterAddresses() {
        return masterAddresses;
    }

    @Override
    public String getApiAddress() {
        return apiAddress;
    }

    @Override
    public String getFixedNetwork() {
        return fixedNetwork;
    }

    @Override
    public String getFixedSubnet() {
        return fixedSubnet;
    }

    @Override
    public String getCoesVersion() {
        return coesVersion;
    }

    @Override
    public List<String> getNodeAddresses() {
        return nodeAddresses;
    }

    @Override
    public Boolean getFloatingIpEnable() {
        return floatingIpEnable;
    }

    @Override
    public Boolean getMasterLbEnable() {
        return masterLbEnable;
    }

    @Override
    public String toString() {
        return new ToStringHelper(this).add("status", status)
                .add("clusterTemplateId", clusterTemplateId).add("uuid", uuid).add("links", links)
                .add("stackId", stackId).add("masterCount", masterCount).add("createTimeout", createTimeout)
                .add("nodeCount", nodeCount).add("discoveryUrl", discoveryUrl).add("keypair", keypair).add("name", name)
                .add("masterAddresses", masterAddresses).add("fixedNetwork", fixedNetwork).add("fixedSubnet", fixedSubnet)
                .add("coeVersion", coesVersion).add("nodeAddresses", nodeAddresses).add("floatingIpEnable", floatingIpEnable)
                .add("masterLbEnable", masterLbEnable)
                .toString();
    }

    /**
     * Concrete builder containing MagnumCluster as model
     */
    public static class ClusterConcreteBuilder implements ClusterBuilder {
        MagnumCluster model;

        public ClusterConcreteBuilder() {
            this(new MagnumCluster());
        }

        public ClusterConcreteBuilder(MagnumCluster model) {
            this.model = model;
        }

        @Override
        public Cluster build() {
            return model;
        }

        @Override
        public ClusterBuilder from(Cluster in) {
            if (in != null)
                this.model = (MagnumCluster) in;
            return this;
        }

        @Override
        public ClusterBuilder status(String status) {
            model.status = status;
            return this;
        }

        @Override
        public ClusterBuilder clusterTemplateId(String clusterTemplateId) {
            model.clusterTemplateId = clusterTemplateId;
            return this;
        }

        @Override
        public ClusterBuilder uuid(String uuid) {
            model.uuid = uuid;
            return this;
        }

        @Override
        public ClusterBuilder links(List<GenericLink> links) {
            model.links = links;
            return this;
        }

        @Override
        public ClusterBuilder stackId(String stackId) {
            model.stackId = stackId;
            return this;
        }

        @Override
        public ClusterBuilder masterCount(Integer masterCount) {
            model.masterCount = masterCount;
            return this;
        }

        @Override
        public ClusterBuilder createTimeout(Integer createTimeout) {
            model.createTimeout = createTimeout;
            return this;
        }

        @Override
        public ClusterBuilder nodeCount(Integer nodeCount) {
            model.nodeCount = nodeCount;
            return this;
        }

        @Override
        public ClusterBuilder discoveryUrl(String discoveryUrl) {
            model.discoveryUrl = discoveryUrl;
            return this;
        }

        @Override
        public ClusterBuilder keypair(String keypair) {
            model.keypair = keypair;
            return this;
        }

        @Override
        public ClusterBuilder name(String name) {
            model.name = name;
            return this;
        }

        @Override
        public ClusterBuilder fixedNetwork(String fixedNetwork) {
            model.fixedNetwork = fixedNetwork;
            return this;
        }

        @Override
        public ClusterBuilder fixedSubnet(String fixedSubnet) {
            model.fixedSubnet = fixedSubnet;
            return this;
        }

        @Override
        public ClusterBuilder floatingIpEnable(Boolean floating) {
            model.floatingIpEnable = floating;
            return this;
        }

        @Override
        public ClusterBuilder masterLbEnable(Boolean masterLb) {
            model.masterLbEnable = masterLb;
            return this;
        }
    }

    public static class Clusters extends ListResult<MagnumCluster> {
        private static final long serialVersionUID = 1L;
        @JsonProperty("clusters")
        private List<MagnumCluster> list;

        @Override
        public List<MagnumCluster> value() {
            return list;
        }
    }
}
