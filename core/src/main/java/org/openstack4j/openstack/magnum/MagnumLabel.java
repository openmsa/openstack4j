package org.openstack4j.openstack.magnum;

import org.openstack4j.model.magnum.Label;
import org.openstack4j.model.magnum.LabelBuilder;
import org.openstack4j.util.ToStringHelper;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MagnumLabel implements Label {
    private static final long serialVersionUID = 1L;
    @JsonProperty("key")
    private String key;

    public static LabelBuilder builder() {
        return new LabelConcreteBuilder();
    }

    @Override
    public LabelBuilder toBuilder() {
        return new LabelConcreteBuilder(this);
    }

    public String getKey() {
        return key;
    }

    @Override
    public String toString() {
        return new ToStringHelper(this).add("key", key).toString();
    }

    /**
     * Concrete builder containing MagnumLabel as model
     */
    public static class LabelConcreteBuilder implements LabelBuilder {
        MagnumLabel model;

        public LabelConcreteBuilder() {
            this(new MagnumLabel());
        }

        public LabelConcreteBuilder(MagnumLabel model) {
            this.model = model;
        }

        @Override
        public Label build() {
            return model;
        }

        @Override
        public LabelBuilder from(Label in) {
            if (in != null)
                this.model = (MagnumLabel) in;
            return this;
        }

        @Override
        public LabelBuilder key(String key) {
            model.key = key;
            return this;
        }
    }

}
