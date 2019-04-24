package Client;

import java.io.IOException;
import java.util.Arrays;

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

            int marks[][] = new int[numberExperts][numberGoals];
            int competencies[] = new int[numberExperts];
            double results[] = new double[numberGoals];
            int sumCompetencies = 0;
            String buf = in.readLine();
            int i = 0;
            while (!buf.equals(";;")) {
                String part[] = buf.split(";", 20);
                competencies[i] = Integer.parseInt(part[2]);
                sumCompetencies += competencies[i];
                String ratings[] = part[3].split(" ", 20);
                for (int j = 0; j < min(ratings.length,numberGoals); j++) {
                    marks[i][j] = Integer.parseInt(ratings[j]);
                }
                i++;
                buf = in.readLine();
            }

            System.out.println(Arrays.toString(competencies));
            System.out.println("sumCompetencies= " +  sumCompetencies);
            for (i = 0; i < numberExperts; i++) {
                for (int j = 0; j < numberGoals; j++) {
                    System.out.printf("%3d", marks[i][j]);
                    double multiplier = (double)competencies[i]/(double)sumCompetencies;
                    //System.out.println(i + " " + j + " " + multiplier);
                    results[j]+=multiplier*(double)marks[i][j];
                }
                System.out.println();
            }
            for (int j=0; j<numberGoals; j++){
                System.out.printf("%3f ", results[j]);
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
