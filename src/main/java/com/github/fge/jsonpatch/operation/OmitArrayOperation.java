package com.github.fge.jsonpatch.operation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.jsonpointer.JsonPointer;

/**
 * Extended JSON Patch {@code omitArray} operation.
 *
 * The operation will remove all the items equivalent to {@code value} from the array at {@code path}.
 * The operation will remove the parent node if applying the operation to the array results in an empty array.
 * The operation will throw an exception if the node at {@code path} is not an array.
 * The operation will do nothing if no items in the array at {@code path} are equivalent to {@code value}.
 */
public final class OmitArrayOperation
        extends OmitArrayOperationBase
{
    public static final String OPERATION_NAME = "omitArray";

    @JsonCreator
    public OmitArrayOperation(@JsonProperty("path") final JsonPointer path,
                         @JsonProperty("value") final JsonNode value)
    {
        super(OPERATION_NAME, path, value);
    }
}

