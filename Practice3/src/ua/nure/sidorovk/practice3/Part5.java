package ua.nure.sidorovk.practice3;

public class Part5 {
    private static final int HUNDRED = 100;
    private static final int NINETY = 90;
    private static final int FIFTY = 50;
    private static final int TEN = 10;
    private static final int NINE = 9;
    private static final int FIVE = 5;
    private static final int ONE = 1;
    private static final String ARROW = " --> ";
    private static final int NINETY_FOUR = 94;

    public static String decimal2Roman(int x) {
        String[] romanCharacters = {"C", "XC", "L", "X", "IX", "V", "I" };
        int[] romanValues = {HUNDRED, NINETY, FIFTY, TEN, NINE, FIVE, ONE};
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < romanValues.length; i++) {
            int numberInPlace = x / romanValues[i];
            if (numberInPlace == 0) {
                continue;
            }
            result.append(((numberInPlace == 4) && (i > 0)) ? (romanCharacters[i] + romanCharacters[i - 1]) :
                    new String(new char[numberInPlace]).replace("\0", romanCharacters[i]));
            x = x % romanValues[i];
        }
        return result.toString();
}
    public static int roman2Decimal(String s) {
        int res = 0;
        int count;
        for (int i=0; i<s.length(); i = i +1 + count) {
            count = 0;
            int s1 = value(s.charAt(i));

            if (i + 1 < s.length()) {
                int s2 = value(s.charAt(i+1));

                if (s1 >= s2) {
                    res = res + s1;
                } else {
                    res = res + s2 - s1;
                    ++count;
                }
            } else {
                res = res + s1;
                ++count;
            }

        }
        return res;
    }

    private static int value(char r) {
        switch (r) {
            case 'V': return FIVE;
            case 'X': return TEN;
            case 'L': return FIFTY;
            case 'C': return HUNDRED;
            default: return ONE;
        }
    }

    public static void main(String[] args) {

        System.out.println("1" + ARROW + decimal2Roman(ONE) + ARROW + roman2Decimal("I"));
        System.out.println("94" + ARROW + decimal2Roman(NINETY_FOUR) + ARROW + roman2Decimal("XCIV"));
        System.out.println("100" + ARROW + decimal2Roman(HUNDRED) + ARROW + roman2Decimal("C"));
        System.out.println("98" + ARROW + decimal2Roman(98) + ARROW + roman2Decimal("XCVIII"));
    }
}
