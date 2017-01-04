package com.github.fge.jsonpatch.operation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.fge.jackson.JsonNumEquals;
import com.github.fge.jackson.jsonpointer.JsonPointer;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.operation.policy.PathMissingPolicy;
import com.google.common.base.Equivalence;
import com.google.common.collect.Iterables;

/**
 * OmitOperationBase implements the basic concept of omitting the specified value at the
 * requested path.
 */
public abstract class OmitOperationBase extends PathValueOperation
{
    private static final Equivalence<JsonNode> EQUIVALENCE
        = JsonNumEquals.getInstance();

    private PathMissingPolicy pathMissingPolicy;

    public OmitOperationBase(final String op,
                             final JsonPointer path,
                             final JsonNode value,
                             final PathMissingPolicy pathMissingPolicy)
    {
        super(op, path, value);
        this.pathMissingPolicy = pathMissingPolicy;
    }

    @Override
    public JsonNode apply(final JsonNode node)
        throws JsonPatchException
    {
        final JsonNode ret = node.deepCopy();
        if (getPath().isEmpty()) {
            if (EQUIVALENCE.equivalent(ret, value)) {
                return MissingNode.getInstance();
            } else {
                return ret;
            }
        }
        final JsonNode valueAtPath = getPath().path(ret);
        if (valueAtPath.isMissingNode()) {
            switch (pathMissingPolicy) {
                case THROW:
                    throw new JsonPatchException(BUNDLE.getMessage(
                        "jsonPatch.noSuchPath"));
                case SKIP:
                    return ret;
            }
        }

        if (EQUIVALENCE.equivalent(valueAtPath, value)) {
            final JsonNode parent = getPath().parent().get(ret);
            final String rawToken = Iterables.getLast(getPath()).getToken().getRaw();
            if (parent.isObject())
                ((ObjectNode) parent).remove(rawToken);
            else
                ((ArrayNode) parent).remove(Integer.parseInt(rawToken));
        }
        return ret;
    }
}
