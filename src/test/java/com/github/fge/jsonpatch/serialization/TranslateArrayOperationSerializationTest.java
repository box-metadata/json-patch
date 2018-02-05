package com.github.fge.jsonpatch.serialization;

import com.github.fge.jsonpatch.operation.TranslateArrayOperation;

import java.io.IOException;

public class TranslateArrayOperationSerializationTest
		extends ExtendedJsonPatchOperationSerializationTest
{
    public TranslateArrayOperationSerializationTest()
			throws IOException
    {
        super(TranslateArrayOperation.OPERATION_NAME);
    }
}
