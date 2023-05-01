package org.openstack4j.openstack.telemetry.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.openstack4j.api.MoreObjects;

import com.fasterxml.jackson.annotation.JsonAnySetter;

public class GnocchiMetric implements Serializable {
    /** Serial. */
    private static final long serialVersionUID = 1L;

    private final Map<String, String> keyPair = new HashMap<>();

    public Map<String, String> getKeyPair() {
        return keyPair;
    }

    @JsonAnySetter
    public void setKeyPair(final String key, final String value) {
        keyPair.put(key, value);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues().add("keyPair", keyPair).toString();
    }

}
