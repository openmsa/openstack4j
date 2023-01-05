/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package org.openstack4j.openstack.zun.domain;

import java.util.List;

import org.openstack4j.model.zun.CapsuleSpec;
import org.openstack4j.model.zun.builder.CapsuleSpecBuilder;
import org.openstack4j.model.zun.builder.CapsuleVolumeBuilder;

public class ZunCapsuleSpec implements CapsuleSpec {

    private List<CapsuleSpecBuilder> containers;
    private String restartPolicy;
    private List<?> initContainers;
    private List<CapsuleVolumeBuilder> volumes;

    @Override
    public List<CapsuleSpecBuilder> getcontainers() {
        return containers;
    }

    @Override
    public String getRestartPolicy() {
        return restartPolicy;
    }

    @Override
    public List<?> getInitContainers() {
        return initContainers;
    }

    @Override
    public List<CapsuleVolumeBuilder> getVolumes() {
        return volumes;
    }

    @Override
    public CapsuleSpecBuilder toBuilder() {
        // TODO Auto-generated method stub
        return null;
    }

    
}
