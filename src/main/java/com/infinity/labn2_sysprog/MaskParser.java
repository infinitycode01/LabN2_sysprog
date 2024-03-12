package com.infinity.labn2_sysprog;

public class MaskParser {
    public static String parseMaskToRegex(String mask) {
        StringBuilder regex = new StringBuilder();

        for (int i = 0; i < mask.length(); i++) {
            char currentChar = mask.charAt(i);

            if (currentChar == '%') {
                // тут додаєм ланцюг однакових літер або символів
            } else {
                // тут коли ми знайшли звичайну літери чи символ
            }

        }

        return regex.toString();
    }
}
