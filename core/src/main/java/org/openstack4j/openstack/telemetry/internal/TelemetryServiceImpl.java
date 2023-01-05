package org.openstack4j.openstack.telemetry.internal;

import org.openstack4j.api.Apis;
import org.openstack4j.api.telemetry.AlarmService;
import org.openstack4j.api.telemetry.CapabilitiesService;
import org.openstack4j.api.telemetry.EventService;
import org.openstack4j.api.telemetry.GnocchiService;
import org.openstack4j.api.telemetry.MeterService;
import org.openstack4j.api.telemetry.SampleService;
import org.openstack4j.api.telemetry.TelemetryService;
import org.openstack4j.api.telemetry.gnocchi.ResourcesService;

/**
 * Telemetry allows collection of Alarms and Measurements
 *
 * @author Jeremy Unruh
 */
public class TelemetryServiceImpl implements TelemetryService {

    /**
     * {@inheritDoc}
     */
    @Override
    public MeterService meters() {
        return Apis.get(MeterService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AlarmService alarms() {
        return Apis.get(AlarmService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EventService events() {
        return Apis.get(EventService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SampleService samples() {
        return Apis.get(SampleService.class);
    }

    @Override
    public ResourcesService resources() {
        return Apis.get(ResourcesService.class);
    }

    @Override
    public CapabilitiesService capabilities() {
        return Apis.get(CapabilitiesService.class);
    }

    @Override
    public GnocchiService gnocchi() {
        return Apis.get(GnocchiService.class);
    }

}
