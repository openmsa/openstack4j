package org.openstack4j.openstack.telemetry.internal;

import org.openstack4j.api.Apis;
import org.openstack4j.api.telemetry.AlarmAodhService;
import org.openstack4j.api.telemetry.CapabilitiesService;
import org.openstack4j.api.telemetry.EventService;
import org.openstack4j.api.telemetry.GnocchiService;
import org.openstack4j.api.telemetry.MeterService;
import org.openstack4j.api.telemetry.SampleService;
import org.openstack4j.api.telemetry.TelemetryAodhService;
import org.openstack4j.api.telemetry.gnocchi.ResourcesService;

/**
 * @author zhangjianweibj
 */
public class TelemetryAodhServiceImpl implements TelemetryAodhService {

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
    public AlarmAodhService alarms() {
        return Apis.get(AlarmAodhService.class);
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
