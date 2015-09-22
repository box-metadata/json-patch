package com.github.fge.jsonpatch;

public final class CopyOperationFactory extends JsonPatchOperationFactoryBase
{
    public String getOperationName()
    {
        return CopyOperation.OPERATION_NAME;
    }
    public Class<? extends JsonPatchOperation> getOperationClass()
    {
        return CopyOperation.class;
    }
}
