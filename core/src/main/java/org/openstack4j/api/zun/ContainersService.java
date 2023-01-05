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
package org.openstack4j.api.zun;

import java.util.List;

import org.openstack4j.model.zun.builder.ContainersCreateBuilder;

public interface ContainersService {

    org.openstack4j.openstack.magnum.MagnumContainer.Containers create(ContainersCreateBuilder containersCreate);
    
    List<? extends org.openstack4j.openstack.magnum.MagnumContainer.Containers>list();
    
    org.openstack4j.openstack.magnum.MagnumContainer.Containers get(String id);
    
    void delete(String id);
    
    
}
