package com.github.fge.jsonpatch.operation;

import com.github.fge.jsonpatch.annotation.ExtendedJsonPatchOperationTypeInfoAnnotations;
import org.testng.annotations.Test;

import java.io.IOException;

@Test
public abstract class ExtendedJsonPatchOperationTest extends JsonPatchOperationTest
{
    protected ExtendedJsonPatchOperationTest(final String operationName)
        throws IOException
    {
        super("extended", operationName, ExtendedJsonPatchOperationTypeInfoAnnotations.class);
    }
}