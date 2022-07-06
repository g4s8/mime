/*
 * The MIT License (MIT) Copyright (c) 2017-2021 Kirill Ch. <g4s8.public@gmail.com>
 * https://github.com/g4s8/mime/LICENSE.txt
 */
package wtf.g4s8.mime;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * MIME type of string.
 *
 * @since 2.0
 */
@SuppressWarnings("PMD.StaticAccessToStaticFields")
final class MimeTypeOfString implements MimeType {

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
                "\\s*(?:%s=(?:%s|\"([^\"]*)\")[;]?)?",
                token,
                token
            )
        );
    }

    /**
     * Source string.
     */
    private final CharSequence src;

    /**
     * Parameter's map cache.
     */
    private volatile Map<String, String> parmap;

    /**
     * New MIME type of string.
     *
     * @param source Source string
     */
    public MimeTypeOfString(final CharSequence src) {
        this.src = src;
    }

    @Override
    public String type() {
        return this.typeMatcher().group(1).toLowerCase(Locale.US);
    }

    @Override
    public String subtype() {
        return this.typeMatcher().group(2).toLowerCase(Locale.US);
    }

    @Override
    public Set<String> params() {
        return this.paramsMap().keySet();
    }

    @Override
    public Optional<String> param(final String name) {
        return Optional.ofNullable(this.paramsMap().get(name.toLowerCase(Locale.US)));
    }

    @Override
    public String toString() {
        return this.src.toString();
    }

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof MimeTypeOfString)) {
            return false;
        }
        final MimeTypeOfString other = (MimeTypeOfString) obj;
        return Objects.equals(this.src, other.src);
    }

    @Override
    public int hashCode() {
        return this.src.hashCode();
    }

    /**
     * Type and subtype matcher.
     *
     * @return Pattern matcher
     */
    private Matcher typeMatcher() {
        final Matcher matcher = MimeTypeOfString.PTN_TYPE.matcher(this.src);
        if (!matcher.lookingAt()) {
            throw new IllegalStateException("Invalid mime type format");
        }
        return matcher;
    }

    /**
     * Parameter name to value map.
     *
     * @return Map of parameters values by names
     */
    private Map<String, String> paramsMap() {
        if (this.parmap == null) {
            final Matcher matcher = this.typeMatcher();
            final String str = this.src.toString().trim();
            final Matcher param = MimeTypeOfString.PTN_PARAM.matcher(str);
            final Map<String, String> map = new HashMap<>();
            int id = matcher.end();
            if (id + 1 <= str.length() && str.charAt(id) == ';') {
                id++;
            } else {
                this.parmap = Collections.emptyMap();
                return this.parmap;
            }
            for (; id < str.length(); id = param.end()) {
                param.region(id, str.length());
                if (!param.lookingAt()) {
                    throw new IllegalStateException(
                        String.format("Invalid mime-type params: %s", param)
                    );
                }
                final String name = param.group(1).toLowerCase(Locale.US);
                if (map.containsKey(name)) {
                    throw new IllegalStateException(
                        String.format("Parameter %s may only exist once.", name)
                    );
                }
                map.put(name, MimeTypeOfString.paramValue(param));
            }
            this.parmap = map;
        }
        return Collections.unmodifiableMap(this.parmap);
    }

    /**
     * Read parameter value from matcher.
     *
     * @param matcher Pattern matcher
     * @return Value
     */
    private static String paramValue(final Matcher matcher) {
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
            throw new IllegalStateException("Bad parameter value");
        }
        return value;
    }
}
