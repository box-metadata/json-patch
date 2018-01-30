package com.github.fge.jsonpatch.operation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.fge.jackson.JsonNumEquals;
import com.github.fge.jackson.jsonpointer.JsonPointer;
import com.github.fge.jsonpatch.JsonPatchException;
import com.google.common.base.Equivalence;
import com.google.common.collect.Iterables;

/**
 * OmitArrayOperationBase implements the basic concept of omitting the specified value from an array of items
 * at the requested path.
 */
public abstract class OmitArrayOperationBase
        extends PathValueOperation
{
    private static final Equivalence<JsonNode> EQUIVALENCE = JsonNumEquals.getInstance();

    public OmitArrayOperationBase(final String op,
                             final JsonPointer path,
                             final JsonNode value)
    {
        super(op, path, value);
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

        final ArrayNode target = (ArrayNode) valueAtPath;
        int size = target.size();
        int index = 0;
        boolean targetChanged = false;
        while (index < size) {
            final JsonNode element = target.get(index);
            if (EQUIVALENCE.equivalent(element, value)) {
                target.remove(index);
                index--;
                size = target.size();
                targetChanged = true;
            }
            index++;
        }

        // If after the operation the array has no elements left, remove the parent node
        if (targetChanged && size == 0) {
            final JsonNode parentNode = path.parent().get(ret);
            final String raw = Iterables.getLast(path).getToken().getRaw();
            if (parentNode.isObject()) {
                ((ObjectNode) parentNode).remove(raw);
            }
        }

        return ret;
    }
}
