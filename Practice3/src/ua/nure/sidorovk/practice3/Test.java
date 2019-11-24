package ua.nure.sidorovk.practice3;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Test {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println( generatePass());
        testPart4();
    }

    private static String generatePass() {
        SecureRandom random = new SecureRandom();
        int num = random.nextInt(10_000);
        return String.format("%04d", num);
    }
    private static void testPart4() throws NoSuchAlgorithmException {
        String exp = "912EC803B2CE49E4A541068D495AB570";
        String exp2 = "F0E4C2F76C58916EC258F246851BEA091D14D4247A2FC3E18694461B1816E13B";
        boolean check = true;

        if(!Part4.hash("asdf", "MD5").equals(exp)){
            check = false;
        }else if (!Part4.hash("asdf", "SHA-256").equals(exp2)){
            check = false;
        }
         if(check){
            System.out.println("OK");
        }
    }


}



