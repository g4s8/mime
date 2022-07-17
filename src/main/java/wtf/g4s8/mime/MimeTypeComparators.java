/*
 * The MIT License (MIT) Copyright (c) 2017-2021 Kirill Ch. <g4s8.public@gmail.com>
 * https://github.com/g4s8/mime/LICENSE.txt
 */
package wtf.g4s8.mime;

import java.util.Comparator;

/**
 * MIME types comparators.
 *
 * @since 2.2
 */
public enum MimeTypeComparators implements Comparator<MimeType> {
    BY_QUALIFIER((left, right) -> Float.compare(
        right.param("q").map(Float::parseFloat).orElse(1F),
        left.param("q").map(Float::parseFloat).orElse(1F)
    )),
    BY_TYPE((left, right) -> compareTypes(left.type(), right.type())),
    BY_SUBTYPE((left, right) -> compareTypes(left.subtype(), right.subtype()));

    /**
     * Origin comparator.
     */
    private final Comparator<? super MimeType> origin;

    /**
     * Private enum constructor.
     *
     * @param origin Comparator
     */
    private MimeTypeComparators(final Comparator<? super MimeType> origin) {
        this.origin = origin;
    }

    @Override
    public int compare(MimeType left, MimeType right) {
        return this.origin.compare(left, right);
    }

    /**
     * Parse MIME types, pay attetion to wildcards.
     *
     * @param left Left type
     * @param right Right type
     * @return Compare result
     */
    private static int compareTypes(final String left, final String right) {
        final int res;
        if (left.equals("*")) {
            if (right.equals("*")) {
                res = 0;
            } else {
                res = 1;
            }
        } else {
            res = left.compareTo(right);
        }
        return res;
    }
}
