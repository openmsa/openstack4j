package org.openstack4j.openstack.compute.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openstack4j.model.compute.Address;
import org.openstack4j.model.compute.Addresses;
import org.openstack4j.util.ToStringHelper;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NovaAddresses implements Addresses {

    private static final long serialVersionUID = 1L;

    @JsonProperty("addresses")
    private Map<String, List<NovaAddress>> addresses = new HashMap<>();

    @Override
    public void add(String key, Address value) {
        if (!addresses.containsKey(key))
            addresses.put(key, new ArrayList<>());

        addresses.get(key).add((NovaAddress) value);
    }

    @JsonAnySetter
    public void add(String key, List<NovaAddress> value) {
        addresses.put(key, value);
    }

    @Override
    public Map<String, List<? extends Address>> getAddresses() {
        return encapsulate();
    }

    @Override
    public List<? extends Address> getAddresses(String type) {
        return addresses.get(type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new ToStringHelper(this)
                .add("addresses", addresses).addValue("\n")
                .toString();
    }

    @SuppressWarnings("unchecked")
    private <T> T encapsulate() {
        return (T) addresses;
    }

    public static class NovaAddress implements Address {

        private static final long serialVersionUID = 1L;
        @JsonProperty("OS-EXT-IPS-MAC:mac_addr")
        private String macAddr;
        private int version;
        private String addr;
        @JsonProperty("OS-EXT-IPS:type")
        private String type;

        @Override
        public String getMacAddr() {
            return macAddr;
        }

        @Override
        public int getVersion() {
            return version;
        }

        @Override
        public String getAddr() {
            return addr;
        }

        @Override
        public String getType() {
            return type;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return new ToStringHelper(this)
                    .add("address", addr).add("type", type).add("version", version)
                    .add("macaddr", macAddr).addValue("\n")
                    .toString();
        }

    }

}
