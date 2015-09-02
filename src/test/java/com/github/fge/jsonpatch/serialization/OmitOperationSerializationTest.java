package com.github.fge.jsonpatch.serialization;

import com.github.fge.jsonpatch.OmitOperation;

import java.io.IOException;

/**
 *
 */
public final class OmitOperationSerializationTest extends ExtendedJsonPatchOperationSerializationTest {
    public OmitOperationSerializationTest() throws IOException {
        super("omit", OmitOperation.class);
    }
}
