package ua.nure.sidorovk.practice5;

import java.io.InputStream;

public class Part2 {

    public static void main(String[] args) throws Exception {

        InputStream stdIn = System.in;
        System.setIn(new MyInputStream(" "));

        Spam.main(null);
        Thread.sleep(2000);
        System.setIn(stdIn);
        stdIn.close();
    }
}
