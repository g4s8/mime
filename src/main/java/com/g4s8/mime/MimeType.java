/**
 * Copyright (c) 2017 Kirill (g4s8.public@gmail.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to read
 * the Software only. Permissions is hereby NOT GRANTED to use, copy, modify,
 * merge, publish, distribute, sublicense, and/or sell copies of the Software.
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
import java.util.List;
import java.util.Map;

/**
 * Media type (or MIME type).
 *
 * @author Kirill (g4s8.public@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface MimeType {

    /**
     * Type name.
     * @return Type name string
     * @throws IOException If failed to read
     */
    String type() throws IOException;

    /**
     * Subtype name.
     * @return Subtype name string
     * @throws IOException If failed to read
     */
    String subtype() throws IOException;

    /**
     * Optional parameters.
     * @return Parameter map
     * @throws IOException If failed to read
     */
    Map<String, List<String>> params() throws IOException;
}
