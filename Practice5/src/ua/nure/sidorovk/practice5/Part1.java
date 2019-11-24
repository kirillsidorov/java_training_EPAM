package ua.nure.sidorovk.practice5;

public class Part1 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread();
        thread.start();
        System.out.println(thread.getName());
        Thread.sleep(300);
        f();
        Runnable runnable = () -> {
            System.out.println(thread.getName());
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread thread1 = new Thread(runnable);
        thread1.start();
    }

    private static void f() throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Thread.sleep(300);
    }


}
