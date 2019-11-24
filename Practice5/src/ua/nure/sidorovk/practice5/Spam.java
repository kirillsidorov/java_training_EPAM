package ua.nure.sidorovk.practice5;

import java.util.Scanner;

public class Spam {
    private Thread[] threads;
    private int[] ints;
    private String[] strings;

    public Spam(String[] strings, int[] ints) {
        this.strings = strings;
        this.ints = ints;
        this.threads = new Thread[strings.length];
    }

    public void start() {
        for(int i = 0; i< strings.length; i++) {
            threads[i] = new Worker(strings[i], ints[i]);
            threads[i].start();
        }
    }

    public void stop() {
        for (Thread t : threads) {
            t.interrupt();
        }
    }

    private static class Worker extends Thread{
        private String string;
        private int anInt;

        Worker(String string, int anInt) {
            this.string = string;
            this.anInt = anInt;
        }

        @Override
        public void run() {
            boolean key = true;
            while (key) {
                System.out.println(string);
                try {
                    sleep(anInt);
                } catch (InterruptedException e) {
                    key = false;
                }
            }
        }

    }

    public static void main(String[] args) {
        int[] time = new int[]{1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000,};
        String[] messages = new String[]{"message - 1", "message - 2", "message - 3", "message - 4",
                "message - 5", "message - 6", "message - 7", "message - 8", "message - 9", "message - 0"};
        final Spam s = new Spam(messages,time);
        s.start();
        Scanner sc = new Scanner(System.in);

        while(sc.hasNextLine()){
            sc.nextLine();
            s.stop();
        }
        sc.close();
    }
}
