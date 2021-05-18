/*
 * The MIT License (MIT) Copyright (c) 2017-2021 Kirill Ch. <g4s8.public@gmail.com>
 * https://github.com/g4s8/mime/LICENSE.txt
 */
package wtf.g4s8.mime;

import java.util.Optional;
import java.util.Set;

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
