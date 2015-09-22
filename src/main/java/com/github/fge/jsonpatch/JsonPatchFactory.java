package com.github.fge.jsonpatch;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

/**
 * A JsonPatchFactory is able to create a JsonPatch from a JsonNode.
 */
public interface JsonPatchFactory
{
    /**
     * Creates a JsonPatch from a JsonNode
     * @param node The node to process
     * @return The JsonPatch that can be applied to a JsonNode
     * @throws JsonPatchException
     */
    public JsonPatch fromJson(JsonNode node) throws JsonPatchException;
}
