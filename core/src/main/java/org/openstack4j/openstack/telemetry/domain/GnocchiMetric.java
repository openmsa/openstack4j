package org.openstack4j.openstack.telemetry.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.google.common.base.MoreObjects;

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
