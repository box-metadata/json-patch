package com.github.fge.jsonpatch.operation;

import java.io.IOException;

public class OmitArrayOptionalOperationTest
        extends ExtendedJsonPatchOperationTest
{
    public OmitArrayOptionalOperationTest()
        throws IOException
    {
        super(OmitArrayOptionalOperation.OPERATION_NAME);
    }
}
