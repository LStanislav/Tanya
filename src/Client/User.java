package Client;

import java.io.IOException;
import java.util.Arrays;
import java.util.TreeMap;

import static Client.Client.in;
import static Client.Client.out;
import static java.lang.Math.min;

public class User extends BaseUser {
    public User(String login, String password) {
        super(login, password);
    }
    public User() {
    }

    public static void showResults() {

        StringBuilder msg = new StringBuilder("showResults;");
        try {
            out.write(msg + System.lineSeparator());
            out.flush();
            int numberExperts = Integer.parseInt(in.readLine());
            int numberGoals = Integer.parseInt(in.readLine());
            System.out.println(numberExperts);
            System.out.println(numberGoals);
            String goals[] = new String[numberGoals + 1];
            for (int i = 0; i < numberGoals + 1; i++) {
                goals[i] = in.readLine();
                System.out.println(goals[i]);
            }
            int marks[][] = new int[numberGoals][numberGoals];
            int expert_goals[][] = new int[numberExperts][numberGoals];
            //double results[][] = new double[numberGoals];
            String buf = in.readLine();
            int k = 0;
            int sum = 0;
            while (!buf.equals(";;")) {
                String part[] = buf.split(";");
                String ratings[] = part[2].split(" ");
                System.out.println("Оценки эксперта " + (k + 1) + ": ");
                for (int i = 0; i < numberGoals; i++) {
                    for (int j = 0; j < numberGoals; j++) {
                        if (i * numberGoals + j < ratings.length) {
                            marks[i][j] = Integer.parseInt(ratings[i * numberGoals + j]);
                        } else {
                            marks[i][j] = 0;
                        }
                        System.out.printf("%3d ", marks[i][j]);
                        expert_goals[k][i] += marks[i][j];
                        sum += marks[i][j];
                    }
                    System.out.println();
                }
                System.out.println();
                k++;
                buf = in.readLine();
            }
            System.out.println();
            int sumGoals[] = new int[numberGoals];
            System.out.println("Зкперты - Цели: ");
            for (int i = 0; i < numberExperts; i++) {
                for (int j = 0; j < numberGoals; j++) {
                    System.out.printf("%4d", expert_goals[i][j]);
                    sumGoals[j] += expert_goals[i][j];
                }
                System.out.println();
            }
            System.out.println(sum);
            for (int i = 0; i < numberGoals; i++) {
                System.out.printf("%4d", sumGoals[i]);
            }
            System.out.println();
            System.out.println("Итоговые веса: ");
            TreeMap<Float, String> ans = new TreeMap<>();
            for (int i = 0; i < numberGoals; i++) {
                Float x = (float) sumGoals[i] / (float) sum;
                ans.put(x, goals[i]);
                System.out.printf("%.3f ", x);
            }
            System.out.println();
            while (!ans.isEmpty()) {
                String str = ans.get(ans.lastKey());
                System.out.printf("%.3f %s\n", ans.lastKey(), str);
                ans.remove(ans.lastKey());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
