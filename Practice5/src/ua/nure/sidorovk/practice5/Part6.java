package ua.nure.sidorovk.practice5;

public class Part6 {
    private static final Object M = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread thread = getBlocked();

        print(thread.getState().toString());

        thread.interrupt();
        thread.join();

        Thread t = new Thread(Part6::waiting);
        t.start();

        while (true) {
            if (t.getState() != Thread.State.RUNNABLE) {
                break;
            }
        }

        synchronized (M) {
            print(t.getState().toString());
            M.notifyAll();
        }
        print(thread.getState().toString());
    }


    private static void waiting() {
        synchronized (M) {
            try {
                M.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private static Thread getBlocked() {
        final Object monitor = new Object();
        Thread thread = new Thread(() -> {
            synchronized (monitor) {
                //do nothing
            }
        });

        new Thread(() -> {
            synchronized (monitor) {
                thread.start();
                while (true) {
                    if (thread.isInterrupted()){
                        break;
                    }
                }
            }
        }).start();

        while (true) {
            if (thread.getState() == Thread.State.BLOCKED) {
                break;
            }
        }

        return thread;
    }

    private static void print(String msg){
        System.out.println(msg);
    }
}