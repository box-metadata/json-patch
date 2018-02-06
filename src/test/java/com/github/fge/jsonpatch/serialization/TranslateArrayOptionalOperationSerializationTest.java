package com.github.fge.jsonpatch.serialization;

import com.github.fge.jsonpatch.operation.TranslateArrayOptionalOperation;

import java.io.IOException;

public class TranslateArrayOptionalOperationSerializationTest
		extends ExtendedJsonPatchOperationSerializationTest
{
    public TranslateArrayOptionalOperationSerializationTest()
			throws IOException
    {
        super(TranslateArrayOptionalOperation.OPERATION_NAME);
    }
}
