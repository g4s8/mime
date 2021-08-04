/*
 * The MIT License (MIT) Copyright (c) 2017-2021 Kirill Ch. <g4s8.public@gmail.com>
 * https://github.com/g4s8/mime/LICENSE.txt
 */
package wtf.g4s8.mime;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Media type (or MIME type).
 *
 * @since 2.0
 */
public interface MimeType {

    /**
     * Type name.
     * @return Name string
     */
    String type();

    /**
     * Subtype name.
     * @return Subtype string
     */
    String subtype();

    /**
     * Optional parameters names.
     * @return unordered case-insentetive set of strings
     */
    Set<String> params();

    /**
     * Parameter value for name.
     * @param name Parameter name, case-insentetive
     * @return Optional parameter value if present for the name
     */
    Optional<String> param(String name);

    /**
     * Mime type of string source.
     * @return Mime type implementation for string provided
     */
    static MimeType of(final CharSequence src) {
        return new MimeTypeOfString(src);
    }

    /**
     * Parse mime-type string containing multiple media types with qualifiers.
     * <p>
     * Multiple media types could be used in {@code Accept} headers.
     * Mime types are ordered first by qualifier param {@code q} value, then by order
     * of appearance in source string.
     * </p>
     * @param src Source string
     * @return Ordered collection of mime types
     * @since 2.1
     */
    static Collection<MimeType> parse(final CharSequence src) {
        return Arrays.stream(src.toString().split(","))
            .map(String::trim)
            .map(MimeTypeOfString::new)
            .sorted(
                (left, right) -> Float.compare(
                    right.param("q").map(Float::parseFloat).orElse(0F),
                    left.param("q").map(Float::parseFloat).orElse(0F)
                )
            ).collect(Collectors.toList());
    }

    /**
     * Default decorator for mime type.
     * @since 2.0
     */
    abstract class Wrap implements MimeType {

        /**
         * Delegate.
         */
        private final MimeType origin;

        /**
         * Wraps origin.
         * @param origin Delegate
         */
        protected Wrap(final MimeType origin) {
            this.origin = origin;
        }

        @Override
        public final String type() {
            return this.origin.type();
        }

        @Override
        public String subtype() {
            return this.origin.subtype();
        }

        @Override
        public Set<String> params() {
            return this.origin.params();
        }

        @Override
        public Optional<String> param(final String name) {
            return this.origin.param(name);
        }

        @Override
        public String toString() {
            return this.origin.toString();
        }
    }
}
