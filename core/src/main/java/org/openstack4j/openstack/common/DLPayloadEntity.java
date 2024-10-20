package org.openstack4j.openstack.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import org.openstack4j.core.transport.HttpResponse;
import org.openstack4j.model.common.DLPayload;

/**
 * A Payload which encapsulates downstream data
 *
 * @author Jeremy Unruh
 */
public class DLPayloadEntity implements DLPayload {

    private final HttpResponse response;

    private DLPayloadEntity(HttpResponse response) {
        this.response = response;
    }

    public static DLPayloadEntity create(HttpResponse response) {
        return new DLPayloadEntity(response);
    }

    @Override
    public HttpResponse getHttpResponse() {
        return response;
    }

    @Override
    public InputStream getInputStream() {
        return response.getInputStream();
    }

    @Override
    public void writeToFile(File file) throws IOException {
        Objects.requireNonNull(file);
        try (InputStream inputStream = response.getInputStream();
                FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            inputStream.transferTo(fileOutputStream);
        }
    }

}
