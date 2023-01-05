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
package org.openstack4j.model.zun.builder;

import java.util.List;
import java.util.Map;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.zun.CapsuleContainer;
import org.openstack4j.model.zun.PortsBuilder;
import org.openstack4j.model.zun.ResourceBuilder;
import org.openstack4j.model.zun.VolumeMount;

public interface CapsuleContainerBuilder extends Buildable.Builder<CapsuleContainerBuilder, CapsuleContainer>  {

    CapsuleContainerBuilder image(String name);

    CapsuleContainerBuilder command(List<String> commands);

    CapsuleContainerBuilder args(List<String> args);

    CapsuleContainerBuilder env(Map<String, String> envs);

    CapsuleContainerBuilder name(String name);

    CapsuleContainerBuilder ports(List<PortsBuilder> ports);

    CapsuleContainerBuilder resources(ResourceBuilder o);

    CapsuleContainerBuilder volumeMounts(List<VolumeMount> vols);

    CapsuleContainerBuilder workDir(String workDir);
}
