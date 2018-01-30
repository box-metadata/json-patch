package com.github.fge.jsonpatch.operation;

import java.io.IOException;

public class OmitArrayOperationTest
        extends ExtendedJsonPatchOperationTest
{
    public OmitArrayOperationTest()
        throws IOException
    {
        super(OmitArrayOperation.OPERATION_NAME);
    }
}
