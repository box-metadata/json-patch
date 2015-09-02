package com.github.fge.jsonpatch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Builds a JsonPatchFactory based on the patch operations we want to register
 */
public class JsonPatchFactoryBuilder {
    private Map<String, Class<? extends JsonPatchOperation>> defaultOperations;
    private Map<String, Class<? extends JsonPatchOperation>> additionalOperations;

    public JsonPatchFactoryBuilder() {
        this.defaultOperations = new HashMap<String, Class<? extends JsonPatchOperation>>();
        this.defaultOperations.put("add", AddOperation.class);
        this.defaultOperations.put("copy", CopyOperation.class);
        this.defaultOperations.put("move", MoveOperation.class);
        this.defaultOperations.put("remove", RemoveOperation.class);
        this.defaultOperations.put("replace", ReplaceOperation.class);
        this.defaultOperations.put("test", TestOperation.class);
        this.additionalOperations = new HashMap<String, Class<? extends JsonPatchOperation>>();
    }

    public JsonPatchFactoryBuilder addOperation(final String operationName, final Class<? extends JsonPatchOperation> c) {
        additionalOperations.put(operationName, c);
        return this;
    }
    public JsonPatchFactory build() {
        Map<String, Class<? extends JsonPatchOperation>> operations = new HashMap<String, Class<? extends JsonPatchOperation>>();
        operations.putAll(defaultOperations);
        operations.putAll(additionalOperations);
        return new JsonPatchFactory(operations);
    }
}
