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
}
