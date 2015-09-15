package com.github.fge.jsonpatch;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public final class JsonPatchFactoryUtilTest {
    @Test
    public void defaultFactoryReturnsFactory() {
        assertTrue(JsonPatchFactoryUtil.defaultFactory() instanceof JsonPatchFactory);
    }
    @Test
    public void defaultOperationsReturnsNonEmptyMap() {
        assertTrue(JsonPatchFactoryUtil.defaultOperations().size() > 0);
    }
    @Test
    public void extendedFactoryReturnsFactory() {
        assertTrue(JsonPatchFactoryUtil.extendedFactory() instanceof JsonPatchFactory);
    }
    @Test
    public void extendedOperationsReturnsNonEmptyMap() {
        assertTrue(JsonPatchFactoryUtil.extendedOperations().size() > 0);
    }
}
