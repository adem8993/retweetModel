package util;


import java.util.StringTokenizer;

/**
 * Created by Adem on 20.9.2016.
 */
public final class StringUtil {

    public static int countNumberOfWords(String text) {
        String trim = text.trim();
        if (trim.isEmpty())
            return 0;
        return trim.split("\\s+").length;
    }
}
