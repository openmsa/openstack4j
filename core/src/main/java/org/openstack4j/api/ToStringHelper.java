/**
 *     Copyright (C) 2019-2023 Ubiqube.
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
package org.openstack4j.api;

import java.util.StringJoiner;

public class ToStringHelper {
    private StringJoiner stringJoiner;
    private boolean omitNull;

    public ToStringHelper(Class<?> clazz) {
        stringJoiner = new StringJoiner(",", clazz.getSimpleName() +"[" , "]");
    }
    public ToStringHelper omitNullValues() {
        omitNull = true;
        return this;
    }

    public ToStringHelper add(String string, Object message) {
        if (message == null && omitNull) {
            return this;
        }
        stringJoiner.add(string + "=" + message);
        return this;
    }

    public ToStringHelper addValue(String message) {
        if (message == null && omitNull) {
            return this;
        }
        stringJoiner.add(message);
        return this;
    }
}
