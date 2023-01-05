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

import org.openstack4j.model.zun.MountsBuilder;
import org.openstack4j.model.zun.NetsBuilder;
import org.openstack4j.model.zun.RestartPolicy;

public interface ContainersCreateBuilder {

    ContainersCreateBuilder environemet(Map<String, String> env);

    ContainersCreateBuilder labels(Map<String, String> labels);

    ContainersCreateBuilder image(String image);

    ContainersCreateBuilder command(List<String> cmd);

    ContainersCreateBuilder name(String name);

    ContainersCreateBuilder cpu(float cpu);

    ContainersCreateBuilder memory(int mem);

    ContainersCreateBuilder workdir(String wd);

    ContainersCreateBuilder restartPolicy(RestartPolicy policy);

    ContainersCreateBuilder interactive(boolean interactive);

    ContainersCreateBuilder tty(boolean tty);

    ContainersCreateBuilder imageDriver(String driver);

    ContainersCreateBuilder securityGroups(List<String> sg);

    ContainersCreateBuilder nets(List<NetsBuilder> nets);

    ContainersCreateBuilder runtime(String rt);

    ContainersCreateBuilder hostname(String hostname);

    ContainersCreateBuilder autoRemove(boolean ar);

    ContainersCreateBuilder autoHeal(boolean ah);

    ContainersCreateBuilder availabilityZone(String az);

    ContainersCreateBuilder hints(Map<String, String> hints);

    ContainersCreateBuilder mounts(List<MountsBuilder> mounts);

    ContainersCreateBuilder privileged(boolean privileged);

    ContainersCreateBuilder healthcheck(HealthCheck hc);

}
