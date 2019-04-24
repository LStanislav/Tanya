package Client;

import java.io.*;
import static Client.Client.*;
import static Client.Menu.*;

public class ClientFunctions {


    public static String encrypt(String string) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            if (Character.isDigit(string.charAt(i))) {
                result.append((char) (string.charAt(i) - 10));
            } else result.append((char) (string.charAt(i) + 10));
        }
        return result.toString();
    }

    public static String decoding(String str) {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i) + 10)) {
                ans.append((char) (str.charAt(i) + 10));
            } else ans.append((char) (str.charAt(i) - 10));
        }
        return ans.toString();
    }

    public static void authorization(String type) {
        try {
            String ans;
            String login;
            while (true) {
                //connector.connect();
                StringBuilder msg = new StringBuilder("authorization;");
                msg.append(type);
                msg.append(";");
                System.out.print("Логин: ");
                login = reader.nextLine();
                msg.append(encrypt(login));
                msg.append(";");
                System.out.print("Пароль: ");
                msg.append(encrypt(reader.nextLine()));
                msg.append(";");
                out.write(msg + System.lineSeparator());
                out.flush();
                ans = in.readLine();
                if (ans.equals("No")) {
                    System.out.println("Неверный логин или пароль!");
                    System.out.println("Попробовать еще раз?(Д/Н)");
                    String ans2 = reader.nextLine();
                    if ((!ans2.equals("")) && ((ans2.charAt(0) == 'Н') || (ans2.charAt(0) == 'N'))) {
                        break;
                    }
                } else break;
            }
            if (ans.equals("Yes")) {
                switch (type) {
                    case "admin":
                        adminMenu();
                        break;
                    case "user":
                        userMenu();
                        break;
                    case "expert":
                        expertMenu(login);
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void exit() {
        //connector.connect();
        try {
            out.write("exit;" + System.lineSeparator());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
