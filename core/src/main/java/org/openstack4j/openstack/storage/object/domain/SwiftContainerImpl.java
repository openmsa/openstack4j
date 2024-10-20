package org.openstack4j.openstack.storage.object.domain;

import java.util.Map;

import org.openstack4j.api.Apis;
import org.openstack4j.api.storage.ObjectStorageContainerService;
import org.openstack4j.model.storage.object.SwiftContainer;
import org.openstack4j.util.ToStringHelper;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an OpenStack Swift Container which holds Objects
 *
 * @author Jeremy Unruh
 */
public class SwiftContainerImpl implements SwiftContainer {

    private static final long serialVersionUID = 1L;

    @JsonProperty
    private String name;

    @JsonProperty("count")
    private int objectCount;

    @JsonProperty("bytes")
    private long totalSize;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getObjectCount() {
        return objectCount;
    }

    @Override
    public long getTotalSize() {
        return totalSize;
    }

    @Override
    public Map<String, String> getMetadata() {
        return Apis.get(ObjectStorageContainerService.class).getMetadata(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new ToStringHelper(this)
                .add("name", name).add("count", objectCount).add("total size", totalSize)
                .toString();
    }
}
