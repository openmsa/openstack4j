package org.openstack4j.model.network.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.network.Network;
import org.openstack4j.model.network.NetworkType;

/**
 * A Builder which creates a Network
 *
 * @author Jeremy Unruh
 */
public interface NetworkBuilder extends Builder<NetworkBuilder, Network> {

    /**
     * @see Network#getName()
     */
    NetworkBuilder name(String name);

    /**
     * @see Network#isAdminStateUp()
     */
    NetworkBuilder adminStateUp(boolean adminStateUp);

    /**
     * @see Network#getNetworkType()
     */
    NetworkBuilder networkType(NetworkType networkType);

    /**
     * @see Network#getProviderPhyNet()
     */
    NetworkBuilder physicalNetwork(String providerPhysicalNetwork);

    /**
     * @see Network#getProviderSegID()
     */
    NetworkBuilder segmentId(String providerSegmentationId);

    /**
     * @see Network#getTenantId()
     */
    NetworkBuilder tenantId(String tenantId);

    /**
     * @see Network#isShared()
     */
    NetworkBuilder isShared(boolean shared);

    /**
     * @see Network#isRouterExternal()
     */
    NetworkBuilder isRouterExternal(boolean routerExternal);

    /**
     * @see Network#getAvailabilityZoneHints()
     */
    NetworkBuilder addAvailabilityZoneHints(String availabilityZone);

    /**
     * @see Network#isPortSecurityEnabled()
     */
    NetworkBuilder isPortSecurityEnabled(Boolean portSecurityEnabled);

    /**
     * @see Network#isDefault()
     */
    NetworkBuilder isDefault(Boolean isDefault);

    /**
     * @See Network#getMTU()
     */
    NetworkBuilder mtu(Integer mtu);

    /**
     * @see Network#getDnsDomain()
     */
    NetworkBuilder dnsDomain(String dnsDomain);

    /**
     * @see Network#getQosPolicyId()
     */
    NetworkBuilder qosPolicyId(String id);

    /**
     * @see Network#getVlanTransparent()
     */
    NetworkBuilder vlanTransparent(boolean value);
}
