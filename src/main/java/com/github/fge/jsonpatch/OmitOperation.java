package com.github.fge.jsonpatch;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.fge.jackson.JsonNumEquals;
import com.github.fge.jackson.jsonpointer.JsonPointer;
import com.google.common.base.Equivalence;
import com.google.common.collect.Iterables;

/**
 * Extended JSON Patch {@code omit} operation.
 *
 * The operation will remove the {@code value} at {@code path} if it exists.
 * It will do nothing if the actual value at {@code path} is not equal to {@code value}.
 * It will throw a "no such path" error if there is no value at {@code path}.
 */
public class OmitOperation extends PathValueOperation {

    private static final Equivalence<JsonNode> EQUIVALENCE
        = JsonNumEquals.getInstance();
    @JsonCreator
    public OmitOperation(@JsonProperty("path") final JsonPointer path,
                         @JsonProperty("value") final JsonNode value)
    {
        super("omit", path, value);
    }

    @Override
    public JsonNode apply(final JsonNode node)
        throws JsonPatchException
    {
        final JsonNode ret = node.deepCopy();
        if (path.isEmpty()) {
            if (EQUIVALENCE.equivalent(ret, value)) {
                return MissingNode.getInstance();
            } else {
                return ret;
            }
        }
        final JsonNode valueAtPath = path.path(ret);
        if (valueAtPath.isMissingNode())
            throw new JsonPatchException(BUNDLE.getMessage(
                "jsonPatch.noSuchPath"));

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
}

