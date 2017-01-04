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
 * TranslateOperationBase implements the basic concept of translating from one specified value to another
 * at the requested path.
 */
public abstract class TranslateOperationBase extends PathDualValueOperation
{
    private static final Equivalence<JsonNode> EQUIVALENCE
        = JsonNumEquals.getInstance();

    private PathMissingPolicy pathMissingPolicy;

    public TranslateOperationBase(final String op,
                             final JsonPointer path,
                             final JsonNode fromValue,
                             final JsonNode toValue,
                             final PathMissingPolicy pathMissingPolicy)
    {
        super(op, path, fromValue, toValue);
        this.pathMissingPolicy = pathMissingPolicy;
    }

    @Override
    public JsonNode apply(final JsonNode node)
        throws JsonPatchException
    {
        final JsonNode ret = node.deepCopy();
        final JsonNode toValueRet = toValue.deepCopy();
        if (getPath().isEmpty()) {
            if (EQUIVALENCE.equivalent(ret, fromValue)) {
                return toValueRet;
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

        if (EQUIVALENCE.equivalent(valueAtPath, fromValue)) {
            final JsonNode parent = getPath().parent().get(ret);
            final String rawToken = Iterables.getLast(getPath()).getToken().getRaw();
            if (parent.isObject())
                ((ObjectNode) parent).set(rawToken, toValueRet);
            else
                ((ArrayNode) parent).set(Integer.parseInt(rawToken), toValueRet);
        }
        return ret;
    }
}
