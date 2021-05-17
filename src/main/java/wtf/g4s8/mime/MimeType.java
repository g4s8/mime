/*
 * The MIT License (MIT) Copyright (c) 2017-2021 Kirill Ch. <g4s8.public@gmail.com>
 * https://github.com/g4s8/mime/LICENSE.txt
 */
package wtf.g4s8.mime;

import java.io.IOException;
import java.util.Map;

/**
 * Media type (or MIME type).
 *
 * @since 0.1
 */
public interface MimeType {

    /**
     * Type name.
     * @return Type name string
     * @throws IOException If failed to read
     */
    String type() throws IOException;

    /**
     * Subtype name.
     * @return Subtype name string
     * @throws IOException If failed to read
     */
    String subtype() throws IOException;

    /**
     * Optional parameters.
     * @return Parameter map
     * @throws IOException If failed to read
     */
    Map<String, String> params() throws IOException;
}
