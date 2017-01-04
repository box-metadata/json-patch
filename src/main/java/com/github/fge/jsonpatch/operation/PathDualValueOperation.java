package com.github.fge.jsonpatch.operation;

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
 * Base class for JSON Patch operations taking one JSON Pointer and two values as arguments
 */
public abstract class PathDualValueOperation extends JsonPatchOperationBase
{
    protected static final MessageBundle BUNDLE
        = MessageBundles.getBundle(JsonPatchMessages.class);

    @JsonSerialize
    protected final JsonNode fromValue;

    @JsonSerialize
    protected final JsonNode toValue;

    /**
     * Protected constructor
     *
     * @param op operation name
     * @param path source path
     * @param fromValue original path
     * @param toValue new value
     */
    protected PathDualValueOperation(final String op, final JsonPointer path,
        final JsonNode fromValue, final JsonNode toValue)
    {
        super(op, path);
        this.fromValue = fromValue;
        this.toValue = toValue;
    }

    @Override
    public final void serialize(final JsonGenerator jgen,
        final SerializerProvider provider)
        throws IOException, JsonProcessingException
    {
        jgen.writeStartObject();
        jgen.writeStringField("op", getOp());
        jgen.writeStringField("path", getPath().toString());
        jgen.writeFieldName("from");
        jgen.writeTree(fromValue);
        jgen.writeFieldName("value");
        jgen.writeTree(toValue);
        jgen.writeEndObject();
    }

    @Override
    public final void serializeWithType(final JsonGenerator jgen,
        final SerializerProvider provider, final TypeSerializer typeSer)
        throws IOException, JsonProcessingException
    {
        serialize(jgen, provider);
    }

    @Override
    public final String toString()
    {
        return "op: " + getOp() + "; path: \"" + getPath() + "\"; from: " + fromValue + "; value: " + toValue;
    }
}
