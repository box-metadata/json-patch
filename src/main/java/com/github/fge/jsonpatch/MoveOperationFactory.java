package com.github.fge.jsonpatch;

public final class MoveOperationFactory extends JsonPatchOperationFactoryBase
{
    public String getOperationName()
    {
        return MoveOperation.OPERATION_NAME;
    }
    public Class<? extends JsonPatchOperation> getOperationClass()
    {
        return MoveOperation.class;
    }
}
