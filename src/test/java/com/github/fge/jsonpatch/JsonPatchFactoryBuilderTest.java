package com.github.fge.jsonpatch;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

/**
 *
 */
public final class JsonPatchFactoryBuilderTest {
    @Test
    public void buildCreatesJsonPatchFactoryWithDefaultOperations() {
        JsonPatchFactoryBuilder builder = new JsonPatchFactoryBuilder();
        JsonPatchFactory factory = builder.build();
        assertEquals(6, factory.getOperations().size());
    }

    @Test
    public void buildCreatesJsonPatchFactoryWithAdditionalOperations() {
        JsonPatchFactoryBuilder builder = new JsonPatchFactoryBuilder();
        builder.addOperation("omit", OmitOperation.class);
        JsonPatchFactory factory = builder.build();
        assertEquals(7, factory.getOperations().size());
    }
}
