package ua.nure.sidorovk.practice5;

public class Part3 {
    private int counter;
    private int counter2;
    private int n;
    private int k;
    private int t;

    public Part3(int n, int k, int t) {
        this.n = n;
        this.k = k;
        this.t = t;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter1) {
        this.counter = counter1;
    }

    public int getCounter2() {
        return counter2;
    }

    public void setCounter2(int counter2) {
        this.counter2 = counter2;
    }

    public void test() throws InterruptedException {
        int time = this.t;
        int iteration = this.k;
        for (int i = 0; i < this.n; i++) {
            Thread myThread = new Thread() {
                public void run() {
                    for (int j = 0; j< iteration; j++) {
                        setCounter(getCounter() + 1);
                        System.out.println(counter + " " + counter2);
                        try {
                            Thread.sleep(time);
                        } catch (InterruptedException e) {
                            System.err.println(e);
                        }
                        setCounter2(getCounter2() + 1);
                    }
                }
            };
            myThread.start();
            myThread.join();
        }
    }

    public synchronized void print() {
        setCounter(getCounter() + 1);
        try {
            Thread.sleep(this.t);
        } catch (InterruptedException e) {
            System.err.println(e);
        }
        setCounter2(getCounter2() + 1);
        System.out.println(counter + " " + counter2);
    }

    public void testSync() {
        for (int i = 0; i < this.n; i++) {
            Thread myThread = new Thread() {
                public void run() {
                    for(int j = 0; j< k; j++) {
                        print();
                    }
                }
            };
            myThread.start();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //Part3 obj = new Part3(3, 3, 50);
        //obj.test();
        //obj.reset();
        //obj.testSync();
    }

    private void reset() {
        setCounter(0);
        setCounter2(0);
    }
}
