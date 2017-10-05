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
package com.g4s8.mime.test;

import com.g4s8.mime.MimeType;
import java.io.IOException;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.SelfDescribing;
import org.hamcrest.TypeSafeMatcher;

/**
 * MIME param matcher.
 *
 * @author Viciouss (xviciouss@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class HmMimeHasParam extends TypeSafeMatcher<MimeType> {

    /**
     * The parameter name to check.
     */
    private final String name;

    /**
     * Expected matcher.
     */
    private final Matcher<String> expected;

    /**
     * Ctor.
     * @param name The name of the parameter to be matched.
     * @param expected The actual value that should be in this parameter.
     */
    public HmMimeHasParam(final String name, final String expected) {
        this(name, CoreMatchers.equalTo(expected));
    }

    /**
     * Ctor.
     * @param name The name of the parameter to be matched.
     * @param expected A matcher that can be used to check the param.
     */
    public HmMimeHasParam(final String name, final Matcher<String> expected) {
        super(MimeType.class);
        this.name = name;
        this.expected = expected;
    }

    @Override
    public boolean matchesSafely(final MimeType type) {
        try {
            final String actual = type.params().get(this.name);
            return this.expected.matches(actual);
        } catch (final IOException err) {
            throw new UnsupportedOperationException(
                "Matcher failed due to error",
                err
            );
        }
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("MIME with param ")
            .appendDescriptionOf(
                new ParamDescription(this.name, this.expected)
            );
    }

    @Override
    public void describeMismatchSafely(
        final MimeType item,
        final Description desc
    ) {
        try {
            desc.appendText("was param ")
                .appendDescriptionOf(
                    new ParamDescription(
                        this.name,
                        item.params().get(this.name)
                    )
                );
        } catch (final IOException err) {
            desc.appendText("was not set");
        }
    }

    /**
     * Description of a parameter, either the expected one or the actual one.
     */
    private static class ParamDescription implements SelfDescribing {
        /**
         * The name of the parameter.
         */
        private final String name;
        /**
         * The value of the parameter.
         */
        private final String value;

        /**
         * Ctor.
         * @param name The parameter name.
         * @param value The parameter value.
         */
        ParamDescription(final String name, final String value) {
            this.name = name;
            this.value = value;
        }

        /**
         * Ctor.
         * @param name The parameter name.
         * @param expected A matcher that was used for the expected value.
         */
        ParamDescription(final String name, final Matcher<String> expected) {
            this(name, expected.toString());
        }

        @Override
        public void describeTo(final Description description) {
            description.appendValue(this.name)
                .appendText(" = ")
                .appendValue(this.value);
        }
    }
}
