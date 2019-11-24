package ua.nure.sidorovk.practice3;

public class Part6 {
    private static String convert(String input) {
        String []  strings = duplicateWords(input);
        String string = input;
        for (String s: strings) {
            string = string.replaceAll("\\b" + s + "\\b", "_" + s);
        }
        return string;
    }

    private static String [] duplicateWords(String string) {
        String[] arr = string.split("\\s+");
        StringBuilder sb = new StringBuilder();
        int index = 1;
        for (String s : arr) {
            for (int j = index; j < arr.length; j++) {
                if (s.equals(arr[j]) && sb.indexOf(s) < 0) {
                    sb.append(s).append(" ");
                }
            }
            index++;
        }
        return sb.toString().split(" ");
    }

    public static void main(String[] args) {
        String s = "This is a test\n" +
                "And this is also a test\n" +
                "And these are also tests\n" +
                "test\n" +
                "Это тест\n" +
                "Это также тест\n" +
                "И это также тесты";

        System.out.println(convert(s));
    }
}
