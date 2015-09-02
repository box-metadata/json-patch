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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class JsonPatchFactory {
    private Map<String, Class<? extends JsonPatchOperation>> operations;

    private static final MessageBundle BUNDLE
        = MessageBundles.getBundle(JsonPatchMessages.class);

    public JsonPatchFactory(Map<String, Class<? extends JsonPatchOperation>> operations) {
        this.operations = operations;
    }

    public Map<String, Class<? extends JsonPatchOperation>> getOperations() {
        return this.operations;
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

    public JsonPatchOperation operationFromJson(JsonNode node)
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
