package org.openstack4j.openstack.identity.v3.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.openstack4j.model.identity.v3.Domain;
import org.openstack4j.model.identity.v3.Project;
import org.openstack4j.model.identity.v3.builder.ProjectBuilder;
import org.openstack4j.openstack.common.ListResult;
import org.openstack4j.util.ToStringHelper;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Project model class for identity/v3
 *
 * @see <a href="http://developer.openstack.org/api-ref-identity-v3.html#projects-v3">API reference</a>
 */
@JsonRootName("project")
/* If we don't explicitly set extra as an ignore property, it will methods with @JsonAnyGetter/Setter will not work */
@JsonIgnoreProperties(value = "extra", ignoreUnknown = true)
public class KeystoneProject implements Project {

    private static final long serialVersionUID = 1L;
    @JsonProperty
    private String id;
    @JsonProperty
    private String name;
    @JsonProperty
    private KeystoneDomain domain;
    @JsonProperty("domain_id")
    private String domainId;
    private String description;
    private Map<String, String> options = new HashMap<>();
    @JsonIgnore
    private Map<String, String> links;
    @JsonProperty("parent_id")
    private String parentId;
    private String subtree;
    private String parents;
    private Boolean enabled = true;
    private List<String> tags = new ArrayList<>();

    /**
     * Extra API properties served
     */
    private Map<String, String> extra = new HashMap<>();

    /**
     * @return the Project builder
     */
    public static ProjectBuilder builder() {
        return new ProjectConcreteBuilder();
    }

    @Override
    public ProjectBuilder toBuilder() {
        return new ProjectConcreteBuilder(this);
    }

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
    public Domain getDomain() {
        return domain;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDomainId() {
        if (domainId == null && domain != null && domain.getId() != null)
            domainId = domain.getId();
        return domainId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    @Override
    public Map<String, String> getOptions() {
        return options;
    }

    /**
     * {@inheritDoc}
     */
    @JsonIgnore
    @Override
    public Map<String, String> getLinks() {
        return links;
    }

    /**
     * {@inheritDoc}
     */
    @JsonProperty("links")
    public void setLinks(Map<String, String> links) {
        this.links = links;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getParentId() {
        return parentId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSubtree() {
        return subtree;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getParents() {
        return parents;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEnabled() {
        return (enabled != null && enabled);
    }

    /**
     * set project enabled
     *
     * @param enabled the new enabled status
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getExtra(String key) {
        return extra.get(key);
    }

    @JsonAnyGetter
    public Map<String, String> getExtra() {
        return extra;
    }

    @JsonAnySetter
    public void setExtra(String key, String value) {
        // is_domain is not necessary
        // if we don't ignore this, this will be set into extra field.
        if (Objects.equals(key, "is_domain")) {
            return;
        }
        extra.put(key, value);
    }

    /**
     * @return the tags
     */
    public List<String> getTags() {
        return tags;
    }

    /**
     * @param tags the tags to set
     */
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String dId = null;
        if (domain != null) {
            dId = domain.getId();
        }

        return new ToStringHelper(this)
                .add("id", id)
                .add("domainId", dId)
                .add("description", description)
                .add("name", name)
                .add("links", links)
                .add("parentId", parentId)
                .add("subtree", subtree)
                .add("parents", parents)
                .add("enabled", enabled)

                .toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, domain != null ? domain.getId(): domainId, description, name, links, parentId, subtree, parents);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        KeystoneProject that = KeystoneProject.class.cast(obj);
        return Objects.equals(this.id, that.id)
                && Objects.equals(this.domain, that.domain)
                && Objects.equals(this.description, that.description)
                && Objects.equals(this.name, that.name)
                && Objects.equals(this.links, that.links)
                && Objects.equals(this.parentId, that.parentId)
                && Objects.equals(this.subtree, that.subtree)
                && Objects.equals(this.parents, that.parents)
                && Objects.equals(this.enabled, that.enabled);
    }

    public static class ProjectConcreteBuilder implements ProjectBuilder {

        KeystoneProject model;

        ProjectConcreteBuilder() {
            this(new KeystoneProject());
        }

        ProjectConcreteBuilder(KeystoneProject model) {
            this.model = model;
        }

        /**
         * @see KeystoneProject#getId()
         */
        @Override
        public ProjectBuilder id(String id) {
            model.id = id;
            return this;
        }

        /**
         * @see KeystoneProject#getDomainId()
         */
        @Override
        public ProjectBuilder domain(Domain domain) {
            if (domain != null && domain.getId() != null)
                model.domainId = domain.getId();
            return this;
        }

        /**
         * @see KeystoneProject#getDescription()
         */
        @Override
        public ProjectBuilder description(String description) {
            model.description = description;
            return this;
        }

        /**
         * @see KeystoneProject#getName()
         */
        @Override
        public ProjectBuilder name(String name) {
            model.name = name;
            return this;
        }

        @Override
        public ProjectBuilder options(Map<String, String> options) {
            model.options = options;
            return this;
        }

        /**
         * @see KeystoneProject#getLinks()
         */
        @Override
        public ProjectBuilder links(Map<String, String> links) {
            model.links = links;
            return this;
        }

        /**
         * @see KeystoneProject#getParentId()
         */
        @Override
        public ProjectBuilder parentId(String parentId) {
            model.parentId = parentId;
            return this;
        }

        /**
         * @see KeystoneProject#getSubtree()
         */
        @Override
        public ProjectBuilder subtree(String subtree) {
            model.subtree = subtree;
            return this;
        }

        /**
         * @see KeystoneProject#getParents()
         */
        @Override
        public ProjectBuilder parents(String parents) {
            model.parents = parents;
            return this;
        }


        /**
         * @see KeystoneProject#setExtra(String, String)
         */
        @Override
        public ProjectBuilder setExtra(String key, String value) {
            model.extra.put(key, value);
            return this;
        }

        /**
         * @see KeystoneProject#setTags(List<String>)
         */

        @Override
        public ProjectBuilder setTags(List<String> tags) {
            model.setTags(tags);
            return this;
        }

        /**
         * @see KeystoneProject#isEnabled()
         */
        @Override
        public ProjectBuilder enabled(boolean enabled) {
            model.enabled = enabled;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Project build() {
            return model;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ProjectBuilder from(Project in) {
            if (in != null)
                this.model = (KeystoneProject) in;
            return this;
        }

        @Override
        public ProjectBuilder domainId(String domainId) {
            model.domainId = domainId;
            return this;
        }
    }

    public static class Projects extends ListResult<KeystoneProject> {

        private static final long serialVersionUID = 1L;
        @JsonProperty("projects")
        protected List<KeystoneProject> list;

        @Override
        public List<KeystoneProject> value() {
            return list;
        }
    }

}
