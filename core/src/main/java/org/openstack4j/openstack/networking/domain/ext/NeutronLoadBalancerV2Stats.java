package org.openstack4j.openstack.networking.domain.ext;

import org.openstack4j.model.network.ext.LoadBalancerV2Stats;
import org.openstack4j.util.ToStringHelper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * The statistics about an lbaas v2 loadbalancer
 *
 * @author emjburns
 */
@JsonRootName("stats")
public class NeutronLoadBalancerV2Stats implements LoadBalancerV2Stats {
    private static final long serialVersionUID = 1L;
    @JsonProperty("bytes_in")
    private Long bytesIn;

    @JsonProperty("bytes_out")
    private Long bytesOut;

    @JsonProperty("total_connections")
    private Integer totalConnections;

    @JsonProperty("active_connections")
    private Integer activeConnections;

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getBytesIn() {
        return bytesIn;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getBytesOut() {
        return bytesOut;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getTotalConnections() {
        return totalConnections;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getActiveConnections() {
        return activeConnections;
    }

    @Override
    public String toString() {
        return new ToStringHelper(this)
                .add("bytesIn", bytesIn)
                .add("bytesOut", bytesOut)
                .add("totalConnections", totalConnections)
                .add("activeConnections", activeConnections)
                .toString();
    }
}
