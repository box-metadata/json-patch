package com.github.fge.jsonpatch.serialization;

import com.github.fge.jsonpatch.JsonPatchOperationFactory;
import org.testng.annotations.Test;

import java.io.IOException;

@Test
public abstract class ExtendedJsonPatchOperationSerializationTest extends JsonPatchOperationSerializationTest
{
    protected ExtendedJsonPatchOperationSerializationTest(final JsonPatchOperationFactory operationFactory)
        throws IOException
    {
        super("extended", operationFactory);
    }
}

