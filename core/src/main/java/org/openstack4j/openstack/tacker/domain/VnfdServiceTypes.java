package org.openstack4j.openstack.tacker.domain;

import org.openstack4j.api.MoreObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * @author Vishvesh Deshmukh
 * @date Aug 11, 2016
 */
@JsonRootName("service_types")
@JsonIgnoreProperties(ignoreUnknown = true)
public class VnfdServiceTypes {

    private String id;

    @JsonProperty("service_type")
    private String serviceType;

    public static VnfdServiceTypes create() {
        return new VnfdServiceTypes();
    }

    /**
     * Service Type to Set : Example serviceType : vnfd
     *
     * @param serviceType the serviceType to set
     * @return VnfdServiceTypes
     */
    public VnfdServiceTypes serviceType(String serviceType) {
        this.serviceType = serviceType;
        return this;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the serviceType
     */
    public String getServiceType() {
        return serviceType;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues()
                .add("id", id).add("serviceType", serviceType)
                .toString();
    }

}
