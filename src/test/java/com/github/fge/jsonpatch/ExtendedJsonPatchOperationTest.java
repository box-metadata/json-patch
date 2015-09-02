package com.github.fge.jsonpatch;

import org.testng.annotations.Test;

import java.io.IOException;

/**
 *
 */
@Test
public abstract class ExtendedJsonPatchOperationTest extends JsonPatchOperationTest {

    protected ExtendedJsonPatchOperationTest(final String operationName, final Class<? extends JsonPatchOperation> op)
        throws IOException
    {
        super(operationName, "extended", op);
    }
}
