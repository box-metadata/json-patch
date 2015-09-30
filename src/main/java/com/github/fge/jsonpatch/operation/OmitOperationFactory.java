package com.github.fge.jsonpatch.operation;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JacksonUtils;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.JsonPatchMessages;
import com.github.fge.jsonpatch.action.OmitAction;
import com.github.fge.jsonpatch.operation.policy.PathMissingPolicy;
import com.github.fge.jsonpatch.properties.PathValueProperties;
import com.github.fge.msgsimple.bundle.MessageBundle;
import com.github.fge.msgsimple.load.MessageBundles;

import java.io.IOException;

public final class OmitOperationFactory implements JsonPatchOperationFactory
{
    private static final MessageBundle BUNDLE
            = MessageBundles.getBundle(JsonPatchMessages.class);

    public String getOperationName()
    {
        return OmitOperation.OPERATION_NAME;
    }
    public JsonPatchOperation create(JsonNode node)
            throws JsonPatchException
    {
        try {
            PathValueProperties properties = JacksonUtils.getReader().withType(PathValueProperties.class).readValue(node);
            return new JsonPatchOperationComposed(new OmitAction(properties), PathMissingPolicy.THROW);
        } catch (IOException e) {
            throw new JsonPatchException(BUNDLE.getMessage("jsonPatch.deserFailed"), e);
        }
    }
}
