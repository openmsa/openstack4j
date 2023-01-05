package org.openstack4j.api.telemetry;

import org.openstack4j.api.telemetry.gnocchi.MeasuresService;
import org.openstack4j.api.telemetry.gnocchi.MetricsService;
import org.openstack4j.api.telemetry.gnocchi.ResourcesService;
import org.openstack4j.api.telemetry.gnocchi.SearchService;

public interface GnocchiService {

    MetricsService metrics();

    MeasuresService mesures();

    ResourcesService resources();

    SearchService search();
}
