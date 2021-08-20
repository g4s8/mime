/*
 * The MIT License (MIT) Copyright (c) 2017-2021 Kirill Ch. <g4s8.public@gmail.com>
 * https://github.com/g4s8/mime/LICENSE.txt
 */
package wtf.g4s8.mime;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import wtf.g4s8.mime.test.HmMimeHasSubType;
import wtf.g4s8.mime.test.HmMimeHasType;
import wtf.g4s8.oot.SequentialTests;
import wtf.g4s8.oot.SimpleTest;
import wtf.g4s8.oot.TestCase;

/**
 * Test case for {@link MimeType}.
 *
 * @since 2.1
 */
final class MimeTypeParseTest extends TestCase.Wrap {

    MimeTypeParseTest() {
        super(
            new SequentialTests(
                new SimpleTest<>(
                    "parse multiple types",
                    MimeType.parse("text/html, application/xhtml+xml, application/xml, */*"),
                    matchers("application/xhtml+xml", "application/xml", "text/html", "*/*")
                ),
                new SimpleTest<>(
                    "parse and sort by qualifier",
                    MimeType.parse("text/html, application/xhtml+xml, application/xml;q=0.9, */*;q=0.8"),
                    matchers("application/xhtml+xml", "text/html", "application/xml", "*/*")
                ),
                new SimpleTest<>(
                    "parse and sort repeated",
                    MimeType.parse(
                        "text/html;q=0.6, application/xml;q=0.9, image/bmp;q=0.3,image/bmp;q=0.5, text/html, multipart/mixed, text/json;q=0.4"
                    ),
                    matchers(
                        "multipart/mixed", "text/html", "application/xml", "image/bmp", "text/json"
                    )
                ),
                new SimpleTest<>(
                    "parse docker client header",
                    MimeType.parse(
                        "application/vnd.oci.image.manifest.v1+json,application/vnd.docker.distribution.manifest.v2+json,application/vnd.docker.distribution.manifest.v1+json,application/vnd.docker.distribution.manifest.list.v2+json"
                    ),
                    matchers(
                        "application/vnd.docker.distribution.manifest.list.v2+json",
                        "application/vnd.docker.distribution.manifest.v1+json",
                        "application/vnd.docker.distribution.manifest.v2+json",
                        "application/vnd.oci.image.manifest.v1+json"
                    )
                )
            )
        );
    }

    /**
     * Short method for matcher.
     * @oaram expect Expected mime types
     * @return Matcher
     */
    private static Matcher<Iterable<? extends MimeType>> matchers(final String... expect) {
        return Matchers.contains(
            Stream.of(expect)
                .map(item -> item.split("/"))
                .map(split -> Matchers.allOf(new HmMimeHasType(split[0]), new HmMimeHasSubType(split[1])))
                .collect(Collectors.toList())
        );
    }
}