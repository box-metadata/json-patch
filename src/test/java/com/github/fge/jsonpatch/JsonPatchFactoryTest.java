package com.github.fge.jsonpatch;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.msgsimple.bundle.MessageBundle;
import com.github.fge.msgsimple.load.MessageBundles;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;

public final class JsonPatchFactoryTest {
    private final RegistryBasedJsonPatchFactory factory;

    private static final MessageBundle BUNDLE
        = MessageBundles.getBundle(JsonPatchMessages.class);

    public JsonPatchFactoryTest() {
        factory = new RegistryBasedJsonPatchFactory.RegistryBasedJsonPatchFactoryBuilder()
                .addOperation("add", AddOperation.class)
                .build();
    }
    @Test
    public void fromJsonParsesPatchCorrectly()
        throws IOException, JsonPatchException, JsonProcessingException {
        JsonNode node = JsonLoader.fromString("[{\"op\":\"add\", \"path\": \"\", \"value\": \"foo\"}]");
        JsonPatch patch = factory.fromJson(node);
        assertTrue(true, "Should have parsed the json patch without error");
    }
    @Test
    public void fromJsonThrowsIfOpNotRecognized()
        throws IOException, JsonPatchException, JsonProcessingException {
        JsonNode node = JsonLoader.fromString("[{\"op\":\"remove\", \"path\": \"\"}]");
        try {
            JsonPatch patch = factory.fromJson(node);
            fail("Should have thrown exception because op not recognized");
        } catch (JsonPatchException e) {
            assertEquals(BUNDLE.getMessage("jsonPatch.deserFailed"), e.getMessage());
        }
    }

    @Test
    public void nullInputsDuringBuildAreRejected()
        throws IOException, JsonPatchException, JsonProcessingException
    {
        try {
            factory.fromJson(null);
            fail("No exception thrown!!");
        } catch (NullPointerException e) {
            assertEquals(e.getMessage(), BUNDLE.getMessage(
                "jsonPatch.nullInput"));
        }
    }

}
