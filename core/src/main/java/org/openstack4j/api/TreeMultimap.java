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

import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class TreeMultimap<K,V> implements SortedSetMultimap<K, V>{
    TreeMap<K, SortedSet<V>> map;

    public TreeMultimap() {
        map = new TreeMap<>();
    }

    public static <K,V> TreeMultimap<K, V> create() {
        return new TreeMultimap<>();
    }

    @Override
    public void put(K key, V value) {
        SortedSet<V> lst = map.computeIfAbsent(key, x -> new TreeSet<>());
        lst.add(value);
    }

    @Override
    public SortedSet<V> get(K key) {
        return map.get(key);
    }

}
