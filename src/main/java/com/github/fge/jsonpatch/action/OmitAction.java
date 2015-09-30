package com.github.fge.jsonpatch.action;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.fge.jackson.JsonNumEquals;
import com.github.fge.jackson.jsonpointer.JsonPointer;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.JsonPatchMessages;
import com.github.fge.jsonpatch.operation.policy.PathMissingPolicy;
import com.github.fge.jsonpatch.properties.PathValueProperties;
import com.github.fge.msgsimple.bundle.MessageBundle;
import com.github.fge.msgsimple.load.MessageBundles;
import com.google.common.base.Equivalence;
import com.google.common.collect.Iterables;

import java.io.IOException;

public final class OmitAction
        implements JsonPatchOperationAction
{
    private final PathValueProperties properties;

    private static final MessageBundle BUNDLE
        = MessageBundles.getBundle(JsonPatchMessages.class);
    private static final Equivalence<JsonNode> EQUIVALENCE
        = JsonNumEquals.getInstance();

    public OmitAction(final PathValueProperties properties)
    {
        this.properties = properties;
    }

    public JsonNode apply(final JsonNode node, final PathMissingPolicy pathMissingPolicy)
        throws JsonPatchException
    {
        final String op = properties.getOp();
        final JsonPointer path = properties.getPath();
        final JsonNode value = properties.getValue();

        final JsonNode ret = node.deepCopy();
        if (path.isEmpty()) {
            if (EQUIVALENCE.equivalent(ret, value)) {
                return MissingNode.getInstance();
            } else {
                return ret;
            }
        }
        final JsonNode valueAtPath = path.path(ret);
        if (valueAtPath.isMissingNode()) {
            switch (pathMissingPolicy) {
                case THROW:
                    throw new JsonPatchException(BUNDLE.getMessage(
                        "jsonPatch.noSuchPath"));
                case SKIP:
                    return ret;
                default:
                    throw new JsonPatchException(BUNDLE.getMessage(
                        "jsonPatch.invalidPolicy"));
            }
        }

        if (EQUIVALENCE.equivalent(valueAtPath, value)) {
            final JsonNode parent = path.parent().get(ret);
            final String rawToken = Iterables.getLast(path).getToken().getRaw();
            if (parent.isObject())
                ((ObjectNode) parent).remove(rawToken);
            else
                ((ArrayNode) parent).remove(Integer.parseInt(rawToken));
        }
        return ret;
    }

    public final void serialize(final JsonGenerator jgen,
        final SerializerProvider provider)
        throws IOException, JsonProcessingException
    {
        properties.serialize(jgen, provider);
    }

    public final void serializeWithType(final JsonGenerator jgen,
        final SerializerProvider provider, final TypeSerializer typeSer)
        throws IOException, JsonProcessingException
    {
        properties.serializeWithType(jgen, provider, typeSer);
    }
}
