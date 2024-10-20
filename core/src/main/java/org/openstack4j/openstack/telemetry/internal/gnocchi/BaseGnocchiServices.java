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
package org.openstack4j.openstack.telemetry.internal.gnocchi;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.openstack4j.api.types.ServiceType;
import org.openstack4j.openstack.common.functions.EnforceVersionToURL;
import org.openstack4j.openstack.internal.BaseOpenStackService;

public class BaseGnocchiServices extends BaseOpenStackService {
    protected BaseGnocchiServices() {
        super(ServiceType.TELEMETRY_GNOCCHI, EnforceVersionToURL.instance("/v1"));
    }

    protected <T> List<T> wrapList(final T[] type) {
        if (type != null) {
            return Arrays.asList(type);
        }
        return Collections.emptyList();
    }
}
