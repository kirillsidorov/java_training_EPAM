package ua.nure.sidorovk.practice3;

import java.security.SecureRandom;
import java.util.Random;

public class Part1 {
    private static final  String FILE = "part1.txt";

    public static void main(String[] args) {
        System.out.println(convert1(Util.readFile(FILE)));
        System.out.println(convert2(Util.readFile(FILE)));
        System.out.println(convert3(Util.readFile(FILE)));
        System.out.println(convert4(Util.readFile(FILE)));

    }

    public static String convert1(String input) {
        String [] strings = input.split("\n");
        StringBuilder st = new StringBuilder();
        for (int i =1; i< strings.length;i++){
            String [] arr = strings[i].split(";");
            st.append(arr[0]).append(": ").append(arr[2]).append("\n");
        }
        return  st.toString();
    }
    public static String convert2(String input) {
        String [] strings = input.split("\n");
        StringBuilder st = new StringBuilder();
        for (int i =1; i< strings.length;i++){
            String [] arr = strings[i].split(";");
            String name = arr[1].split(" ")[0];
            String lastName = arr[1].split(" ")[1];
            st.append(lastName).append(" ").append(name);
            st.append(" (email: ").append(arr[2]).append(")\n");
        }
        return  st.toString();
    }
    public static String convert3(String input) {
        StringBuilder st = new StringBuilder();
        User [] users = getUsers(input.split("\n"));
        String [] strings = getDomains(users);
        for (Object domain : strings) {
            st.append(domain).append(" ==> ");
            for (User user : users) {
                if (domain.equals(user.getDomain())) {
                    st.append(user.getLogin()).append(", ");
                }
            }
            st.delete(st.length() - 2, st.length()).append("\n");
        }
        return  st.toString();
    }
    public static String convert4(String input) {
        StringBuilder sb = new StringBuilder();
        String [] strings = input.split("\n");
        User [] users = getUsers(strings);
        sb.append(strings[0]).append(";Password\n");
        for (int i = 0; i< users.length; i++){
            sb.append(strings[i+1]).append(";").append(users[i].getPass()).append("\n");
        }
        return  sb.toString();
    }

    private static String[] getDomains(User [] users){
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < users.length; i++) {
            if(stringBuilder.indexOf(users[i].getDomain())<0){
                stringBuilder.append(users[i].getDomain()).append(" ");
            }
        }
        return stringBuilder.toString().trim().split(" ");
    }

    private static User[] getUsers(String [] strings){
        User [] users = new User[strings.length-1];
        for (int i =1; i< strings.length;i++){
            String [] arr = strings[i].split(";");
            users[i-1] = new User(arr[0], arr[2]);
        }
        return users;
    }

    private static String generatePass() {
        int number =  1000 + (int)(Math.random() * (10000 - 1_000 + 1));
        return String.format("%04d", number);
    }

    static class User{

        private String login;
        private String email;
        private String pass;

        public User(String login,String email) {
            this.login = login;
            this.email = email;
            this.pass = generatePass();
        }

        public String getPass() {
                return this.pass;
        }

        public String getLogin() {
            return login;
        }

        public String getDomain(){
            return email.split("@")[1];
        }
    }
}
