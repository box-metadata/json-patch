package com.github.fge.jsonpatch;

public final class AddOperationFactory extends JsonPatchOperationFactoryBase
{
    public String getOperationName()
    {
        return AddOperation.OPERATION_NAME;
    }
    public Class<? extends JsonPatchOperation> getOperationClass()
    {
        return AddOperation.class;
    }
}
