package org.openstack4j.openstack.dns.v2.internal;

import static org.openstack4j.core.transport.ClientConstants.PATH_NAMESERVERS;
import static org.openstack4j.core.transport.ClientConstants.PATH_ZONES;

import java.util.List;
import java.util.Objects;

import org.openstack4j.api.dns.v2.ZoneService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.dns.v2.Nameserver;
import org.openstack4j.model.dns.v2.Zone;
import org.openstack4j.openstack.dns.v2.domain.DesignateNameserver;
import org.openstack4j.openstack.dns.v2.domain.DesignateZone;

public class ZoneServiceImpl extends BaseDNSServices implements ZoneService {

    @Override
    public Zone get(String zoneId) {
        Objects.requireNonNull(zoneId);
        return get(DesignateZone.class, PATH_ZONES, "/", zoneId).execute();
    }

    @Override
    public ActionResponse delete(String zoneId) {
        Objects.requireNonNull(zoneId);
        return deleteWithResponse(PATH_ZONES, "/", zoneId).execute();
    }

    @Override
    public List<? extends Nameserver> listNameservers(String zoneId) {
        Objects.requireNonNull(zoneId);
        return get(DesignateNameserver.Nameservers.class, PATH_ZONES, "/", zoneId, PATH_NAMESERVERS).execute().getList();
    }

    @Override
    public Zone update(Zone zone) {
        Objects.requireNonNull(zone);
        return patch(DesignateZone.class, PATH_ZONES, "/", zone.getId()).entity(zone).execute();
    }

    @Override
    public Zone create(Zone zone) {
        Objects.requireNonNull(zone);
        return post(DesignateZone.class, uri(PATH_ZONES)).entity(zone).execute();
    }

    @Override
    public Zone create(String name, String email) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(email);
        return create(DesignateZone.builder().name(name).email(email).build());
    }

    @Override
    public List<? extends Zone> list() {
        return get(DesignateZone.Zones.class, uri(PATH_ZONES)).execute().getList();
    }

}
