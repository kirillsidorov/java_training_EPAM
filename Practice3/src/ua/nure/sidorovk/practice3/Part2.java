package ua.nure.sidorovk.practice3;

public class Part2 {
    public static void main(String[] args) {
        String file = "part2.txt";
        System.out.println(convert(Util.readFile(file)));
    }

    private static String convert(String input) {

        String[] words = input.replace("'", " ").replace(",","").replace("-"," ").split("\\s+");
        StringBuilder result = new StringBuilder();
        String smallestWords = getWords(words, getMinLength(words));
        result.append("Min: ").append(smallestWords).append("\n");
        String longestWords = getWords(words, getMaxLength(words));
        result.append("Max: ").append(longestWords);

        return result.toString();
    }

    private static String getWords(String[] arr, int length) {
        StringBuilder sb = new StringBuilder();
        for (String s : arr) {
            if (s.length() == length && (sb.indexOf(s) < 0)) {
                sb.append(s).append(", ");
            }
        }
        sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }

    private static int getMaxLength(String[] strings) {
        int length = 0;
            for (String w : strings) {
                if (w.length() > length) {
                    length = w.length();
                }
        }
        return length;
    }

    private static int getMinLength(String [] strings){
         int maxLength = 1_000_000;
        for (String s: strings) {
            if(maxLength > s.length()){
                maxLength = s.length();
            }
        }
        return maxLength;
    }
}
