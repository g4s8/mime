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
package com.g4s8.mime.test;

import com.g4s8.mime.MimeType;
import java.io.IOException;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * MIME type matcher.
 *
 * @author Kirill (g4s8.public@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class HmMimeHasType extends TypeSafeMatcher<MimeType> {

    /**
     * Expected type.
     */
    private final String expected;

    /**
     * Ctor.
     * @param expected Expected type
     */
    public HmMimeHasType(final String expected) {
        super(MimeType.class);
        this.expected = expected;
    }

    @Override
    public boolean matchesSafely(final MimeType type) {
        try {
            return this.expected.equals(type.type());
        } catch (final IOException err) {
            throw new UnsupportedOperationException(
                "Matcher failed due to error",
                err
            );
        }
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("MIME with type '")
            .appendValue(this.expected)
            .appendText("'");
    }
}
