package ua.nure.sidorovk.practice6;

import ua.nure.sidorovk.practice6.part2.Part2;
import ua.nure.sidorovk.practice6.part3.Part3;
import ua.nure.sidorovk.practice6.part4.Part4;
import ua.nure.sidorovk.practice6.part5.Part5;
import ua.nure.sidorovk.practice6.part6.Part6;

public class Demo {
    public static void main(String[] args) {

//        Part1.main(new String[]{});
//        Part2.main(new String[]{});
//        Part3.main(new String[]{});
//        Part4.main(new String[]{});
       // Part5.main(new String[]{});
//        System.out.println("~~~~~~~~~~~~Part6");
        Part6.main(new String[] {"--input", "part6.txt", "--task", "frequency"});
        Part6.main(new String[] {"--input", "part6.txt", "--task", "length"});
        Part6.main(new String[] {"--input", "part6.txt", "--task", "duplicates"});

    }
}
