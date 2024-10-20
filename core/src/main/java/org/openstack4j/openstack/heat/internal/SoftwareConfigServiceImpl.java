package org.openstack4j.openstack.heat.internal;

import java.util.Objects;

import org.openstack4j.api.heat.SoftwareConfigService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.heat.SoftwareConfig;
import org.openstack4j.openstack.heat.domain.HeatSoftwareConfig;

/**
 * Software Configuration Service for HEAT Orchestration
 *
 * @author Jeremy Unruh
 */
public class SoftwareConfigServiceImpl extends BaseHeatServices implements SoftwareConfigService {

    private static final String BASE_URI = "/software_configs";

    /**
     * {@inheritDoc}
     */
    @Override
    public SoftwareConfig create(SoftwareConfig sc) {
        Objects.requireNonNull(sc);
        return post(HeatSoftwareConfig.class, BASE_URI).entity(sc).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SoftwareConfig show(String configId) {
        Objects.requireNonNull(configId);
        return get(HeatSoftwareConfig.class, uri(BASE_URI + "/%s", configId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String configId) {
        Objects.requireNonNull(configId);
        return deleteWithResponse(uri(BASE_URI + "/%s", configId)).execute();
    }

}
