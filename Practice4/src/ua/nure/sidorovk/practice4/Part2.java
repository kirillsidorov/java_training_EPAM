
package ua.nure.sidorovk.practice4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;

public class Part2 {

    private static String getRandomLineDigits(){
        final int fifty = 50;
        final int amountOfNumbers = 10;
        StringBuilder line = new StringBuilder();
        SecureRandom r = new SecureRandom();

        for (int i=0;i<amountOfNumbers;i++){
            line.append((r.nextInt(fifty))).append(" ");
        }

        return line.toString();
    }


    private static void writeFile(String path, String line){
        try(FileWriter fw = new FileWriter(new File(path))){
            fw.append(line);
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    private static String readFile(String path){
        String lineInPart2 = "";
        try(BufferedReader br = new BufferedReader(new FileReader(new File(path)))){
            lineInPart2 = br.readLine();
        }catch(IOException e){
            e.printStackTrace();
        }
        return lineInPart2;
    }

    private static String sort(String line){
        StringBuilder sb = new StringBuilder();
        String [] strings = line.trim().split(" ");
        int [] arr = new int[strings.length];
        for(int i =0; i< strings.length; i++){
            arr[i] = Integer.parseInt(strings[i]);
        }
        int [] arrSort = sortBuble(arr);
        for (int value : arrSort) {
            sb.append(value).append(" ");
        }
        return sb.toString();
    }

    private static int[] sortBuble(int[] arr){
        int n = arr.length;
        int temp;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (arr[j - 1] > arr[j]) {
                    temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }


    public static void main(String[] args){
        String file1 = "part2.txt";
        String file2 = "part2_sorted.txt";

        writeFile(file1, getRandomLineDigits());
        String line = readFile(file1);
        System.out.println("input  ==> " + line);
        writeFile(file2, sort(line));
        System.out.println("output ==> " + readFile(file2));
    }
}
