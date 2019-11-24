package ua.nure.sidorovk.practice5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

class Util {
    private  int [][] matrix;

    int [][] getMatrix(){
        return this.matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public void readFile() throws IOException {
        String line;
        ArrayList<String> stringLines = new ArrayList<>();

        String FILE = "part4.txt";
        String ENCODING = "Cp1251";
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(new File(FILE)), ENCODING))) {
            while ((line = bufferedReader.readLine()) != null) {
                stringLines.add(line);
            }
            int rows = stringLines.size();
            int columns = 0;
            if (stringLines.size() != 0) {
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
