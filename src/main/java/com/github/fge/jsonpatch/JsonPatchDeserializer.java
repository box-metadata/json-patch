package com.github.fge.jsonpatch;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

/**
 * JsonPatchDeserializer allows you to register a custom JsonPatchFactory for jackson to use
 * during deserialization.
 */
public class JsonPatchDeserializer
    extends JsonDeserializer<JsonPatch>
{
    private JsonPatchFactory factory;
    public JsonPatchDeserializer(JsonPatchFactory factory)
    {
        this.factory = factory;
    }

    @Override
    public JsonPatch deserialize(final JsonParser jp,
        final DeserializationContext ctxt)
        throws IOException, JsonProcessingException
    {
        final JsonNode node = jp.readValueAsTree();
        try {
            return factory.fromJson(node);
        } catch (JsonPatchException e) {
            throw ctxt.mappingException(JsonPatchOperation.class);
        }
    }
}
