package org.openstack4j.openstack.telemetry.internal.gnocchi;

import org.openstack4j.api.Apis;
import org.openstack4j.api.telemetry.GnocchiService;
import org.openstack4j.api.telemetry.gnocchi.MeasuresService;
import org.openstack4j.api.telemetry.gnocchi.MetricsService;
import org.openstack4j.api.telemetry.gnocchi.ResourcesService;
import org.openstack4j.api.telemetry.gnocchi.SearchService;

public class GnocchiServiceImpl implements GnocchiService {

    @Override
    public MetricsService metrics() {
        return Apis.get(MetricsService.class);
    }

    @Override
    public MeasuresService mesures() {
        return Apis.get(MeasuresService.class);
    }

    @Override
    public ResourcesService resources() {
        return Apis.get(ResourcesService.class);
    }

    @Override
    public SearchService search() {
        return Apis.get(SearchService.class);
    }

}
