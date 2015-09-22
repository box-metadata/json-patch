package com.github.fge.jsonpatch;

import org.testng.annotations.Test;

import java.io.IOException;

@Test
public abstract class StandardJsonPatchOperationTest extends JsonPatchOperationTest
{
    protected StandardJsonPatchOperationTest(final JsonPatchOperationFactory operationFactory)
        throws IOException
    {
        super("standard", operationFactory);
    }
}
