package ua.nure.sidorovk.practice4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part6 {
    private static final String ENCODING = "Cp1251";

    public static void main(String[] args) {
        String text =  readFile();
        InputStream in = System.in;
        Scanner scanner = new Scanner(in);
        String latinPat = "\\b([A-Za-z]{1,})\\b";
        String cyrilicPat = "\\b([А-Яа-яёїєі]{1,})\\b";

        while (scanner.hasNext()) {
                String s = scanner.nextLine();
                if ("stop".equals(s)||"Stop".equals(s)) {
                    break;
                }
            switch (s) {
                case "Latn":
                case "latn":
                    System.out.println(s + ": " + getWords(latinPat, text));
                    break;
                case "Cyrl":
                case "cyrl":
                    System.out.println(s + ": " + getWords(cyrilicPat, text));
                    break;
                default:
                    System.out.println(s + ": Incorrect input");
                    break;
            }
        }
        scanner.close();
    }

    private static String getWords(String pat, String text){
        Pattern pattern = Pattern.compile(pat);
        Matcher matcher = pattern.matcher(text);
        StringBuilder result = new StringBuilder();

        while (matcher.find()){
            result.append(matcher.group()).append(" ");
        }

        return result.toString();
    }

    private static String readFile(){
        StringBuilder text = new StringBuilder();

        try(BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(new FileInputStream(new File("part6.txt")),ENCODING))) {

            String stringLine;

            while ((stringLine = bufferedReader.readLine()) != null) {
                text.append(stringLine).append("\n");
            }

        } catch(IOException e){
            e.printStackTrace();
        }

        return text.toString();
    }

}
