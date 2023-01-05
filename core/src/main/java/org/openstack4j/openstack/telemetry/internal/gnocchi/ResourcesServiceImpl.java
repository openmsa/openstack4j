package org.openstack4j.openstack.telemetry.internal.gnocchi;

import static com.google.common.base.Preconditions.checkNotNull;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.openstack4j.api.telemetry.gnocchi.ResourcesService;
import org.openstack4j.model.telemetry.gnocchi.MeasureFilter;
import org.openstack4j.model.telemetry.gnocchi.Resource;
import org.openstack4j.openstack.telemetry.domain.GnocchiMeasure;
import org.openstack4j.openstack.telemetry.domain.GnocchiResource;

public class ResourcesServiceImpl extends BaseGnocchiServices implements ResourcesService {

    @Override
    public Resource create(final Resource resource) {
        checkNotNull(resource);
        return post(GnocchiResource.class, uri("/resource")).entity((resource)).execute();
    }

    @Override
    public Resource read(final String resourceId) {
        checkNotNull(resourceId);
        return get(GnocchiResource.class, uri("/resource/%s", resourceId)).execute();
    }

    @Override
    public List<? extends Resource> list() {
        return list("generic");
    }

    @Override
    public List<? extends Resource> list(final String resource) {
        final GnocchiResource[] resources = get(GnocchiResource[].class, uri("/resource/%s", resource)).execute();
        return wrapList(resources);
    }

    @Override
    public List<GnocchiMeasure> list(final String resource, final String id, final String metric,
            final MeasureFilter filter) {
        final Invocation<Object[][]> inv = get(Object[][].class,
                uri("/resource/%s/%s/metric/%s/measures", resource, id, metric));
        final Consumer<? super MeasureFilter> applyFilters = x -> inv.params(x.getConstraints());
        Optional.ofNullable(filter).ifPresent(applyFilters);
        final Object[][] measuresRaw = inv.execute();
        return Arrays.stream(measuresRaw).map(this::newMeasure).collect(Collectors.toList());
    }

    private GnocchiMeasure newMeasure(final Object[] obj) {
        final String strDate = (String) obj[0];
        final Double gran = (Double) obj[1];
        final Double value = (Double) obj[2];
        return new GnocchiMeasure(OffsetDateTime.parse(strDate), gran.intValue(), value);
    }

    @Override
    public Resource update(final String resourceType, final Resource resource) {
        checkNotNull(resourceType);
        checkNotNull(resource);
        checkNotNull(resource.getId());
        return patch(GnocchiResource.class, uri("/resource/%s/%s", resourceType, resource.getId())).execute();
    }

    @Override
    public List<Resource> history(final String instanceId) {
        checkNotNull(instanceId);
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(final String resourceId) {
        checkNotNull(resourceId);
        delete(resourceId);
    }

    @Override
    public GnocchiResource instance(final String instanceId) {
        checkNotNull(instanceId);
        return get(GnocchiResource.class, uri("/resource/instance/%s", instanceId)).execute();
    }
}
