package ua.nure.sidorovk.practice6.part3;

public class Parking {

    private int [] p;
    private static final int OCCUPIED = 1;
    private static final int FREE = 0;

    Parking(int n){
        p = new int[n];
        for (int i: p) {
            p[i] = FREE;
        }
    }

    boolean arrive(int k){
        try {
            if (p[k] != OCCUPIED) {
                p[k] = OCCUPIED;
                return true;
            } else {
                int f = findFree(k);
                if (f > -1) {
                    p[f] = OCCUPIED;
                }
            }
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("IllegalArgumentException");
        }
        return false;
    }

    boolean depart(int k){
        try {
            if (p[k] == OCCUPIED) {
                p[k] = FREE;
                return true;
            }
        }catch (ArrayIndexOutOfBoundsException i){
            System.out.println("IllegalArgumentException");
        }
        return false;
    }

    private int findFree(int k){
        int position = k;
        int i = 0;
        while (i<p.length) {
            if((position<p.length) && (p[position] == FREE)){
                return position;
            }else if(position == p.length){
                position = -1;
                i -= 1;
            }
            position++;
            i++;
        }
        return -1;
    }

    void print(){
        for (int i :p) {
            System.out.print(i);
        }
        System.out.println();
    }
}
