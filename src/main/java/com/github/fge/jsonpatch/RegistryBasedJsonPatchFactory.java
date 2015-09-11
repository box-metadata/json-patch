package com.github.fge.jsonpatch;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
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
    private Map<String, Class<? extends JsonPatchOperation>> operations;

    private static final MessageBundle BUNDLE
        = MessageBundles.getBundle(JsonPatchMessages.class);

    /**
     * Builds a JsonPatchFactory based on the patch operations we want to register
     */
    public static class RegistryBasedJsonPatchFactoryBuilder {
        private Map<String, Class<? extends JsonPatchOperation>> operations;

        public RegistryBasedJsonPatchFactoryBuilder() {
            this.operations = new HashMap<String, Class<? extends JsonPatchOperation>>();
        }

        public RegistryBasedJsonPatchFactoryBuilder addOperation(final String operationName, final Class<? extends JsonPatchOperation> c) {
            this.operations.put(operationName, c);
            return this;
        }
        public RegistryBasedJsonPatchFactoryBuilder addOperations(Map<String, Class<? extends JsonPatchOperation>> ops) {
            this.operations.putAll(ops);
            return this;
        }

        public RegistryBasedJsonPatchFactory build() {
            return new RegistryBasedJsonPatchFactory(this);
        }
    }

    private RegistryBasedJsonPatchFactory(RegistryBasedJsonPatchFactoryBuilder builder) {
        this.operations = new HashMap<String, Class<? extends JsonPatchOperation>>(builder.operations);
    }

    public Class<? extends JsonPatchOperation> getOperation(String opName) {
        return this.operations.get(opName);
    }

    public JsonPatch fromJson(JsonNode node)
            throws JsonPatchException, IOException, JsonProcessingException {
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

    private JsonPatchOperation operationFromJson(JsonNode node)
        throws JsonPatchException, IOException, JsonProcessingException {
        if (!node.isObject()) {
            throw new JsonPatchException(BUNDLE.getMessage(
                "jsonPatch.deserFailed"));
        }
        String op = node.get("op").asText();
        if (this.operations.containsKey(op)) {
            JsonPatchOperation parsedOp = JacksonUtils.getReader().withType(this.operations.get(op)).readValue(node);
            return parsedOp;
        } else {
            throw new JsonPatchException(BUNDLE.getMessage(
                "jsonPatch.deserFailed"));
        }
    }
}
