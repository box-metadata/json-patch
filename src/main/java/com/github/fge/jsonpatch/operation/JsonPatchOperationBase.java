/*
 * Copyright (c) 2014, Francis Galiegue (fgaliegue@gmail.com)
 * Copyright (c) 2016, Jessica Beller (jbeller@box.com)
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

package com.github.fge.jsonpatch.operation;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.github.fge.jackson.jsonpointer.JsonPointer;
import com.github.fge.jsonpatch.operation.policy.PathMissingPolicy;

/**
 * JsonPatchOperationBase implements the basic concept of json patch.
 */
public abstract class JsonPatchOperationBase
    implements JsonPatchOperation
{
    private final PathMissingPolicy pathMissingPolicy;

    private final String op;

    @JsonSerialize(using = ToStringSerializer.class)
    private final JsonPointer path;

    JsonPatchOperationBase(final String op, final JsonPointer path)
    {
        this(op, path, null);
    }

    JsonPatchOperationBase(final String op, final JsonPointer path, final PathMissingPolicy pathMissingPolicy)
    {
        this.op = op;
        this.path = path;
        this.pathMissingPolicy = pathMissingPolicy;
    }

    @Override
    public String getOp() { return this.op; }

    @Override
    public JsonPointer getPath() { return this.path; }

    public PathMissingPolicy getPathMissingPolicy() { return this.pathMissingPolicy; }

    @Override
    public String toString()
    {
        return "op: " + this.op + "; path: \"" + this.path + '"';
    }
}
