package com.github.fge.jsonpatch;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

/**
 *
 */
public interface JsonPatchFactory {
    public JsonPatch fromJson(JsonNode node)
        throws JsonPatchException, IOException, JsonProcessingException;
}
