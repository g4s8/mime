/*
 * The MIT License (MIT) Copyright (c) 2017-2021 Kirill Ch. <g4s8.public@gmail.com>
 * https://github.com/g4s8/mime/LICENSE.txt
 */
package wtf.g4s8.mime;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

/**
 * Simple mime type without params.
 */
public final class MimeTypeSimple implements MimeType {

    /**
     * Type.
     */
    private final String tpe;

    /**
     * Subtype.
     */
    private final String sub;

    /**
     * New simple MIME-type.
     * @param type Type
     * @param subtype Subtype
     */
    public MimeTypeSimple(final String type, final String subtype) {
        this.tpe = type;
        this.sub = subtype;
    }

    @Override
    public String type() {
        return this.tpe;
    }

    @Override
    public String subtype() {
        return this.sub;
    }

    @Override
    public Optional<String> param(String name) {
        return Optional.empty();
    }

    @Override
    public Set<String> params() {
        return Collections.emptySet();
    }
}
