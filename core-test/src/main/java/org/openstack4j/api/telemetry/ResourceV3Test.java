package org.openstack4j.api.telemetry;

import java.io.IOException;
import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.telemetry.Resource;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

@Test(suiteName = "ResourceV3 Tests")
public class ResourceV3Test extends AbstractTest {
    private static final String JSON_RESOURCES = "/telemetry/resources.json";
    private static final String JSON_RESOURCE = "/telemetry/resource.json";

    @Override
    protected Service service() {
        return Service.TELEMETRY;
    }

    @Test
    public void listResourcesTest() throws IOException {
        respondWith(JSON_RESOURCES);

        List<? extends org.openstack4j.model.telemetry.gnocchi.Resource> resourcess = osv3().telemetry().resources().list();
        assertEquals(resourcess.size(), 2);

        org.openstack4j.model.telemetry.gnocchi.Resource resource = resourcess.get(0);
        assertEquals(resource.getId(), "02748368-2b4a-4b70-ac13-b6c5fd8ed415");
        //assertNotNull(resource.getMeataData());
    }

    @Test
    public void getSampleTest() throws IOException {
        respondWith(JSON_RESOURCE);
        //Resource resource = osv3().telemetry().resources().get("1e93a890-3732-11e6-a491-005056ac9b87");
        //assertEquals(resource.getId(), "02748368-2b4a-4b70-ac13-b6c5fd8ed415");
        //assertNotNull(resource.getMeataData());
    }
}
