package com.infinity.labn2_sysprog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordMatch {
    /*public static void main(String[] args) {
        String input = "aaahgfuuuul";
        String regex = "(\\w)\\1+"; // находит последовательности повторяющихся символов
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            String repeatedChar = matcher.group(1);
            int count = matcher.group().length();
            matcher.appendReplacement(result, repeatedChar + "(" + count + ")");
        }
        matcher.appendTail(result);

        System.out.println(result.toString());
    }*/

    public static void main(String[] args) {
        String inputText = "abc abbc abbbc abbbbc abbbbbc xyz"; // Пример входного текста
        String mask = "%abc%d"; // Пример маски

        // Экранируем специальные символы в маске и создаем регулярное выражение
        String regex = mask.replace("%", "\\\\w+").replace("d", "\\\\d");

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputText);

        System.out.println("Слова, соответствующие маске " + mask + ":");

        // Выводим найденные слова
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}
