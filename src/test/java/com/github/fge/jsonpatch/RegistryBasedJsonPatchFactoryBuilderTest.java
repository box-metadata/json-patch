package com.github.fge.jsonpatch;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 *
 */
public final class RegistryBasedJsonPatchFactoryBuilderTest {
    @Test
    public void buildCreatesJsonPatchFactoryWithNoOperations() {
        RegistryBasedJsonPatchFactory factory =
                new RegistryBasedJsonPatchFactory.RegistryBasedJsonPatchFactoryBuilder().build();
        assertNull(factory.getOperation("add"));
    }

    @Test
    public void buildCreatesJsonPatchFactoryWithAdditionalOperations() {
        RegistryBasedJsonPatchFactory factory =
                new RegistryBasedJsonPatchFactory.RegistryBasedJsonPatchFactoryBuilder()
                        .addOperation("add", AddOperation.class)
                        .build();
        assertNotNull(factory.getOperation("add"));
    }
}
