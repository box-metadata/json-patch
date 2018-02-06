package com.github.fge.jsonpatch;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.github.fge.jackson.JacksonUtils;
import com.github.fge.jsonpatch.operation.*;

/**
 * ExtendedJsonPatchFactory can create a JsonPatchFactory configured to work with the extended set of JSON Patch operations.
 */
public class ExtendedJsonPatchFactory
{
    public static JsonPatchFactory create()
    {
        ObjectMapper mapper = JacksonUtils.newMapper();
        mapper.registerSubtypes(
                new NamedType(AddOperation.class, AddOperation.OPERATION_NAME),
                new NamedType(CopyOperation.class, CopyOperation.OPERATION_NAME),
                new NamedType(MoveOperation.class, MoveOperation.OPERATION_NAME),
                new NamedType(MoveOptionalOperation.class, MoveOptionalOperation.OPERATION_NAME),
                new NamedType(RemoveOperation.class, RemoveOperation.OPERATION_NAME),
                new NamedType(RemoveOptionalOperation.class, RemoveOptionalOperation.OPERATION_NAME),
                new NamedType(ReplaceOperation.class, ReplaceOperation.OPERATION_NAME),
                new NamedType(TestOperation.class, TestOperation.OPERATION_NAME),
                new NamedType(OmitOperation.class, OmitOperation.OPERATION_NAME),
                new NamedType(OmitOptionalOperation.class, OmitOptionalOperation.OPERATION_NAME),
                new NamedType(OmitArrayOptionalOperation.class, OmitArrayOptionalOperation.OPERATION_NAME),
                new NamedType(TranslateOperation.class, TranslateOperation.OPERATION_NAME),
                new NamedType(TranslateOptionalOperation.class, TranslateOptionalOperation.OPERATION_NAME),
                new NamedType(TranslateArrayOptionalOperation.class, TranslateArrayOptionalOperation.OPERATION_NAME)
        );
        return new JsonPatchFactory(mapper);
    }
}
