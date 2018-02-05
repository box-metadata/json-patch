package com.github.fge.jsonpatch.serialization;

import com.github.fge.jsonpatch.operation.OmitArrayOperation;

import java.io.IOException;

public class OmitArrayOperationSerializationTest
        extends ExtendedJsonPatchOperationSerializationTest
{
    public OmitArrayOperationSerializationTest()
            throws IOException
    {
        super(OmitArrayOperation.OPERATION_NAME);
    }
}
