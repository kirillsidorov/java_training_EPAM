package ua.nure.sidorovk.practice5;

import java.io.InputStream;

public class MyInputStream extends InputStream {

    private int pos;
    private String string;

    MyInputStream(String input) {
        this.string = input;
    }

    @Override
    public int read(){
        byte[] arr = string.getBytes();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (arr.length > pos) {
            return arr[pos++] & 0xff;
        } else {
            return -1;
        }
    }
}
