package com.github.fge.jsonpatch.operation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.github.fge.jackson.JsonNumEquals;
import com.github.fge.jackson.jsonpointer.JsonPointer;
import com.github.fge.jsonpatch.JsonPatchException;
import com.google.common.base.Equivalence;

/**
 * Extended JSON Patch {@code translateArray} operation.
 * The operation will translate all items equivalent to {@code fromValue} to a {@code toValue} in the array at
 * {@code path} if they exist.
 * The operation will do nothing if no items in the array at {@code path} are equivalent to {@code fromValue}.
 * The operation will throw an exception if the node at {@code path} is not an array.
 */
public final class TranslateArrayOperation
		extends PathDualValueOperation
{
	public static final String OPERATION_NAME = "translateArray";

	private static final Equivalence<JsonNode> EQUIVALENCE = JsonNumEquals.getInstance();

	@JsonCreator
	public TranslateArrayOperation(@JsonProperty("path") final JsonPointer path,
							  @JsonProperty("from") final JsonNode fromValue,
							  @JsonProperty("value") final JsonNode toValue)
	{
		super(OPERATION_NAME, path, fromValue, toValue);
	}

	@Override
	public JsonNode apply(final JsonNode node)
		throws JsonPatchException
	{
		final JsonNode ret = node.deepCopy();
		final JsonNode valueAtPath = path.path(ret);
		// Return node unchanged if the path is empty or there is no node after traversing the path
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
