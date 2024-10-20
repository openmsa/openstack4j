package org.openstack4j.openstack.networking.domain;

import org.openstack4j.model.network.AllowedAddressPair;
import org.openstack4j.util.ToStringHelper;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A Fixed IP Address
 *
 * @author Jeremy Unruh
 */
public class NeutronAllowedAddressPair implements AllowedAddressPair {

    private static final long serialVersionUID = 1L;

    @JsonProperty("ip_address")
    private String ipAddress;

    @JsonProperty("mac_address")
    private String macAddress;

    public NeutronAllowedAddressPair() {
    }

    public NeutronAllowedAddressPair(String ipAddress, String macAddress) {
        this.ipAddress = ipAddress;
        this.macAddress = macAddress;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getIpAddress() {
        return ipAddress;
    }

    @Override
    public String getMacAddress() {
        return macAddress;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new ToStringHelper(this)
                .add("ipAddress", ipAddress).add("macAddress", macAddress).toString();
    }

}
