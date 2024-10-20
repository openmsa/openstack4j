package org.openstack4j.openstack.octavia.domain.LoadBalancerV2StatusTree;

import java.util.List;

import org.openstack4j.model.octavia.status.ListenerV2Status;
import org.openstack4j.model.octavia.status.LoadBalancerV2Status;
import org.openstack4j.util.ToStringHelper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * An object to hold status of lbaas v2 loadbalancer
 *
 * @author wei
 */
@JsonRootName("loadbalancer")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OctaviaLoadBalancerV2Status extends Status implements LoadBalancerV2Status {
    @JsonProperty("name")
    private String name;

    @JsonProperty("listeners")
    private List<ListenerV2Status> listenerStatuses;

    @Override
    public List<ListenerV2Status> getListenerStatuses() {
        return listenerStatuses;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return new ToStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("listenerStatuses", listenerStatuses)
                .add("operatingStatus", operatingStatus)
                .add("provisioningStatus", provisioningStatus)
                .toString();
    }

}
