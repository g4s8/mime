/*
 * The MIT License (MIT) Copyright (c) 2017-2021 Kirill Ch. <g4s8.public@gmail.com>
 * https://github.com/g4s8/mime/LICENSE.txt
 */

package wtf.g4s8.mime;

import wtf.g4s8.oot.ConsoleReport;
import wtf.g4s8.oot.FailingReport;
import wtf.g4s8.oot.SequentialTests;
import wtf.g4s8.oot.TestCase;

/**
 * Test main.
 * @since 1.0
 * @checkstyle JavadocMethodCheck (500 lines)
 */
public final class Test extends TestCase.Wrap {

    /**
     * New main test.
     */
    private Test() {
        super(
            new SequentialTests(new MimeTypeOfTest(), new MimeTypeSmartTest())
        );
    }

    public static void test() throws Exception {
        try (FailingReport report = new FailingReport(new ConsoleReport())) {
            new Test().run(report);
        }
    }
}
