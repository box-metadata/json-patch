package com.github.fge.jsonpatch;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.msgsimple.bundle.MessageBundle;
import com.github.fge.msgsimple.load.MessageBundles;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.*;

public final class RegistryBasedJsonPatchFactoryTest
{
    private final RegistryBasedJsonPatchFactory factory;

    private static final MessageBundle BUNDLE
        = MessageBundles.getBundle(JsonPatchMessages.class);

    public RegistryBasedJsonPatchFactoryTest() {
        factory = new RegistryBasedJsonPatchFactory.Builder()
                .addOperation(new AddOperationFactory())
                .build();
    }
    @Test
    public void fromJsonParsesPatchCorrectly()
        throws IOException, JsonPatchException
    {
        JsonNode node = JsonLoader.fromString("[{\"op\":\"add\", \"path\": \"\", \"value\": \"foo\"}]");
        JsonPatch patch = factory.fromJson(node);
        assertTrue(true, "Should have parsed the json patch without error");
    }
    @Test
    public void fromJsonThrowsIfOpNotRecognized()
        throws IOException, JsonPatchException
    {
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
        throws JsonPatchException
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
