package com.github.fge.jsonpatch.operation;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.action.JsonPatchOperationAction;
import com.github.fge.jsonpatch.operation.policy.PathMissingPolicy;

import java.io.IOException;

/**
 * TODO: rename me!
 */
public class JsonPatchOperationComposed implements JsonPatchOperation
{
    private JsonPatchOperationAction action;
    private PathMissingPolicy pathMissingPolicy;

    public JsonPatchOperationComposed(JsonPatchOperationAction action, PathMissingPolicy pathMissingPolicy)
    {
        this.action = action;
        this.pathMissingPolicy = pathMissingPolicy;
    }

    public JsonNode apply(final JsonNode node)
            throws JsonPatchException
    {
        return action.apply(node, pathMissingPolicy);
    }

    public final void serialize(final JsonGenerator jgen,
        final SerializerProvider provider)
        throws IOException, JsonProcessingException
    {
        action.serialize(jgen, provider);
    }

    public final void serializeWithType(final JsonGenerator jgen,
        final SerializerProvider provider, final TypeSerializer typeSer)
        throws IOException, JsonProcessingException
    {
        action.serializeWithType(jgen, provider, typeSer);
    }
}
