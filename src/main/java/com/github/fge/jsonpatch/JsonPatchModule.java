package com.github.fge.jsonpatch;

import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.fge.jsonpatch.operation.AddOperation;
import com.github.fge.jsonpatch.operation.CopyOperation;
import com.github.fge.jsonpatch.operation.MoveOperation;
import com.github.fge.jsonpatch.operation.RemoveOperation;
import com.github.fge.jsonpatch.operation.ReplaceOperation;
import com.github.fge.jsonpatch.operation.TestOperation;

/**
 * This module registers the standard JSON-PATCH operations with Jackson.
 */
public class JsonPatchModule extends SimpleModule {
    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     */
    public JsonPatchModule() {
        registerSubtypes(
                new NamedType(AddOperation.class, AddOperation.OPERATION_NAME),
                new NamedType(CopyOperation.class, CopyOperation.OPERATION_NAME),
                new NamedType(MoveOperation.class, MoveOperation.OPERATION_NAME),
                new NamedType(RemoveOperation.class, RemoveOperation.OPERATION_NAME),
                new NamedType(ReplaceOperation.class, ReplaceOperation.OPERATION_NAME),
                new NamedType(TestOperation.class, TestOperation.OPERATION_NAME)
        );
    }
}
