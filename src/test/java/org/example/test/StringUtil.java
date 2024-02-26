package org.example.test;

public class StringUtil {

    public static String extractLastTrimmed(String str) {
        if (str == null) {
            return null;
        }
        String[] strings = str.split(",");
        String last = strings[strings.length - 1];
        last = last.trim();
        return last.isEmpty() ? null : last;
    }
}
