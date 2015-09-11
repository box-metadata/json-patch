package com.github.fge.jsonpatch;

import org.testng.annotations.Test;

import java.io.IOException;

/**
 *
 */
@Test
public abstract class StandardJsonPatchOperationTest extends JsonPatchOperationTest {

    protected StandardJsonPatchOperationTest(final String operationName, final Class<? extends JsonPatchOperation> op)
        throws IOException
    {
        super(operationName, "standard", op);
    }
}
