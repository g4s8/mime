/*
 * The MIT License (MIT) Copyright (c) 2017-2021 Kirill Ch. <g4s8.public@gmail.com>
 * https://github.com/g4s8/mime/LICENSE.txt
 */
package wtf.g4s8.mime.test;

import wtf.g4s8.mime.MimeType;
import java.util.Optional;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.SelfDescribing;
import org.hamcrest.TypeSafeMatcher;

/**
 * MIME param matcher.
 *
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
        return type.param(name).map(this.expected::matches).orElse(false);
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("MIME with param ").appendDescriptionOf(
            new ParamDescription(this.name, this.expected)
        );
    }

    @Override
    public void describeMismatchSafely(
        final MimeType item,
        final Description desc
    ) {
        final Optional<String> opt = item.param(name);
        if (opt.isPresent()) {
            desc.appendText("was param ")
                .appendDescriptionOf(new ParamDescription(this.name, opt.get()));
        } else {
            desc.appendText("no param `").appendValue(name).appendText("`");
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
