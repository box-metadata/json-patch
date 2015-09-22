package com.github.fge.jsonpatch;

public final class TestOperationFactory extends JsonPatchOperationFactoryBase
{
    public String getOperationName()
    {
        return TestOperation.OPERATION_NAME;
    }
    public Class<? extends JsonPatchOperation> getOperationClass()
    {
        return TestOperation.class;
    }
}
