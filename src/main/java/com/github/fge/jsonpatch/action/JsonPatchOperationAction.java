package com.github.fge.jsonpatch.action;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.operation.policy.PathMissingPolicy;

/**
 *
 */
public interface JsonPatchOperationAction extends JsonSerializable
{
    public JsonNode apply(final JsonNode node, final PathMissingPolicy pathMissingPolicy)
        throws JsonPatchException;
}
