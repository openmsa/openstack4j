package org.openstack4j.openstack.sahara.domain;

import org.openstack4j.model.sahara.JobConfigHintConfig;
import org.openstack4j.util.ToStringHelper;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * For mapping JSON response to/from java objects
 *
 * @author ekasit.kijsipongse@nectec.or.th
 * @author siwat.pru@outlook.com
 */

public class SaharaJobConfigHintConfig implements JobConfigHintConfig {

    @JsonProperty("name")
    private String name;
    @JsonProperty("value")
    private String value;
    @JsonProperty("description")
    private String description;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getValue() {
        return value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return new ToStringHelper(this)
                .add("name", name)
                .add("value", value)
                .add("description", description)
                .toString();
    }

}
