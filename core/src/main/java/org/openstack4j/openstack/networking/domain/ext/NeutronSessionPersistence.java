package org.openstack4j.openstack.networking.domain.ext;

import org.openstack4j.model.network.ext.SessionPersistence;
import org.openstack4j.model.network.ext.SessionPersistenceType;
import org.openstack4j.model.network.ext.builder.SessionPersistenceBuilder;
import org.openstack4j.util.ToStringHelper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("session_persistence")
@JsonIgnoreProperties(ignoreUnknown = true)
public class NeutronSessionPersistence implements SessionPersistence {

    private static final long serialVersionUID = 1L;
    @JsonProperty("cookie_name")
    private String cookieName;
    private SessionPersistenceType type;

    public static SessionPersistenceBuilder builder() {
        return new SessionPersistenceContreteBuilder();
    }

    /**
     * wrap the SessionPersistence to builder
     *
     * @return SessionPersistenceBuilder
     */
    @Override
    public SessionPersistenceBuilder toBuilder() {
        return new SessionPersistenceContreteBuilder();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCookieName() {
        return cookieName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SessionPersistenceType getType() {
        return type;
    }

    @Override
    public String toString() {
        return new ToStringHelper(this)
                .add("type", type)
                .add("cookieName", cookieName)
                .toString();
    }

    /**
     * SessionPersistence Builder
     *
     * @author liujunpeng
     */
    public static class SessionPersistenceContreteBuilder implements SessionPersistenceBuilder {

        private NeutronSessionPersistence m;

        public SessionPersistenceContreteBuilder() {
            this(new NeutronSessionPersistence());
        }

        public SessionPersistenceContreteBuilder(NeutronSessionPersistence m) {
            this.m = m;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public SessionPersistenceBuilder from(SessionPersistence in) {
            m = (NeutronSessionPersistence) in;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public SessionPersistenceBuilder type(SessionPersistenceType type) {
            m.type = type;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public SessionPersistenceBuilder cookieName(String cookieName) {
            m.cookieName = cookieName;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public SessionPersistence build() {
            return m;
        }

    }


}
