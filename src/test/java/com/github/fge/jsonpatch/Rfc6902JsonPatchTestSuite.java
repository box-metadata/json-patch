package com.github.fge.jsonpatch;

import java.io.IOException;
import java.util.HashMap;

public final class Rfc6902JsonPatchTestSuite extends JsonPatchTestSuite
{
    public Rfc6902JsonPatchTestSuite()
        throws IOException
    {
        super("rfc6902", new HashMap<String, Class<? extends JsonPatchOperation>>());
    }
}

