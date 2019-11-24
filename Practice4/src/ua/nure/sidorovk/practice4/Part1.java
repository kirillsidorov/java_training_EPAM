package ua.nure.sidorovk.practice4;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
    private static final String FILE = "part1.txt";
    private static final String ENCODING = "Cp1251";

    private static String invertWord(String string) {
        StringBuilder str = new StringBuilder(string);
        int ln = str.length();

        for (int i = 0; i < ln; i++) {
            char c = str.charAt(i);
            if (Character.isLowerCase(c)) {
                str.replace(i, i + 1, Character.toUpperCase(c) + "");
            } else {
                str.replace(i, i + 1, Character.toLowerCase(c) + "");
            }
        }
        return str.toString();
    }

    private static String invertText(BufferedReader bufferedReader) throws IOException {
        String stringLine;
        StringBuilder text = new StringBuilder();

        String pattern = "(^|\\b)[A-ZА-ЯЁІa-zа-яёі]{4,}\\b";
        Pattern pattern1 = Pattern.compile(pattern);

        while ((stringLine = bufferedReader.readLine()) != null) {
            Matcher matcher1 = pattern1.matcher(stringLine);
            String result = stringLine;

            while(matcher1.find()){
                String word = stringLine.substring(matcher1.start(),matcher1.end());
                String newWord = invertWord(word);
                result = result.replaceFirst(word, newWord);
            }

            text.append(result).append("\n");
        }

        return text.toString();
    }

    public static void main(String[] args) throws IOException {

        try(BufferedReader bufferedReader = new BufferedReader(
                                            new InputStreamReader(
                                            new FileInputStream(new File(FILE)), ENCODING))) {
            String s = invertText(bufferedReader);
            System.out.println(s);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
