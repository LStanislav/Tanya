package Client;

import java.io.IOException;

import static Client.Client.in;
import static Client.Client.out;

public class BaseUser {
    protected BaseUser(){
        login = "";
        password = "";
    }

    protected BaseUser(String login, String password) {
        this.login = login;
        this.password = password;
    }

    protected String login;
    protected String password;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static void showGoals() {
        StringBuilder msg = new StringBuilder("showGoals;");
        try {
            out.write(msg + System.lineSeparator());
            out.flush();
            String buf = in.readLine();
            while (!buf.equals(";;")) {
                String part[] = buf.split(";", 5);
                System.out.println(part[0] + "  |  " + part[1]);
                buf = in.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
