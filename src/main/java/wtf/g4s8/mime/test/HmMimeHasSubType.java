/*
 * The MIT License (MIT) Copyright (c) 2017-2021 Kirill Ch. <g4s8.public@gmail.com>
 * https://github.com/g4s8/mime/LICENSE.txt
 */
package wtf.g4s8.mime.test;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import wtf.g4s8.mime.MimeType;

/**
 * MIME subtype matcher.
 *
 * @since 0.1
 */
public final class HmMimeHasSubType extends TypeSafeMatcher<MimeType> {

    /**
     * Expected subtype.
     */
    private final String expected;

    /**
     * New MIME subtype matcher.
     *
     * @param expected Expected type
     */
    public HmMimeHasSubType(final String expected) {
        super(MimeType.class);
        this.expected = expected;
    }

    @Override
    public boolean matchesSafely(final MimeType type) {
        return this.expected.equals(type.subtype());
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("MIME with subtype '")
            .appendValue(this.expected)
            .appendText("'");
    }
}
