package io.inkwellmc.restart.util;

public class Formatter {
    public static String format(String str, String... replacers) {
        for (int i = 0; i <= replacers.length-1; i++) {
            str = str.replace("{"+i+"}", replacers[i]);
        }
        return str;
    }
}