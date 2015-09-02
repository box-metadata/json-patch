package com.github.fge.jsonpatch;

import java.io.IOException;
import java.util.HashMap;

public final class ExtendedJsonPatchTestSuite extends JsonPatchTestSuite
{
    public ExtendedJsonPatchTestSuite()
        throws IOException
    {
        super("extended", getAdditionalOperations());
    }
    private static HashMap<String, Class<? extends JsonPatchOperation>> getAdditionalOperations() {
        HashMap<String, Class<? extends JsonPatchOperation>> additionalOperations = new HashMap<String, Class<? extends JsonPatchOperation>>();
        additionalOperations.put("omit", OmitOperation.class);
        return additionalOperations;
    }
}

