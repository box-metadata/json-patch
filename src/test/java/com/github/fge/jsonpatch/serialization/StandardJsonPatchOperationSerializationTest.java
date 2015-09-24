package com.github.fge.jsonpatch.serialization;

import com.github.fge.jsonpatch.JsonPatchDeserializer;
import com.github.fge.jsonpatch.JsonPatchFactoryUtil;
import com.github.fge.jsonpatch.JsonPatchOperationFactory;
import org.testng.annotations.Test;

import java.io.IOException;

@Test
public abstract class StandardJsonPatchOperationSerializationTest extends JsonPatchOperationSerializationTest
{
    protected StandardJsonPatchOperationSerializationTest(final JsonPatchOperationFactory operationFactory)
        throws IOException
    {
        super("standard", operationFactory, new JsonPatchDeserializer(JsonPatchFactoryUtil.defaultFactory()));
    }
}
