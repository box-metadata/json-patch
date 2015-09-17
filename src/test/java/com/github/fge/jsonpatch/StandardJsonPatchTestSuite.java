package com.github.fge.jsonpatch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public final class StandardJsonPatchTestSuite extends JsonPatchTestSuite
{
    public StandardJsonPatchTestSuite()
        throws IOException
    {
        super("standard", new ArrayList<JsonPatchOperationFactory>());
    }
}

