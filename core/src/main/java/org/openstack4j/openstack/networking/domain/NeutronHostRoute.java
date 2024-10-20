package org.openstack4j.openstack.networking.domain;

import org.openstack4j.model.network.HostRoute;
import org.openstack4j.util.ToStringHelper;

/**
 * A Network Host based Routing Entry.
 *
 * @author Jeremy Unruh
 */
public class NeutronHostRoute implements HostRoute {

    private static final long serialVersionUID = 1L;

    private String destination;
    private String nexthop;

    public NeutronHostRoute() {
    }

    public NeutronHostRoute(String destination, String nexthop) {
        this.destination = destination;
        this.nexthop = nexthop;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDestination() {
        return destination;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNexthop() {
        return nexthop;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new ToStringHelper(this)
                .add("destination", destination).add("nexthop", nexthop).toString();
    }

}
