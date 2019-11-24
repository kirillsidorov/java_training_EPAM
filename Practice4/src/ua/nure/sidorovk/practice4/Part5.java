package ua.nure.sidorovk.practice4;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Part5 {
    private static final String NO_SUCH_VALUE = "No such values";

    public static void main(String[] args) {
        InputStream in = System.in;
        Scanner scanner = new Scanner(in);
        ArrayList<String>  list = new ArrayList<>();
        int one = 1;

        while (true) {
            String s = scanner.nextLine();
            if ("stop".equals(s)) {
                break;
            }
            list.add(s);
        }

        for (String s : list) {
            String[] var = s.split(" ");
            if(var.length > one) {
                String key = var[0];
                String loc = var[1];
                getVar(key, loc);
            }else{
                System.out.println(NO_SUCH_VALUE);
            }
        }
    }

   private static void getVar(String key, String loc){
       Locale.setDefault(Locale.forLanguageTag("en"));
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("resources", new Locale(loc));
            if (bundle.containsKey(key)) {
                System.out.println(bundle.getString(key));
            } else {
                System.out.println(NO_SUCH_VALUE);
            }
        }catch (MissingResourceException e){
            System.out.println(NO_SUCH_VALUE);
        }
    }



}
