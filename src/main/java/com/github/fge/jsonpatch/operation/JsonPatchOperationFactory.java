package com.github.fge.jsonpatch.operation;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonpatch.JsonPatchException;

public interface JsonPatchOperationFactory
{
    /**
     * Gets the name of the JsonPatchOperation that this factory will create.
     * @return
     */
    public String getOperationName();

    /**
     * Creates a JsonPatchOperation from a JsonNode
     * @param node The JsonNode to create the JsonPatchOperation from
     * @return The JsonPatchOperation
     * @throws com.github.fge.jsonpatch.JsonPatchException
     */
    public JsonPatchOperation create(JsonNode node) throws JsonPatchException;
}
