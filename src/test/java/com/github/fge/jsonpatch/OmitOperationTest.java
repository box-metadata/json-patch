package com.github.fge.jsonpatch;

import java.io.IOException;

/**
 *
 */
public final class OmitOperationTest extends ExtendedJsonPatchOperationTest {
    public OmitOperationTest()
       throws IOException  {
        super("omit", OmitOperation.class);
    }
}
