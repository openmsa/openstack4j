package org.openstack4j.openstack.telemetry.domain;

import java.time.OffsetDateTime;

import org.openstack4j.model.telemetry.gnocchi.Measure;

import com.google.common.base.MoreObjects;

public class GnocchiMeasure implements Measure {
    private static final long serialVersionUID = 1L;

    private final OffsetDateTime timeStamp;

    private final Double value;

    private final int granularity;

    public GnocchiMeasure(final OffsetDateTime time, final int granularity, final Double value) {
        this.timeStamp = time;
        this.granularity = granularity;
        this.value = value;
    }

    @Override
    public OffsetDateTime getTimeStamp() {
        return timeStamp;
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public int getGranularity() {
        return granularity;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues().add("timeStamp", timeStamp).add("value", value)
                .add("granularity", granularity).toString();
    }

}
