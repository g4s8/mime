/*
 * The MIT License (MIT) Copyright (c) 2017-2021 Kirill Ch. <g4s8.public@gmail.com>
 * https://github.com/g4s8/mime/LICENSE.txt
 */
package wtf.g4s8.mime;

import java.io.IOException;
import java.util.Map;

/**
 * Smart MIME type.
 * <p>
 * Aware of some parameters, like `charset` etc.
 * </p>
 *
 * @since 0.1
 */
public final class MimeTypeSmart implements MimeType {

    /**
     * Charset parameter.
     */
    private static final String PARAM_CHARSET = "charset";

    /**
     * Origin MIME type.
     */
    private final MimeType orig;

    /**
     * Ctor.
     * @param origin Origin MIME type
     */
    public MimeTypeSmart(final MimeType origin) {
        this.orig = origin;
    }

    @Override
    public String type() throws IOException {
        return this.orig.type();
    }

    @Override
    public String subtype() throws IOException {
        return this.orig.subtype();
    }

    @Override
    public Map<String, String> params() throws IOException {
        return this.orig.params();
    }

    /**
     * Read charset from parameters.
     * @return Charset string
     * @throws IOException If not found or invalid.
     */
    public String charset() throws IOException {
        final Map<String, String> params = this.params();
        if (!params.containsKey(MimeTypeSmart.PARAM_CHARSET)) {
            throw new IOException("Charset is not provided");
        }
        final String charset = params.get(MimeTypeSmart.PARAM_CHARSET);
        if (charset == null || "".equals(charset)) {
            throw new IOException("The charset parameter is not set");
        }
        return charset;
    }

    @Override
    public String toString() {
        return this.orig.toString();
    }
}
