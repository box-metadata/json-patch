package com.github.fge.jsonpatch.operation;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JacksonUtils;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.JsonPatchMessages;
import com.github.fge.msgsimple.bundle.MessageBundle;
import com.github.fge.msgsimple.load.MessageBundles;

import java.io.IOException;

/**
 * The JsonPatchOperationFactoryBase implements a few common operations
 * for all JsonPatchOperationFactorys.
 */
public abstract class JsonPatchOperationFactoryBase implements JsonPatchOperationFactory
{
    private static final MessageBundle BUNDLE
        = MessageBundles.getBundle(JsonPatchMessages.class);

    /**
     * Gets the class of JsonPatchOperation that this factory will create.
     * @return
     */
    public abstract Class<? extends JsonPatchOperation> getOperationClass();

    public JsonPatchOperation create(JsonNode node)
        throws JsonPatchException
    {
        try {
            return JacksonUtils.getReader().withType(getOperationClass()).readValue(node);
        } catch (IOException e) {
            throw new JsonPatchException(BUNDLE.getMessage("jsonPatch.deserFailed"), e);
        }
    }
}
