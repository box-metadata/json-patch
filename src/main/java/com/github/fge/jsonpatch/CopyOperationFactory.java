package com.github.fge.jsonpatch;

public final class CopyOperationFactory extends JsonPatchOperationFactoryBase
{
    public String getOperationName()
    {
        return "copy";
    }
    public Class<? extends JsonPatchOperation> getOperationClass()
    {
        return CopyOperation.class;
    }
}
