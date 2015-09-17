package com.github.fge.jsonpatch;

public final class RemoveOperationFactory extends JsonPatchOperationFactoryBase
{
    public String getOperationName()
    {
        return "remove";
    }
    public Class<? extends JsonPatchOperation> getOperationClass()
    {
        return RemoveOperation.class;
    }
}
