package com.github.fge.jsonpatch.properties;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.github.fge.jackson.jsonpointer.JsonPointer;
import com.github.fge.jsonpatch.JsonPatchMessages;
import com.github.fge.msgsimple.bundle.MessageBundle;
import com.github.fge.msgsimple.load.MessageBundles;

import java.io.IOException;

/**
 *
 */
public class PathValueProperties
        implements JsonPatchOperationProperties
{
    protected static final MessageBundle BUNDLE
        = MessageBundles.getBundle(JsonPatchMessages.class);

    protected final String op;

    protected final JsonPointer path;

    @JsonSerialize
    protected final JsonNode value;

    /**
     * @param op operation name
     * @param path affected path
     * @param value JSON value
     */
    @JsonCreator
    public PathValueProperties(@JsonProperty("op") final String op,
                               @JsonProperty("path") final JsonPointer path,
                               @JsonProperty("value") final JsonNode value)
    {
        this.op = op;
        this.path = path;
        this.value = value.deepCopy();
    }

    public String getOp()
    {
        return op;
    }

    public JsonPointer getPath()
    {
        return path;
    }

    public JsonNode getValue()
    {
        return value.deepCopy();
    }

    public final void serialize(final JsonGenerator jgen,
        final SerializerProvider provider)
        throws IOException, JsonProcessingException
    {
        jgen.writeStartObject();
        jgen.writeStringField("op", op);
        jgen.writeStringField("path", path.toString());
        jgen.writeFieldName("value");
        jgen.writeTree(value);
        jgen.writeEndObject();
    }

    public final void serializeWithType(final JsonGenerator jgen,
        final SerializerProvider provider, final TypeSerializer typeSer)
        throws IOException, JsonProcessingException
    {
        serialize(jgen, provider);
    }

    public final String toString()
    {
        return "op: " + op + "; path: \"" + path + "\"; value: " + value;
    }
}
