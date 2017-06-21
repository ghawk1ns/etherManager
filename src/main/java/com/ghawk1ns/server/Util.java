package com.ghawk1ns.server;

import java.util.Arrays;

/**
 * Created by ghawkins on 6/17/17.
 */
public class Util {

    /**
     * @return true if s is null or empty
     */
    public static boolean nullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }

    public static boolean nullOrEmpty(String...s) {
        return Arrays.stream(s).anyMatch(Util::nullOrEmpty);
    }

    /**
     * @param ver version of the api being used
     * @param path api path to action
     * @param action specific action to be called
     * @return well formatted path ex) "v1/api/update"
     */
    public static String createPath(String ver, String path, String action) {
        return String.format("%s/%s/%s", ver, path, action);
    }

    /**
     *
     * @return an int from a string or 0 if it failed
     */
    public static int stoi(String raw) {
        if (nullOrEmpty(raw)) {
            return 0;
        }
        try {
            return Integer.valueOf(raw);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     *
     * @return a double from a string or 0 if it failed
     */
    public static double stod(String raw) {
        if (nullOrEmpty(raw)) {
            return 0;
        }
        try {
            return Double.valueOf(raw);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     *
     * @return a long from a string or 0 if it failed
     */
    public static long stol(String raw) {
        if (nullOrEmpty(raw)) {
            return 0;
        }
        try {
            return Long.valueOf(raw);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
