package com.infinity.labn2_sysprog;

public class MaskParser {
    public static String parseMaskToRegex(String mask) {
        StringBuilder regex = new StringBuilder();

        for (int i = 0; i < mask.length(); i++) {
            char currentChar = mask.charAt(i);

            if (currentChar == '%') regex.append("(\\w+)");
            else regex.append(currentChar);
        }

        return regex.toString();
    }
}
