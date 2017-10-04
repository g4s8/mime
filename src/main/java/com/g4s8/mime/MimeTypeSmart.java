/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Kirill (g4s8.public@gmail.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.g4s8.mime;

import java.io.IOException;
import java.util.Map;

/**
 * Smart MIME type.
 * <p>
 * Aware of some parameters, like `charset` etc.
 *
 * @author Kirill (g4s8.public@gmail.com)
 * @version $Id$
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
