package com.github.fge.jsonpatch;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JacksonUtils;
import com.github.fge.msgsimple.bundle.MessageBundle;
import com.github.fge.msgsimple.load.MessageBundles;

import java.io.IOException;
import java.util.*;

/**
 * RegistryBasedJsonPatchFactory is responsible for parsing JsonNodes into JsonPatch objects based on
 * which JsonPatchOperations it knows about in its registry.
 */
public class RegistryBasedJsonPatchFactory implements JsonPatchFactory
{
    private final Map<String, JsonPatchOperationFactory> operationFactories;

    private static final MessageBundle BUNDLE
        = MessageBundles.getBundle(JsonPatchMessages.class);

    /**
     * Builds a JsonPatchFactory based on the patch operations we want to register
     */
    public static class Builder
    {
        private Map<String, JsonPatchOperationFactory> operationFactories;

        public Builder()
        {
            this.operationFactories = new HashMap<String, JsonPatchOperationFactory>();
        }

        public Builder addOperation(final JsonPatchOperationFactory operationFactory)
        {
            this.operationFactories.put(operationFactory.getOperationName(), operationFactory);
            return this;
        }
        public Builder addOperations(final List<JsonPatchOperationFactory> ops)
        {
            for (JsonPatchOperationFactory operationFactory : ops) {
                this.operationFactories.put(operationFactory.getOperationName(), operationFactory);
            }
            return this;
        }

        public RegistryBasedJsonPatchFactory build()
        {
            return new RegistryBasedJsonPatchFactory(this);
        }
    }

    private RegistryBasedJsonPatchFactory(final Builder builder)
    {
        this.operationFactories = new HashMap<String, JsonPatchOperationFactory>(builder.operationFactories);
    }

    public JsonPatchOperationFactory getOperation(final String opName)
    {
        return this.operationFactories.get(opName);
    }

    public JsonPatch fromJson(final JsonNode node)
            throws JsonPatchException
    {
        BUNDLE.checkNotNull(node, "jsonPatch.nullInput");
        if (!node.isArray()) {
            throw new JsonPatchException(BUNDLE.getMessage(
                "jsonPatch.deserFailed"));
        }
        Iterator<JsonNode> patchOps = node.elements();
        List<JsonPatchOperation> parsedOps = new ArrayList<JsonPatchOperation>();
        while (patchOps.hasNext()) {
            JsonNode patchOp = patchOps.next();
            parsedOps.add(operationFromJson(patchOp));
        }
        return new JsonPatch(parsedOps);
    }

    private JsonPatchOperation operationFromJson(final JsonNode node)
            throws JsonPatchException
    {
        if (!node.isObject()) {
            throw new JsonPatchException(BUNDLE.getMessage(
                "jsonPatch.deserFailed"));
        }
        String op = node.get("op").asText();
        if (this.operationFactories.containsKey(op)) {
            JsonPatchOperation parsedOp = this.operationFactories.get(op).create(node);
            return parsedOp;
        } else {
            throw new JsonPatchException(BUNDLE.getMessage(
                "jsonPatch.deserFailed"));
        }
    }
}
