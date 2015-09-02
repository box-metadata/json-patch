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

/**
 *
 */
public final class JsonPatchFactoryTest {
    private final Map<String, Class<? extends JsonPatchOperation>> operations;
    private final JsonPatchFactory factory;

    private static final MessageBundle BUNDLE
        = MessageBundles.getBundle(JsonPatchMessages.class);

    public JsonPatchFactoryTest() {
        operations = new HashMap<String, Class<? extends JsonPatchOperation>>();
        operations.put("add", AddOperation.class);
        factory = new JsonPatchFactory(operations);
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
    public void operationFromJsonParsesOperationCorrectly()
        throws IOException, JsonPatchException, JsonProcessingException {
        JsonNode node = JsonLoader.fromString("{\"op\":\"add\", \"path\": \"\", \"value\": \"foo\"}");
        JsonPatchOperation patchOp = factory.operationFromJson(node);
        assertEquals(AddOperation.class, patchOp.getClass());
    }
    @Test
    public void operationFromJsonThrowsIfOpNotRecognized()
        throws IOException, JsonPatchException, JsonProcessingException {
        JsonNode node = JsonLoader.fromString("{\"op\":\"remove\", \"path\": \"\"}");
        try {
            JsonPatchOperation patchOp = factory.operationFromJson(node);
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
