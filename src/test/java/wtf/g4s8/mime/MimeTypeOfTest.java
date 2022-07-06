/*
 * The MIT License (MIT) Copyright (c) 2017-2021 Kirill Ch. <g4s8.public@gmail.com>
 * https://github.com/g4s8/mime/LICENSE.txt
 */
package wtf.g4s8.mime;


import java.util.Arrays;
import org.hamcrest.Matchers;
import wtf.g4s8.mime.test.HmMimeHasParam;
import wtf.g4s8.mime.test.HmMimeHasSubType;
import wtf.g4s8.mime.test.HmMimeHasType;
import wtf.g4s8.oot.SequentialTests;
import wtf.g4s8.oot.SimpleTest;
import wtf.g4s8.oot.TestCase;

/**
 * Test case for {@link MimeTypeOf}.
 *
 * @since 0.1
 */
public final class MimeTypeOfTest extends TestCase.Wrap {

    /**
     * New test case.
     */
    public MimeTypeOfTest() {
        super(
            new SequentialTests(
                Arrays.<TestCase>asList(
                    new SimpleTest<>(
                        "Parse type",
                        new MimeTypeOfString("application/pdf"),
                        new HmMimeHasType("application")
                    ),
                    new SimpleTest<>(
                        "Parse subtype",
                        new MimeTypeOfString("image/bmp"),
                        new HmMimeHasSubType("bmp")
                    ),
                    new SimpleTest<>(
                        "Parse boundary param",
                        new MimeTypeOfString("multipart/byteranges; boundary=3d6b6a416f9b5"),
                        new HmMimeHasParam("boundary", "3d6b6a416f9b5")
                    ),
                    new SimpleTest<>(
                        "Parse param ignore case",
                        new MimeTypeOfString("text/xml; EncOdinG=utf-8"),
                        new HmMimeHasParam(
                            "encoding",
                            Matchers.equalTo("utf-8")
                        )
                    ),
                    new SimpleTest<>(
                        "Parse multiple params",
                        new MimeTypeOfString("test/test;  FoO=1 bAr=Two "),
                        Matchers.allOf(
                            new HmMimeHasParam("foo", "1"),
                            new HmMimeHasParam("bar", "Two")
                        )
                    ),
                    new SimpleTest<>(
                        "Convert toString",
                        new MimeTypeOfString("text/plain"),
                        Matchers.hasToString(String.join("/", "text", "plain"))
                    ),
                    new SimpleTest<>(
                        "Read param ignore case",
                        new MimeTypeOfString("test/plan; FOO=bar"),
                        new HmMimeHasParam("Foo", "bar")
                    ),
                    new SimpleTest<>(
                        "Parse with whitespaces",
                        new MimeTypeOfString(
                            /*longline*/"multipart/form-data; charset=ISO-8859-1; boundary=bNeQZuZ5pNrHBg7REi9oH8LEOhtqW83GcR_"
                        ),
                        Matchers.allOf(
                            new HmMimeHasType("multipart"),
                            new HmMimeHasSubType("form-data"),
                            new HmMimeHasParam("charset", "ISO-8859-1"),
                            new HmMimeHasParam("boundary", "bNeQZuZ5pNrHBg7REi9oH8LEOhtqW83GcR_")
                        )
                    )
                )
            )
        );
    }
}
