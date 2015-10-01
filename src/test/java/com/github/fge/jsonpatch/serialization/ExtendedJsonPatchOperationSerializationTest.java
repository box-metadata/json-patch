package com.github.fge.jsonpatch.serialization;

import com.github.fge.jsonpatch.JsonPatchDeserializer;
import com.github.fge.jsonpatch.JsonPatchFactoryUtil;
import org.testng.annotations.Test;

import java.io.IOException;

@Test
public abstract class ExtendedJsonPatchOperationSerializationTest extends JsonPatchOperationSerializationTest
{
    protected ExtendedJsonPatchOperationSerializationTest(final String operationName)
        throws IOException
    {
        super("extended", operationName, new JsonPatchDeserializer(JsonPatchFactoryUtil.extendedFactory()));
    }
}

