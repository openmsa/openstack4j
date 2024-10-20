package org.openstack4j.openstack.compute.domain.ext;

import java.util.List;

import org.openstack4j.model.compute.ext.DomainEntry;
import org.openstack4j.openstack.common.ListResult;
import org.openstack4j.util.ToStringHelper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * A Floating IP DNS Extension - Domain Entry
 *
 * @author Jeremy Unruh
 */
@JsonRootName("domain_entry")
@Deprecated
public class ExtDomainEntry implements DomainEntry {

    private static final long serialVersionUID = 1L;

    @JsonProperty("availability_zone")
    private String availabilityZone;
    @JsonProperty
    private String domain;
    @JsonProperty
    private String project;
    @JsonProperty
    private Scope scope;

    public ExtDomainEntry() {
    }

    public ExtDomainEntry(Scope scope, String availabilityZone, String project) {
        this.scope = scope;
        this.availabilityZone = availabilityZone;
        this.project = project;
    }

    @Override
    public String getAvailabilityZone() {
        return availabilityZone;
    }

    @Override
    public String getDomain() {
        return domain;
    }

    @Override
    public String getProject() {
        return project;
    }

    @Override
    public Scope getScope() {
        return scope;
    }

    @Override
    public String toString() {
        return new ToStringHelper(this)
                .add("availabilityZone", availabilityZone).add("domain", domain)
                .add("project", project).add("scope", scope)
                .toString();
    }

    @Deprecated
    public static class DomainEntries extends ListResult<ExtDomainEntry> {

        private static final long serialVersionUID = 1L;

        @JsonProperty("domain_entries")
        private List<ExtDomainEntry> results;

        @Override
        protected List<ExtDomainEntry> value() {
            return results;
        }

    }

}
