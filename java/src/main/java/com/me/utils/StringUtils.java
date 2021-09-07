package com.me.utils;

import java.util.Arrays;

/**
 * @author zs
 *@date 2021/9/3.

 */
public final class StringUtils {
    /**
     * Converts the given object into a string representation by calling {@link Object#toString()}
     * and formatting (possibly nested) arrays and {@code null}.
     *
     * <p>See {@link Arrays#deepToString(Object[])} for more information about the used format.
     */
    public static String arrayAwareToString(Object o) {
        final String arrayString = Arrays.deepToString(new Object[]{o});
        return arrayString.substring(1, arrayString.length() - 1);
    }
}
