package com.github.fge.jsonpatch;

public final class OmitOperationFactory extends JsonPatchOperationFactoryBase
{
    public String getOperationName()
    {
        return "omit";
    }
    public Class<? extends JsonPatchOperation> getOperationClass()
    {
        return OmitOperation.class;
    }
}
