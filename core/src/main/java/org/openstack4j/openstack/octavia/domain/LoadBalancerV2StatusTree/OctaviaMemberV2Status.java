package org.openstack4j.openstack.octavia.domain.LoadBalancerV2StatusTree;

import org.openstack4j.model.octavia.status.MemberV2Status;
import org.openstack4j.util.ToStringHelper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * An object to hold status of lbaas v2 member
 *
 * @author wei
 */
@JsonRootName("members")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OctaviaMemberV2Status extends Status implements MemberV2Status {

    @JsonProperty("protocol_port")
    private Integer protocolPort;

    @JsonProperty("address")
    private String address;

    public Integer getProtocolPort() {
        return protocolPort;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return new ToStringHelper(this)
                .add("id", id)
                .add("protocolPort", protocolPort)
                .add("address", address)
                .add("operatingStatus", operatingStatus)
                .add("provisioningStatus", provisioningStatus)
                .toString();
    }
}
