package ua.nure.sidorovk.practice5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Part4 {

    private static int getMaxWithoutParallelization(int[][] matrix) throws InterruptedException {
        int max = matrix[0][0];
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                Thread.sleep(1);
                max = Math.max(max, anInt);
            }
        }
        return max;
    }

    private static int getMaxWithParallelization(int[][] matrix) throws InterruptedException, ExecutionException {
        ExecutorService exc = Executors.newFixedThreadPool(matrix.length);

        Future<Integer>[] futures = new Future[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            futures[i] = exc.submit(new Find(matrix[i]));
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            max = Math.max(max, futures[i].get());
        }

        exc.shutdown();
        return max;
    }

    public static void main(String[] args) throws Exception {
        int[][] matrix;
        Util1 util = new Util1();
        util.readFile();
        matrix = util.getMatrix();


        long start = System.currentTimeMillis();
        System.out.println(Part4.getMaxWithParallelization(matrix));
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        start = System.currentTimeMillis();
        System.out.println(Part4.getMaxWithoutParallelization(matrix));
        end = System.currentTimeMillis();
        System.out.println(end - start);

    }

    static class Find implements Callable<Integer> {
        private int[] column;

        public Find(int[] column) {
            this.column = column;
        }

        @Override
        public Integer call() throws InterruptedException {
            int max = column[0];
            final int[] arr = column;
            for (int i = 1; i < arr.length; i++) {
                Thread.sleep(1);
                max = Math.max(max, arr[i]);
            }
            return max;
        }

    }


    static class Util1 {
        private static final String FILE = "part4.txt";
        private static final String ENCODING = "Cp1251";
        private int[][] matrix;

        int[][] getMatrix() {
            return this.matrix;
        }

        public void setMatrix(int[][] matrix) {
            this.matrix = matrix;
        }

        public void readFile() throws IOException {
            String line;
            ArrayList<String> stringLines = new ArrayList<>();

            try (BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(new File(FILE)), ENCODING))) {
                while ((line = bufferedReader.readLine()) != null) {
                    stringLines.add(line);
                }
                int rows = stringLines.size();
                int columns = 0;
                if (rows != 0) {
                    columns = stringLines.get(0).split(" ").length;
                }
                setMatrix(new int[rows][columns]);
                for (int i = 0; i < rows; i++) {
                    String[] d = stringLines.get(i).split(" ");
                    for (int j = 0; j < columns; j++) {
                        this.matrix[i][j] = Integer.parseInt(d[j]);
                    }
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }


    }

}