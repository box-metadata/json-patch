package com.github.fge.jsonpatch;

import org.testng.annotations.Test;

import java.io.IOException;

/**
 *
 */
@Test
public abstract class Rfc6902JsonPatchOperationTest extends JsonPatchOperationTest {

    protected Rfc6902JsonPatchOperationTest(final String operationName, final Class<? extends JsonPatchOperation> op)
        throws IOException
    {
        super(operationName, "rfc6902", op);
    }
}
