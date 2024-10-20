package org.openstack4j.openstack.barbican.domain;

import org.openstack4j.model.barbican.ContainerConsumer;
import org.openstack4j.util.ToStringHelper;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BarbicanContainerConsumer implements ContainerConsumer {
    private static final long serialVersionUID = 1L;
    private String name;
    @JsonProperty("URL")
    private String url;

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
    public String getUrl() {
        return url;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new ToStringHelper(this)
                .add("name", name).add("url", url)
                .toString();
    }
}
