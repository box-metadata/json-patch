package com.github.fge.jsonpatch.operation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.fge.jackson.JsonNumEquals;
import com.github.fge.jackson.jsonpointer.JsonPointer;
import com.github.fge.jsonpatch.JsonPatchException;
import com.google.common.base.Equivalence;
import com.google.common.collect.Iterables;

/**
 * Extended JSON Patch {@code omitArray?} operation.
 *
 * The operation will remove all the items equivalent to {@code value} from the array at {@code path}.
 * The operation will remove the parent node if applying the operation to the array results in an empty array.
 * The operation will throw an exception if the node at {@code path} is not an array.
 * The operation will do nothing if no items in the array at {@code path} are equivalent to {@code value}.
 */
public final class OmitArrayOptionalOperation
        extends PathValueOperation
{
    public static final String OPERATION_NAME = "omitArray?";
    private static final Equivalence<JsonNode> EQUIVALENCE = JsonNumEquals.getInstance();

    @JsonCreator
    public OmitArrayOptionalOperation(@JsonProperty("path") final JsonPointer path,
									  @JsonProperty("value") final JsonNode value)
    {
        super(OPERATION_NAME, path, value);
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
        // Note after removing an element from an ArrayNode, the remaining elements are shifted to the left
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

