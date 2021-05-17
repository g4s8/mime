/*
 * The MIT License (MIT) Copyright (c) 2017-2021 Kirill Ch. <g4s8.public@gmail.com>
 * https://github.com/g4s8/mime/LICENSE.txt
 */
package wtf.g4s8.mime.test;

import wtf.g4s8.mime.MimeType;
import java.io.IOException;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * MIME type matcher.
 *
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
