package org.openstack4j.openstack.telemetry.internal.gnocchi;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.openstack4j.api.telemetry.gnocchi.SearchService;
import org.openstack4j.openstack.telemetry.domain.GnocchiResource;

public class SearchServiceImpl extends BaseGnocchiServices implements SearchService {

    @Override
    public List<GnocchiResource> resource(final String resourceType) {
        final GnocchiResource[] resources = post(GnocchiResource[].class, uri("/search/resource/%s", resourceType))
                .execute();
        return wrapList(resources);
    }

    @Override
    public List<GnocchiResource> resource(final String resourceType, final String filter) {
        final Map<String, String> params = Optional.ofNullable(filter).map(x -> Collections.singletonMap("filter", x))
                .orElse(new HashMap<>());
        final GnocchiResource[] resources = post(GnocchiResource[].class, uri("/search/resource/%s", resourceType))
                .params(params).execute();
        return wrapList(resources);
    }
}
