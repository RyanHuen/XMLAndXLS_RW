package ryan.utils;

import com.sun.istack.internal.Nullable;

/**
 * Created by ryanhuenwork on 16-10-21.
 */
public class StringUtils {
    /**
     * Returns true if the string is null or 0-length.
     *
     * @param str the string to be examined
     * @return true if str is null or zero length
     */
    public static boolean isEmpty(@Nullable CharSequence str) {
        if (str == null || str.length() == 0)
            return true;
        else
            return false;
    }
}
