package com.github.fge.jsonpatch;

public final class AddOperationFactory extends JsonPatchOperationFactoryBase
{
    public String getOperationName()
    {
        return "add";
    }
    public Class<? extends JsonPatchOperation> getOperationClass()
    {
        return AddOperation.class;
    }
}
