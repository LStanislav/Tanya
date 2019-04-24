package Client;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static Client.Client.*;
import static Client.ClientFunctions.encrypt;

public class Expert extends BaseUser {
    private int marks[];
    private int competency;

    public Expert() {
        competency = 0;
    }

    public Expert(String login, String password) {
        super(login, password);
        competency = 0;
    }

    public int[] getMarks() {
        return marks;
    }

    public int getCompetency() {
        return competency;
    }

    public void setCompetency(int competency) {
        this.competency = competency;
    }

    public void setMarks(int[] marks) {
        this.marks = marks;
    }

    public void setMarks(String str) {
        String[] marks = str.split(" ");
        this.marks = new int[marks.length];
        for (int i = 0; i < marks.length; i++) {
            this.marks[i] = Integer.parseInt(marks[i]);
        }
    }

    public static void estimateGoals(String login) {

        StringBuilder msg = new StringBuilder("estimateGoals;");
        try {
            msg.append(encrypt(login) + ";");
            out.write(msg + System.lineSeparator());
            out.flush();
            msg = new StringBuilder();
            ArrayList<String> title = new ArrayList<>();
            ArrayList<String> description = new ArrayList<>();
            String buf = in.readLine();
            while (!buf.equals(";;")) {
                String part[] = buf.split(";");
                //System.out.println(part[0] + "  |  " + part[1]);
                title.add(part[0]);
                description.add(part[1]);
                buf = in.readLine();
            }
            int marks[][] = new int[title.size()][title.size()];
            int N = title.size();
            N = N*(N-1);
            for (int i = 0; i < title.size(); i++) {
                String t1 = title.get(i);
                String d1 = description.get(i);
                for (int j = i + 1; j < title.size(); j++) {
                    String t2 = title.get(j);
                    String d2 = description.get(j);
                    System.out.println(t1 + " - " + d1);
                    System.out.println(t2 + " - " + d2);
                    while (true) {
                        System.out.println("Введите вашу оценку(0.." + N + "): ");
                        String mark = reader.nextLine();
                        int m;
                        try {
                            m = Integer.parseInt(mark);
                            if ((m < 0) || (m > N)) {
                                System.out.println("Неверая оценка!");
                            } else {
                                marks[i][j] = m;
                                marks[j][i] = N - m;
                                break;
                            }
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            for (int i = 0; i < title.size(); i++) {
                for (int j = 0; j < title.size(); j++){
                    msg.append(marks[i][j] + " ");
                }
            }
            out.write(msg + System.lineSeparator());
            out.flush();
            System.out.println(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
