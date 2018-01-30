package com.github.fge.jsonpatch.operation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.github.fge.jackson.JsonNumEquals;
import com.github.fge.jackson.jsonpointer.JsonPointer;
import com.github.fge.jsonpatch.JsonPatchException;
import com.google.common.base.Equivalence;

/**
 * TranslateArrayOperationBase implements the basic concept of translating from one specified value to another
 * at the requested path for each element in the array.
 */
public abstract class TranslateArrayOperationBase extends PathDualValueOperation
{
    private static final Equivalence<JsonNode> EQUIVALENCE
        = JsonNumEquals.getInstance();

    public TranslateArrayOperationBase(final String op,
                             final JsonPointer path,
                             final JsonNode fromValue,
                             final JsonNode toValue)
    {
        super(op, path, fromValue, toValue);
    }

    @Override
    public JsonNode apply(final JsonNode node)
        throws JsonPatchException
    {
        final JsonNode ret = node.deepCopy();
        final JsonNode valueAtPath = path.path(ret);
        // Return node unchanged if the path is empty or there is no node at the path
        if (path.isEmpty() || valueAtPath.isMissingNode()) {
            return ret;
        }

        // Since this operation is only valid for array nodes, throw an exception if the node is not an array
        if (!valueAtPath.isArray())
            throw new JsonPatchException(BUNDLE.getMessage(
                "jsonPatch.notAnArray"));

        final JsonNode toValueRet = toValue.deepCopy();
        final ArrayNode target = (ArrayNode) valueAtPath;
        final int size = target.size();
        for (int index = 0; index < size; index++){
            final JsonNode element = target.get(index);
            if (EQUIVALENCE.equivalent(element, fromValue)) {
                target.set(index, toValueRet);
            }
        }

        return ret;
    }
}
