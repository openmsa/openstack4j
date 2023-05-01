package org.openstack4j.openstack.tacker.domain;

import org.openstack4j.util.ToStringHelper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * @author Vishvesh Deshmukh
 * @date Aug 16, 2016
 */
@JsonRootName("placement_attr")
@JsonIgnoreProperties(ignoreUnknown = true)
public class VnfPlacementAttribute {

    @JsonProperty("vim_name")
    private String vimName;

    @Override
    public String toString() {
        return new ToStringHelper(this)
                .add("vimName", vimName)
                .toString();
    }

    /**
     * @return the vimName
     */
    public String getVimName() {
        return vimName;
    }
}
