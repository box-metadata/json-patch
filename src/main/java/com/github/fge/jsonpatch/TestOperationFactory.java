package com.github.fge.jsonpatch;

public final class TestOperationFactory extends JsonPatchOperationFactoryBase
{
    public String getOperationName()
    {
        return "test";
    }
    public Class<? extends JsonPatchOperation> getOperationClass()
    {
        return TestOperation.class;
    }
}
