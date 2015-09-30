package com.github.fge.jsonpatch.operation.policy;

/**
 * Specifies what the operation should do if the path is missing in the target JsonNode
 */
public enum PathMissingPolicy
{
    THROW, SKIP, NOT_APPLICABLE
}
