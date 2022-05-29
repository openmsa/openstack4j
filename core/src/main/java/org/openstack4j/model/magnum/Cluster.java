package org.openstack4j.model.magnum;

import java.util.List;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.openstack.common.GenericLink;

public interface Cluster extends ModelEntity, Buildable<ClusterBuilder> {
    /**
     * Gets status
     *
     * @return status
     */
    String getStatus();

    /**
     * Gets clusterTemplateId
     *
     * @return clusterTemplateId
     */
    String getClusterTemplateId();

    /**
     * Gets uuid
     *
     * @return uuid
     */
    String getUuid();

    /**
     * Gets links
     *
     * @return links
     */
    List<GenericLink> getLinks();

    /**
     * Gets stackId
     *
     * @return stackId
     */
    String getStackId();

    /**
     * Gets masterCount
     *
     * @return masterCount
     */
    Integer getMasterCount();

    /**
     * Gets createTimeout
     *
     * @return createTimeout
     */
    Integer getCreateTimeout();

    /**
     * Gets nodeCount
     *
     * @return nodeCount
     */
    Integer getNodeCount();

    /**
     * Gets discoveryUrl
     *
     * @return discoveryUrl
     */
    String getDiscoveryUrl();

    /**
     * Gets keypair
     *
     * @return keypair
     */
    String getKeypair();

    /**
     * Gets name
     *
     * @return name
     */
    String getName();

    /**
     * List of floating IP of all master nodes.
     * 
     * @return List of Ips.
     */
    List<String> getMasterAddresses();

    /**
     * The endpoint URL of COE API exposed to end-users.
     * 
     * @return The API address.
     */
    String getApiAddress();

    /**
     * The name or network ID of a Neutron network to provide connectivity to the internal network for the bay/cluster.
     * 
     * @return The network ID.
     */
    String getFixedNetwork();

    /**
     * Fixed subnet that are using to allocate network address for nodes in bay/cluster.
     * 
     * @return The subnetwork ID.
     */
    String getFixedSubnet();

    /**
     * Version info of chosen COE in bay/cluster for helping client in picking the right version of client.
     * 
     * @return The COE version.
     */
    String getCoesVersion();

    /**
     * List of floating IP of all servers that serve as node.
     * 
     * @return List of nodes addresses.
     */
    List<String> getNodeAddresses();

    /**
     * Whether enable or not using the floating IP of cloud provider. Some cloud providers used floating IP, some used public IP, thus Magnum provide this option for specifying the choice of using floating IP. If it’s not set, the value of floating_ip_enabled in template will be used.
     * 
     * @return Status of floating IP.
     */
    Boolean getFloatingIpEnable();

    /**
     * Since multiple masters may exist in a bay/cluster, a Neutron load balancer is created to provide the API endpoint for the bay/cluster and to direct requests to the masters. In some cases, such as when the LBaaS service is not available, this option can be set to false to create a bay/cluster without the load balancer. In this case, one of the masters will serve as the API endpoint. The default is true, i.e. to create the load balancer for the bay.
     * 
     * @return Status of master LB.
     */
    Boolean getMasterLbEnable();
}
