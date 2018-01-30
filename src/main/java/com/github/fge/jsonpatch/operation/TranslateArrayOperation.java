package com.github.fge.jsonpatch.operation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.jsonpointer.JsonPointer;

/**
 * Extended JSON Patch {@code translateArray} operation.
 * The operation will translate all items equivalent to {@code fromValue} to a {@code toValue} in the array at
 * {@code path} if they exist.
 * The operation will do nothing if no items in the array at {@code path} are equivalent to {@code fromValue}.
 * The operation will throw an exception if the node at {@code path} is not an array.
 */
public final class TranslateArrayOperation
		extends TranslateArrayOperationBase
{
	public static final String OPERATION_NAME = "translateArray";

	@JsonCreator
	public TranslateArrayOperation(@JsonProperty("path") final JsonPointer path,
							  @JsonProperty("from") final JsonNode fromValue,
							  @JsonProperty("value") final JsonNode toValue)
	{
		super(OPERATION_NAME, path, fromValue, toValue);
	}
}
