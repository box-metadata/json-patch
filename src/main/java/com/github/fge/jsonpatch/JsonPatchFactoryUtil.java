package com.github.fge.jsonpatch;

import java.util.*;

/**
 * Utilities for JsonPatchFactory. It provides a default implementation of JsonPatchFactory that
 * has all the RFC6902 operations registered.
 */
public final class JsonPatchFactoryUtil
{
    private static final List<JsonPatchOperationFactory> DEFAULT_OPERATIONS =
        Collections.unmodifiableList(
            new ArrayList<JsonPatchOperationFactory>() {{
                add(new AddOperationFactory());
                add(new CopyOperationFactory());
                add(new MoveOperationFactory());
                add(new RemoveOperationFactory());
                add(new ReplaceOperationFactory());
                add(new TestOperationFactory());
            }}
        );
    private static final List<JsonPatchOperationFactory> EXTENDED_OPERATIONS =
        Collections.unmodifiableList(
            new ArrayList<JsonPatchOperationFactory>() {{
                add(new OmitOperationFactory());
            }}
        );
    private static final JsonPatchFactory DEFAULT_FACTORY =
            (new RegistryBasedJsonPatchFactory.Builder())
                .addOperations(DEFAULT_OPERATIONS)
                .build();

    private static final JsonPatchFactory EXTENDED_FACTORY =
            (new RegistryBasedJsonPatchFactory.Builder())
                .addOperations(DEFAULT_OPERATIONS)
                .addOperations(EXTENDED_OPERATIONS)
                .build();

    public static List<JsonPatchOperationFactory> defaultOperations()
    {
        return DEFAULT_OPERATIONS;
    }
    public static JsonPatchFactory defaultFactory()
    {
        return DEFAULT_FACTORY;
    }
    public static List<JsonPatchOperationFactory> extendedOperations()
    {
        return EXTENDED_OPERATIONS;
    }
    public static JsonPatchFactory extendedFactory()
    {
        return EXTENDED_FACTORY;
    }

    private JsonPatchFactoryUtil() {}
}
