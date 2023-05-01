package org.openstack4j.test.common;

import static org.testng.Assert.assertTrue;

import java.util.Map;

import org.openstack4j.openstack.storage.object.functions.MapWithoutMetaPrefixFunction;
import org.testng.annotations.Test;

/**
 * Tests HeaderNameValue based transformation
 *
 * @author Jeremy Unruh
 */
public class MapWithoutMetaPrefixFunctionTest {

    private static Map<String, String> VALUES = Map.of(
            "X-Meta-Value1", "Value1",
            "X-Meta-Value2", "value2",
            "x-meta-value3", "Value3",
            "x-meta-value4", "value4" // Test case-sensitiveness of X- or -Meta- detection to be RFC-compliant
    );

    @Test
    public void keyTest() {
        Map<String, String> map = MapWithoutMetaPrefixFunction.INSTANCE.apply(VALUES);
        for (String key : VALUES.keySet()) {
            assertTrue(map.containsKey(key));
        }
    }

    @Test
    public void valueTest() {
        Map<String, String> map = MapWithoutMetaPrefixFunction.INSTANCE.apply(VALUES);
        for (String value : VALUES.values()) {
            assertTrue(map.containsValue(value));
        }
    }

}
