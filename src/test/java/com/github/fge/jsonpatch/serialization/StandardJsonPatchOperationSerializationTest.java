package com.github.fge.jsonpatch.serialization;

import com.github.fge.jsonpatch.JsonPatchOperation;
import org.testng.annotations.Test;

import java.io.IOException;

@Test
public abstract class StandardJsonPatchOperationSerializationTest extends JsonPatchOperationSerializationTest {
    protected StandardJsonPatchOperationSerializationTest(final String operationName,
                                                          final Class<? extends JsonPatchOperation> c)
        throws IOException {
        super(operationName, "standard", c);
    }
}
