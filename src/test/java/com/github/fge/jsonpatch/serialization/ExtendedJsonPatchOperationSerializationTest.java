package com.github.fge.jsonpatch.serialization;

import com.github.fge.jsonpatch.JsonPatchOperation;
import org.testng.annotations.Test;

import java.io.IOException;

@Test
public abstract class ExtendedJsonPatchOperationSerializationTest extends JsonPatchOperationSerializationTest
{
    protected ExtendedJsonPatchOperationSerializationTest(final String operationName,
        final Class<? extends JsonPatchOperation> c)
        throws IOException {
        super(operationName, "extended", c);
    }
}

