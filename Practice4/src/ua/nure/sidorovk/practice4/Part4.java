package ua.nure.sidorovk.practice4;

import java.io.FileNotFoundException;
import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.NoSuchElementException;

public class Part4 implements Iterable {
    private static final String ENCODING = "Cp1251";
    private static String textFile;
    private static String firstSuggestion;
    private static String patternSuggestion = "([A-ZА-Я]+[^.]{1,})(.)";

    public static void main(String[] args) throws IOException {

        try (BufferedReader bufferedReader = new BufferedReader
                (new InputStreamReader
                        (new FileInputStream(new File("part4.txt")),ENCODING))){

            String stringLine;
            StringBuilder text = new StringBuilder();

            while ((stringLine = bufferedReader.readLine()) != null) {
                text.append(stringLine).append("\n");
            }
            textFile = text.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if("".equals(textFile)) {
            Pattern pattern = Pattern.compile(patternSuggestion);
            Matcher matcher = pattern.matcher(textFile);
            if(matcher.find()){
                firstSuggestion = matcher.group(1);
            }
        }

        String[] strings = getSuggestions(textFile);
        for (String s:strings) {
            System.out.println(s);
        }
    }

    private static String [] getSuggestions(String text){
        String [] strings = new String[10];

        Pattern pattern = Pattern.compile(patternSuggestion);
        Matcher matcher = pattern.matcher(text);
        int i = 0;
        while(matcher.find()){
            strings[i++] = matcher.group().replace("\n", "");
        }
        String [] result = new String[i];
        System.arraycopy(strings, 0, result, 0, i);

        return result;
    }

    @Override
    public Iterator<String> iterator() {
        return new IteratorImpl();
    }

    private static class IteratorImpl implements Iterator<String> {
        private String suggestion = firstSuggestion;

        private Pattern pattern = Pattern.compile(patternSuggestion);
        private Matcher matcher = pattern.matcher(textFile);

        @Override
        public boolean hasNext() {
            return suggestion != null;
        }

        @Override
        public String next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            if(matcher.find()) {
                suggestion = matcher.group();
            }else {
                suggestion = null;
            }
            return this.suggestion;
        }

        @Override
        public void remove(){
            throw new UnsupportedOperationException();
        }
    }
}