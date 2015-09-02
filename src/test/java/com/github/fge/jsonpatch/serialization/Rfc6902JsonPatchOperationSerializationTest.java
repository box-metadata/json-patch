package com.github.fge.jsonpatch.serialization;

import com.github.fge.jsonpatch.JsonPatchOperation;
import org.testng.annotations.Test;

import java.io.IOException;

@Test
public abstract class Rfc6902JsonPatchOperationSerializationTest extends JsonPatchOperationSerializationTest {
    protected Rfc6902JsonPatchOperationSerializationTest(final String operationName,
        final Class<? extends JsonPatchOperation> c)
        throws IOException {
        super(operationName, "rfc6902", c);
    }
}
