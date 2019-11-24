package ua.nure.sidorov.practice1;

public class Part7{

        public static int str2int(String number){
            int digit = 0;
            for (int i = number.length(); i > 0; i--) {
                digit += (int) (number.charAt(number.length() - i) - 64)
                        * Math.pow(26, number.length() - i);
            }
            return digit;
        }

        public static String int2str(int number) {
            String chars = "";
            String charsMirror = "";
            int modul;
            int divident = number;
            while (divident != 0) {
                modul = divident % 26;
                if (modul == 0) {
                    chars += "Z";
                    divident = (divident - 1) / 26;
                } else {
                    chars += (char) (modul + 64);
                    divident = (divident - modul) / 26;
                }
            }
            for (int i = 0; i < chars.length(); i++)
                charsMirror += chars.charAt(chars.length() - i - 1);
            return charsMirror;
        }

        public static String rightColumn(String number){
            String chars = "";
            int num;
            num = str2int(number);
            num++;
            chars = int2str(num);
            return chars;
        }

        public static void main(String[] args) {
            System.out.println("A ==> " + str2int("A") + " ==> " + int2str(1));
            System.out.println("B ==> " + str2int("B") + " ==> " + int2str(2));
            System.out.println("Z ==> " + str2int("Z") + " ==> " + int2str(26));
            System.out.println("AA ==> " + str2int("AA") + " ==> " + int2str(27));
            System.out.println("AZ ==> " + str2int("AZ") + " ==> " + int2str(52));
            System.out.println("BA ==> " + str2int("BA") + " ==> " + int2str(53));
            System.out.println("ZZ ==> " + str2int("ZZ") + " ==> " + int2str(702));
            System.out.println("AAA ==> " + str2int("AAA") + " ==> " + int2str(703));
        }
}