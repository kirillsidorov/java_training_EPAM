package ua.nure.sidorovk.practice4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {

    private static final String FILE_PATH = "part3.txt";

    public static void main(String[] args) throws IOException {
        Text text = new Text();
        text.readFile(FILE_PATH);
        InputStream in = System.in;
        Scanner scanner = new Scanner(in);

        while (true) {
            String s = scanner.nextLine();

            if ("stop".equals(s)){
                break;
            }

            String strPattern = getPattern(s);

            if (" ".equals(strPattern)) {
                System.out.println("Incorrect input");
                continue;
            }
            System.out.println(text.getVariable(getPattern(s)));
        }
    }

    private static String getPattern(String s) {
        String result;
        switch (s) {
            case "double":
                result = "([0-9]+)+([.]+)+([0-9]+)|(([.]+)+([0-9]+))|(([0-9]+)([.]+))";
                break;
            case "String":
                result = "\\b([a-zA-ZА-ЯЁа-яё]{2,})\\b";
                break;
            case "int":
                result = "(^|\\s)([0-9]{1,})\\s";
                break;
            case "char":
                result = "\\b[a-zA-Zа-яёА-ЯЁf]{1}\\b";
                break;
            default:
                result = " ";
                break;
        }
        return result;
    }

static class Text {
    private StringBuilder stringBuilder;
    private static final String ENCODING = "Cp1251";

    void readFile(String path) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(
                                new File(path)), ENCODING))) {
            String stringLine;
            stringBuilder = new StringBuilder();

            while ((stringLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(stringLine).append("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    String getVariable(String patternV) {

        Pattern pattern = Pattern.compile(patternV);
        Matcher matcher = pattern.matcher(stringBuilder);
        StringBuilder sb = new StringBuilder();

        while (matcher.find()) {
            String findString = matcher.group().trim();
            sb.append(findString).append(" ");
        }

        if ("".equals(sb.toString())) {
            sb.append("No such values");
        }

        return sb.toString();
    }


}
}