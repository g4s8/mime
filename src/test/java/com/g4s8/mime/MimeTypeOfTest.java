/**
 * Copyright (c) 2017 Kirill (g4s8.public@gmail.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to read
 * the Software only. Permissions is hereby NOT GRANTED to use, copy, modify,
 * merge, publish, distribute, sublicense, and/or sell copies of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.g4s8.mime;

import com.g4s8.mime.test.HmMimeHasSubType;
import com.g4s8.mime.test.HmMimeHasType;
import java.io.IOException;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Test case for {@link MimeTypeOf}.
 *
 * @author Kirill (g4s8.public@gmail.com)
 * @version $Id$
 * @since 0.1
 * @checkstyle JavadocMethodCheck (500 lines)
 */
public final class MimeTypeOfTest {

    @Test
    public void type() {
        MatcherAssert.assertThat(
            "Can't read type",
            new MimeTypeOf("application/pdf"),
            new HmMimeHasType("application")
        );
    }

    @Test
    public void subtype() {
        MatcherAssert.assertThat(
            "Can't read subtype",
            new MimeTypeOf("image/bmp"),
            new HmMimeHasSubType("bmp")
        );
    }

    @Test
    public void param() throws IOException {
        MatcherAssert.assertThat(
            "Can't read params",
            new MimeTypeOf("multipart/byteranges; boundary=3d6b6a416f9b5")
                .params()
                .get("boundary")
                .get(0),
            Matchers.equalTo("3d6b6a416f9b5")
        );
    }

    @Test
    public void asString() {
        final String src = "text/plain";
        MatcherAssert.assertThat(
            "Bad toString()",
            new MimeTypeOf(src),
            Matchers.hasToString(src)
        );
    }
}
