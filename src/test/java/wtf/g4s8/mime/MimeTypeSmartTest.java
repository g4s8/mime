/*
 * The MIT License (MIT) Copyright (c) 2017-2021 Kirill Ch. <g4s8.public@gmail.com>
 * https://github.com/g4s8/mime/LICENSE.txt
 */
package wtf.g4s8.mime;

import org.hamcrest.Matchers;
import wtf.g4s8.oot.SequentialTests;
import wtf.g4s8.oot.SimpleTest;
import wtf.g4s8.oot.TestCase;

/**
 * Test case for {@link MimeTypeSmart}.
 *
 * @since 0.1
 */
public final class MimeTypeSmartTest extends TestCase.Wrap {

    /**
     * New test case.
     */
    public MimeTypeSmartTest() {
        super(
            new SequentialTests(
                new SimpleTest<String>(
                    "Parse charset",
                    () -> new MimeTypeSmart(
                        new MimeTypeOf("text/html; charset=utf-8")
                    ).charset(),
                    Matchers.equalTo("utf-8")
                )
            )
        );
    }
}
