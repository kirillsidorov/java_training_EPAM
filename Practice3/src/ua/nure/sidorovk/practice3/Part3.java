package ua.nure.sidorovk.practice3;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {
    private static String convert(String input) {
        String result = input;
        result = getStringLow(input, result);
        result = getStringUp(input, result);
        return  result;
    }

    private static String getStringLow(String input,  String result) {
        String patternLower = "(^|\\b)[a-zа-яё]{3,}\\b";
        Pattern pattern1 = Pattern.compile(patternLower);
        Matcher matcher1 = pattern1.matcher(input);

        while(matcher1.find()){
            String word = input.substring(matcher1.start(),matcher1.end());
            String newWord = word.substring(0, 1).toUpperCase(Locale.ENGLISH) + word.substring(1);
            result = result.replaceFirst(word, newWord);
        }
        return result;
    }

    private static String getStringUp(String input, String result) {
        String patternUpper = "\\p{javaUpperCase}\\p{javaLowerCase}{2,}";
        Pattern pattern = Pattern.compile(patternUpper);
        Matcher matcher = pattern.matcher(input);

        while(matcher.find()) {
            String str= input.substring(matcher.start(),matcher.end());
            result = result.replaceFirst(str, str.toLowerCase(Locale.ENGLISH));
        }
        return result;
    }

    public static void main(String[] args) {
        String s3 ="Пришвартоваться у причала стоит шиллинг. И назови своё имя.\n" +
                "Может быть, лучше три шиллинга? А имя — чёрт с ним.\n" +
                "Добро пожаловать в Порт-Ройал, мистер Смит.\n" +
                "It's a shilling to tie up your boat at the dock. And I shall need to know your name.\n" +
                "What do you say to three shillings, and we forget the name?\n" +
                "Welcome to Port Royal Mr.Smith";

       System.out.println(convert(s3));
    }
}
