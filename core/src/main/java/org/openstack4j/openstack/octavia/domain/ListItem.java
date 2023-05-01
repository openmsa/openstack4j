package org.openstack4j.openstack.octavia.domain;

import org.openstack4j.api.MoreObjects;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Lbaas V2 lists of objects take the form
 * "id":"the_id"
 * This class is used to hold these list items in the proper format
 *
 * @author wei
 */
@JsonAutoDetect
public class ListItem {
    @JsonProperty("id")
    String id;

    public ListItem() {
    }

    public ListItem(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .toString();
    }
}
