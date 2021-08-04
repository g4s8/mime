/*
 * The MIT License (MIT) Copyright (c) 2017-2021 Kirill Ch. <g4s8.public@gmail.com>
 * https://github.com/g4s8/mime/LICENSE.txt
 */
package wtf.g4s8.mime;
// // text/html, application/xhtml+xml, application/xml;q=0.9, */*;q=0.8

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import wtf.g4s8.mime.test.HmMimeHasSubType;
import wtf.g4s8.mime.test.HmMimeHasType;

/**
 * Test case for {@link MimeType}.
 *
 * @since 2.1
 */
final class MimeTypeTest {
    @Test
    void parseMultipleTypes() {
        MatcherAssert.assertThat(
            MimeType.parse("text/html, application/xhtml+xml, application/xml;q=0.9, */*;q=0.8"),
            Matchers.contains(
                Matchers.allOf(new HmMimeHasType("application"), new HmMimeHasSubType("xml")),
                Matchers.allOf(new HmMimeHasType("*"), new HmMimeHasSubType("*")),
                Matchers.allOf(new HmMimeHasType("text"), new HmMimeHasSubType("html")),
                Matchers.allOf(new HmMimeHasType("application"), new HmMimeHasSubType("xhtml+xml"))
            )
        );
    }
}