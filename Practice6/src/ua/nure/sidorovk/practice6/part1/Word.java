package ua.nure.sidorovk.practice6.part1;

import java.util.Objects;

public class Word implements Comparable<Word>{
    private String content;
    private int frequency;

    Word(String string, int count){
        this.content = string;
        frequency = count;
    }

    int getFrequency() {
        return frequency;
    }

    String getContent() {
        return content;
    }

    @Override
    public int hashCode() {
        return Objects.hash(frequency);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Word word = (Word) o;
        return frequency == word.frequency;
    }

    @Override
    public int compareTo(Word word) {
        return word.getFrequency() - this.getFrequency();
    }
}
