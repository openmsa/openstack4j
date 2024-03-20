package org.openstack4j.openstack.common;

import org.openstack4j.model.common.IdEntity;
import org.openstack4j.util.ToStringHelper;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Basic Id based Entity Model implementation
 *
 * @author Jeremy Unruh
 */
public class IdResourceEntity implements IdEntity {

    private static final long serialVersionUID = 1L;

    @JsonProperty
    private String id;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return new ToStringHelper(getClass())
                .add("id", id)
                .toString();
    }
}
