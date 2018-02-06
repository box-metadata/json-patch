package com.github.fge.jsonpatch.serialization;

import com.github.fge.jsonpatch.operation.OmitArrayOptionalOperation;

import java.io.IOException;

public class OmitArrayOptionalOperationSerializationTest
        extends ExtendedJsonPatchOperationSerializationTest
{
    public OmitArrayOptionalOperationSerializationTest()
            throws IOException
    {
        super(OmitArrayOptionalOperation.OPERATION_NAME);
    }
}
