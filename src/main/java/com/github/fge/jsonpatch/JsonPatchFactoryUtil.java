package com.github.fge.jsonpatch;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Utilities for JsonPatchFactory. It provides a default implementation of JsonPatchFactory that
 * has all the RFC6902 operations registered.
 */
public class JsonPatchFactoryUtil {
    private static Map<String, Class<? extends JsonPatchOperation>> DEFAULT_OPERATIONS =
        Collections.unmodifiableMap(
            new HashMap<String, Class<? extends JsonPatchOperation>>() {{
                put("add", AddOperation.class);
                put("copy", CopyOperation.class);
                put("move", MoveOperation.class);
                put("remove", RemoveOperation.class);
                put("replace", ReplaceOperation.class);
                put("test", TestOperation.class);
            }}
        );
    private static JsonPatchFactory DEFAULT_FACTORY;

    public static Map<String, Class<? extends JsonPatchOperation>> defaultOperations() {
        return DEFAULT_OPERATIONS;
    }
    public static JsonPatchFactory defaultFactory() {
        return DEFAULT_FACTORY;
    }

    private JsonPatchFactoryUtil() {
    }
}
