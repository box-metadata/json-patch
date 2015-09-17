package com.github.fge.jsonpatch;

import com.fasterxml.jackson.databind.JsonNode;

public interface JsonPatchOperationFactory
{
    /**
     * Gets the name of the JsonPatchOperation that this factory will create.
     * @return
     */
    public String getOperationName();

    /**
     * Gets the class of JsonPatchOperation that this factory will create.
     * @return
     */
    public Class<? extends JsonPatchOperation> getOperationClass();

    /**
     * Creates a JsonPatchOperation from a JsonNode
     * @param node The JsonNode to create the JsonPatchOperation from
     * @return The JsonPatchOperation
     * @throws JsonPatchException
     */
    public JsonPatchOperation create(JsonNode node) throws JsonPatchException;
}
