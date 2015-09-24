/*
 * Copyright (c) 2014, Francis Galiegue (fgaliegue@gmail.com)
 *
 * This software is dual-licensed under:
 *
 * - the Lesser General Public License (LGPL) version 3.0 or, at your option, any
 *   later version;
 * - the Apache Software License (ASL) version 2.0.
 *
 * The text of this file and of both licenses is available at the root of this
 * project or, if you have the jar distribution, in directory META-INF/, under
 * the names LGPL-3.0.txt and ASL-2.0.txt respectively.
 *
 * Direct link to the sources:
 *
 * - LGPL 3.0: https://www.gnu.org/licenses/lgpl-3.0.txt
 * - ASL 2.0: http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package com.github.fge.jsonpatch.serialization;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.github.fge.jackson.JacksonUtils;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jackson.JsonNumEquals;
import com.github.fge.jsonpatch.*;
import com.google.common.base.Equivalence;
import com.google.common.collect.Lists;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import static org.testng.Assert.*;

@Test
public abstract class JsonPatchOperationSerializationTest
{
    private static final Equivalence<JsonNode> EQUIVALENCE
        = JsonNumEquals.getInstance();

    private final JsonNode node;
    private final ObjectMapper mapper;
    private final RegistryBasedJsonPatchFactory factory;

    /**
     * @param directoryName The directory name for the data provider JSON
     * @param operationFactory The JsonPatchOperationFactory for the particular operation we want to test serialization
     * @param mapperModules ObjectMapper Modules we want to register for this mapper
     *                      (e.g. if we want to use a different deserializer for the extended JSON patch operations)
     * @throws IOException
     */
    protected JsonPatchOperationSerializationTest(final String directoryName,
        final JsonPatchOperationFactory operationFactory,
        final JsonPatchDeserializer deserializer)
        throws IOException
    {
        final String resource = "/jsonpatch/" + directoryName + "/" + operationFactory.getOperationName() + ".json";
        node = JsonLoader.fromResource(resource);
        mapper = JacksonUtils.newMapper();
        Module module = new SimpleModule()
                .addDeserializer(JsonPatch.class, deserializer);
        mapper.registerModule(module);
        factory = (new RegistryBasedJsonPatchFactory.Builder())
                .addOperation(operationFactory)
                .build();
    }

    @DataProvider
    public final Iterator<Object[]> getInputs()
    {
        final List<Object[]> list = Lists.newArrayList();

        for (final JsonNode n: node.get("errors"))
            list.add(new Object[] { n.get("op")});

        for (final JsonNode n: node.get("ops"))
            list.add(new Object[] { n.get("op")});

        return list.iterator();
    }

    @Test(dataProvider = "getInputs")
    public final void patchOperationSerializationWorks(final JsonNode input)
        throws IOException, JsonPatchException
    {
        JsonNode patchWithOpNode = JacksonUtils.nodeFactory().arrayNode().add(input);
        final JsonPatch patchWithOp = mapper.treeToValue(patchWithOpNode, JsonPatch.class);

        /*
         * Now, write the operation as a String...
         */
        final String out = mapper.writeValueAsString(patchWithOp);

        /*
         * And read it as a JsonNode again, then test for equality.
         *
         * The reason we do that is that JSON does not guarantee the order of
         * object members; but JsonNode's .equals() method will correctly handle
         * this event, and we trust its .toString().
         */
        final JsonNode output = JacksonUtils.getReader().readTree(out);
        assertTrue(EQUIVALENCE.equivalent(patchWithOpNode, output));
    }
}

