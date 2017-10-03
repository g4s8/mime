/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Kirill (g4s8.public@gmail.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
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

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * MIME type from string.
 *
 * @author Kirill (g4s8.public@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@SuppressWarnings("PMD.StaticAccessToStaticFields")
public final class MimeTypeOf implements MimeType {

    /**
     * Type pattern.
     */
    private static final Pattern PTN_TYPE;

    /**
     * Parameters pattern.
     */
    private static final Pattern PTN_PARAM;

    static {
        final String token = "([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)";
        PTN_TYPE = Pattern.compile(
            String.format("%s/%s", token, token)
        );
        PTN_PARAM = Pattern.compile(
            String.format(
                ";\\s*(?:%s=(?:%s|\"([^\"]*)\"))?",
                token,
                token
            )
        );
    }

    /**
     * Source string.
     */
    private final String src;

    /**
     * Ctor.
     * @param source Source string
     */
    public MimeTypeOf(final String source) {
        this.src = source;
    }

    @Override
    public String type() throws IOException {
        return this.matcher().group(1).toLowerCase(Locale.US);
    }

    @Override
    public String subtype() throws IOException {
        return this.matcher().group(2).toLowerCase(Locale.US);
    }

    @Override
    @SuppressWarnings("PMD.AvoidInstantiatingObjectsInLoops")
    public Map<String, List<String>> params() throws IOException {
        final Matcher match = this.matcher();
        final Matcher param = MimeTypeOf.PTN_PARAM.matcher(this.src);
        final Map<String, List<String>> map = new HashMap<>(1);
        for (int id = match.end(); id < this.src.length(); id = param.end()) {
            param.region(id, this.src.length());
            if (!param.lookingAt()) {
                throw new IOException("Invalid mime-type params format");
            }
            final String name = param.group(1);
            if (!map.containsKey(name)) {
                map.put(name, new LinkedList<String>());
            }
            map.get(name).add(MimeTypeOf.paramValue(param));
        }
        return map;
    }

    @Override
    public String toString() {
        return this.src;
    }

    /**
     * Matcher for type/subtype.
     * @return Pattern matcher
     * @throws IOException If failed
     */
    private Matcher matcher() throws IOException {
        final Matcher matcher = MimeTypeOf.PTN_TYPE.matcher(this.src);
        if (!matcher.lookingAt()) {
            throw new IOException("Invalid mime-type format");
        }
        return matcher;
    }

    /**
     * Read parameter value from matcher.
     * @param matcher Pattern matcher
     * @return Value
     * @throws IOException If failed
     * @checkstyle MagicNumberCheck (30 lines)
     */
    private static String paramValue(final Matcher matcher) throws IOException {
        final String token = matcher.group(2);
        final String value;
        if (token == null) {
            value = matcher.group(3);
        } else {
            if (token.length() > 2
                && token.charAt(0) == '\''
                && token.charAt(token.length() - 1) == '\'') {
                value = token.substring(1, token.length() - 1);
            } else {
                value = token;
            }
        }
        if (value == null) {
            throw new IOException("Bad parameter value");
        }
        return value;
    }
}
