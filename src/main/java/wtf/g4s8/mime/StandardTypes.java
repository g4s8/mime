/*
 * The MIT License (MIT) Copyright (c) 2017-2021 Kirill Ch. <g4s8.public@gmail.com>
 * https://github.com/g4s8/mime/LICENSE.txt
 */
package wtf.g4s8.mime;

import java.util.Optional;
import java.util.Set;

/**
 * Standard MIME types.
 * @since 2.2
 */
public enum StandardTypes implements MimeType {

    WILDCARD("*", "*"),
    APPLICATION_OCTETSTREAM("application", "octet-stream"),
    TEXT_PLAIN("text", "plain"),
    TEXT_CSS("text", "css"),
    TEXT_HTML("text", "html"),
    TEXT_JAVASCRIPT("text", "javascript"),
    APPLICATION_JAVASCRIPT("application", "javascript");

    /**
     * Origin MIME.
     */
    private final MimeType origin;

    /**
     * Private enum constructor.
     * @param type MIME type
     * @param subtype MIME subtype
     */
    private StandardTypes(final String type, final String subtype) {
        this.origin = new MimeTypeSimple(type, subtype);
    }

    @Override
    public String type() {
        return this.origin.type();
    }

    @Override
    public String subtype() {
        return this.origin.subtype();
    }

    @Override
    public Optional<String> param(String name) {
        return this.origin.param(name);
    }

    @Override
    public Set<String> params() {
        return this.origin.params();
    }
}