package org.openstack4j.api.telemetry.gnocchi;

import java.util.List;

import org.openstack4j.openstack.telemetry.domain.GnocchiResource;

public interface SearchService {
    List<GnocchiResource> resource(final String resourceType);

    List<GnocchiResource> resource(final String resourceType, final String filter);
}
