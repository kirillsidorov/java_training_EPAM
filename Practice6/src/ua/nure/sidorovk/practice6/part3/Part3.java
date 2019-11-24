package ua.nure.sidorovk.practice6.part3;

public class Part3 {
    public static void main(String[] args) {
        test1();
        test2();
        test3();

    }

    private static void test3() {
        Parking parking = new Parking(5);

        parking.arrive(5);
        parking.depart(5);
    }

    private static void test2() {
        Parking parking = new Parking(2);

        parking.print();
        parking.arrive(0);
        parking.print();

        parking.arrive(0);
        parking.print();

        System.out.println(parking.arrive(0));
        parking.print();
    }

    private static void test1() {
        int n = 3;
        Parking parking = new Parking(n);

        parking.print();
        parking.arrive(1);
        parking.arrive(2);
        parking.print();
        parking.arrive(1);
        parking.print();
        parking.arrive(1);
        parking.print();
    }


}
