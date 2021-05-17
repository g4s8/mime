/*
 * The MIT License (MIT) Copyright (c) 2017-2021 Kirill Ch. <g4s8.public@gmail.com>
 * https://github.com/g4s8/mime/LICENSE.txt
 */
package com.g4s8.mime;

import java.util.Arrays;

import com.g4s8.mime.test.HmMimeHasParam;
import com.g4s8.mime.test.HmMimeHasSubType;
import com.g4s8.mime.test.HmMimeHasType;
import org.hamcrest.Matchers;
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
                        new MimeTypeOf("application/pdf"),
                        new HmMimeHasType("application")
                    ),
                    new SimpleTest<>(
                        "Parse subtype",
                        new MimeTypeOf("image/bmp"),
                        new HmMimeHasSubType("bmp")
                    ),
                    new SimpleTest<>(
                        "Parse boundary param",
                        new MimeTypeOf("multipart/byteranges; boundary=3d6b6a416f9b5"),
                        new HmMimeHasParam("boundary", "3d6b6a416f9b5")
                    ),
                    new SimpleTest<>(
                        "Parse encoding param",
                        new MimeTypeOf("text/xml; encoding=utf-8"),
                        new HmMimeHasParam(
                            "encoding",
                            Matchers.equalToIgnoringCase("UtF-8")
                        )
                    ),
                    new SimpleTest<>(
                        "Convert toString",
                        new MimeTypeOf("text/plain"),
                        Matchers.hasToString(String.join("/", "text", "plain"))
                    )
                )
            )
        );
    }
}
