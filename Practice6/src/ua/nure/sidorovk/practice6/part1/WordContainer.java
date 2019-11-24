package ua.nure.sidorovk.practice6.part1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;

public class WordContainer {

    public static void main(String[] args) {
        ArrayList <Word> words = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        StringBuilder stringBuilder = new StringBuilder();

        while(scanner.hasNext()){
            String string = scanner.next();
            if("stop".equals(string.trim())) {
                break;
            }else {
                stringBuilder.append(string).append(" ");
            }
        }

        String [] s = stringBuilder.toString().split(" ");
        HashSet<String> stringHashSet = new HashSet<>(Arrays.asList(s));

        for (String value : stringHashSet) {
            words.add(getWord(s, value));
        }

        Comparator<Word> comparator = new Comparator<Word>() {
            @Override
            public int compare(Word o1, Word o2) {
                int result = o2.getFrequency() - o1.getFrequency();
                if (result == 0) {
                    result = o1.getContent().compareTo(o2.getContent());
                }
                return result;
            }
        };

        words.sort(comparator);

        for (Word value : words) {
            System.out.println(value.getContent() + " : " + value.getFrequency());
        }
    }

    private static Word getWord(String [] strings, String word){
        int count = 0;
        for (String s : strings) {
            if(word.equals(s)){
                count++;
            }
        }
        return new Word(word, count);
        }
    }

