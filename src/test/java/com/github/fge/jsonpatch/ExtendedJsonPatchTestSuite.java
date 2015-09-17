package com.github.fge.jsonpatch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public final class ExtendedJsonPatchTestSuite extends JsonPatchTestSuite
{
    public ExtendedJsonPatchTestSuite()
        throws IOException
    {
        super("extended", getAdditionalOperations());
    }
    private static ArrayList<JsonPatchOperationFactory> getAdditionalOperations()
    {
        ArrayList<JsonPatchOperationFactory> additionalOperations = new ArrayList<JsonPatchOperationFactory>();
        additionalOperations.add(new OmitOperationFactory());
        return additionalOperations;
    }
}

