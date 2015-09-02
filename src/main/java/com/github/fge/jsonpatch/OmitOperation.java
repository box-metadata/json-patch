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
 *
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
        if (path.isEmpty()) {
            if (EQUIVALENCE.equivalent(node, value)) {
                return MissingNode.getInstance();
            } else {
                return node.deepCopy();
            }
        }
        if (path.path(node).isMissingNode())
            throw new JsonPatchException(BUNDLE.getMessage(
                "jsonPatch.noSuchPath"));

        final JsonNode ret = node.deepCopy();
        if (EQUIVALENCE.equivalent(path.path(ret), value)) {
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
