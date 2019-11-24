package ua.nure.sidorovk.practice6.part6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part6 {
    private static final String ARROW = " ==> ";

    public static void main(String[] args) {
        String path = args[1];
        String issue = args[3];
        String text =readFile(path);
        String[] words = getWords(text);


    if ("frequency".equals(issue)) {
        print(getResultFrequency(words));
    }

    if ("length".equals(issue)) {
        print(getResultLength(words));
    }

    if ("duplicates".equals(issue)) {
        print(getResultDuplicates(words));
        }
    }

    private static class CountWithPlace {
        private int length;
        private int count = 1;
        private final int place;

        CountWithPlace(int length, int place) {
            this.length = length;
            this.place = place;
        }

        CountWithPlace(int place) {
            this.place = place;
        }

        private void setCount(int count) {
            this.count = count;
        }

        private int getCount() {
            return count;
        }


        int getLength() {
            return length;
        }
    }

    private static String[] getResultFrequency(String [] input) {

        final HashMap<String, CountWithPlace> wordCounts = new HashMap<>();
        for (int place = 0; place < input.length; place++) {
            String w = input[place];
            CountWithPlace countWithPlace = wordCounts.get(w);
            if (countWithPlace == null) {
                wordCounts.put(w, new CountWithPlace(place));
            } else {
                countWithPlace.setCount(countWithPlace.getCount()+1);
            }
        }

        TreeMap<String, CountWithPlace> sortedWords = new TreeMap<>((String a, String b) -> {
            CountWithPlace countWithPlaceA = wordCounts.get(a);
            CountWithPlace countWithPlaceB = wordCounts.get(b);
            int count = countWithPlaceB.count - countWithPlaceA.count;
            if (count == 0) {
                return countWithPlaceA.place - countWithPlaceB.place;
            } else {
                return count;
            }
        });
        sortedWords.putAll(wordCounts);

        TreeSet<String> firstStrings = new TreeSet<>(Comparator.reverseOrder());
        int i = 0;
        for (String s : sortedWords.keySet()) {
            if (i == 3) {
                break;
            }
            i++;
            firstStrings.add(s);
        }

        int j =0;
        String [] result = new String[3];
        for (String s : firstStrings) {
            result[j] = s + ARROW + sortedWords.get(s).getCount();
            j++;
        }
        return result;
    }

    private static String[] getResultLength(String [] input) {

        final HashMap<String, CountWithPlace> wordCounts = new HashMap<>();
        for (int place = 0; place < input.length; place++) {
            String w = input[place];

            int finalPlace = place;
            wordCounts.computeIfAbsent(w, w1 -> new CountWithPlace(w1.length(), finalPlace));

        }

        TreeMap<String, CountWithPlace> sortedWords = new TreeMap<>((String a, String b) -> {
            CountWithPlace countWithPlaceA = wordCounts.get(a);
            CountWithPlace countWithPlaceB = wordCounts.get(b);
            int length = countWithPlaceB.length - countWithPlaceA.length;
            if (length == 0) {
                return countWithPlaceA.place - countWithPlaceB.place;
            } else {
                return length;
            }
        });
        sortedWords.putAll(wordCounts);

        String [] result = new String[3];
        int i = 0;
        for (Iterator<String> iterator = sortedWords.keySet().iterator(); iterator.hasNext(); ) {
            String s = iterator.next();
            if (i == 3) {
                break;
            }
            result[i] = s + ARROW + sortedWords.get(s).getLength();
            i++;
        }
        return result;
    }

    private static String[] getResultDuplicates(String [] input) {
        final Map<String, Integer> wordCounts = new LinkedHashMap<>();
        for (String w : input) {
            wordCounts.merge(w, 1, Integer::sum);
        }
        int i = 0;
        String [] result = new String[3];
        for (Map.Entry<String, Integer> wordCount : wordCounts.entrySet()) {
            if (i == 3) {
                break;
            }
            if (wordCount.getValue() > 1) {
                result[i] = new StringBuilder(wordCount.getKey().toUpperCase(Locale.ENGLISH)).reverse().toString();
                i++;
            }
        }
        return result;
    }

    private static void print(String[] strings){
        for (String s : strings) {
            System.out.println(s);
        }
    }

    private static String [] getWords(String text){
        String space = " ";
        StringBuilder sb = new StringBuilder();
        Pattern pattern = Pattern.compile("\\b[a-zA-Z]+\\b");
        Matcher matcher = pattern.matcher(text);
        while(matcher.find()){
            sb.append(matcher.group()).append(space);
        }
        return sb.toString().split(space);
    }

    private static String readFile(String path) {
        String res = null;
        String encoding = "Cp1251";
        try {
             byte[] bytes = Files.readAllBytes(Paths.get(path));
             res = new String(bytes, encoding);
        } catch (IOException ex) {
             ex.printStackTrace();
        }
        return res;
    }
}
