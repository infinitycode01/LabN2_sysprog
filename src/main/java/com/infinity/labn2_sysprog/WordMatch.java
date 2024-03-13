package com.infinity.labn2_sysprog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordMatch {
    public static String convertWord(String word) {
        String regex = "(\\w)\\1+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(word);

        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            String repeatedChar = matcher.group(1);
            int count = matcher.group().length();
            matcher.appendReplacement(result, count + "(" + repeatedChar + ")");
        }
        matcher.appendTail(result);

        return result.toString();
    }

    public static String[] getSplitedWord(String text) {
        Pattern splitPattern = Pattern.compile("[\\s\\n\\t\\r\\f]+");
        String[] splited = splitPattern.split(text);
        return splited;
    }
}
