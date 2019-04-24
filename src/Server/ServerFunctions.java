package Server;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ServerFunctions {

    public static String authorization(String type, String login, String password) {
        try {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/Tanya/enter/" + type + ".txt")));
            String log;
            String pass;
            System.out.println(login + " " + password);
            while ((log = buffer.readLine()) != null) {
                pass = buffer.readLine();
                if (type.equals("expert")){
                    buffer.readLine();
                }
                System.out.println(log + " " + pass);
                if ((pass.equals(password)) && (log.equals(login))) {
                    return "Yes";
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(new String(buf));
        return "No";
    }

    synchronized public static String addUser(String login, String password) {
        String ans = "";
        try {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/Tanya/enter/user.txt")));
            String log;
            String pass;
            while ((log = buffer.readLine()) != null) {
                pass = buffer.readLine();
                if (log.equals(login)) {
                    ans = "Есть пользователь с таким логином!";
                    break;
                }
            }
            if (ans.equals("")) {
                ans = "Пользователь успешно добавлен!";
                File file = new File("D://JavaProject/Tanya/enter/user.txt");
                System.out.println(file.exists() + " " + file.getAbsolutePath());

                FileWriter fileWriter = new FileWriter("D://JavaProject/Tanya/enter/user.txt", true);
                System.out.println(login + " " + password);
                fileWriter.write(login + "\n");
                fileWriter.write(password + "\n");
                fileWriter.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }

    public static List<String> showUsers() {
        File file = new File("D://JavaProject/Tanya/enter/user.txt");
        System.out.println(file.exists() + " " + file.getAbsolutePath());
        ArrayList<String> res = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/Tanya/enter/user.txt")));
            String log;
            String pass;
            while ((log = bufferedReader.readLine()) != null) {
                pass = bufferedReader.readLine();
                res.add(log + ";" + pass + ";");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        res.add(";;");
        return res;
    }

    synchronized public static String deleteUser(String login) {
        String ans = "";
        try {
            BufferedReader bufferedReader;
            FileWriter fileWriter;
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/Tanya/enter/user.txt")));
            String log;
            String pass;
            while ((log = bufferedReader.readLine()) != null) {
                pass = bufferedReader.readLine();
                if (log.equals(login)) {
                    ans = "Пользователь успешно удален!";
                }
            }

            if (!ans.equals("")) {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/Tanya/enter/user.txt")));
                fileWriter = new FileWriter("D://JavaProject/Tanya/enter/buffer.txt", true);

                while ((log = bufferedReader.readLine()) != null) {
                    pass = bufferedReader.readLine();
                    if (!log.equals(login)) {
                        fileWriter.write(log + "\n");
                        fileWriter.write(pass + "\n");
                    }
                }
                fileWriter.close();
                fileWriter = new FileWriter("D://JavaProject/Tanya/enter/user.txt");
                fileWriter.write("");
                fileWriter.close();

                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/Tanya/enter/buffer.txt")));
                fileWriter = new FileWriter("D://JavaProject/Tanya/enter/user.txt", true);
                while ((log = bufferedReader.readLine()) != null) {
                    pass = bufferedReader.readLine();
                    fileWriter.write(log + "\n");
                    fileWriter.write(pass + "\n");
                    fileWriter.flush();
                }
                fileWriter = new FileWriter("D://JavaProject/Tanya/enter/buffer.txt");
                fileWriter.write("");
                fileWriter.close();
            } else {
                ans = "Нет пользователя с таким логином!";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }

    synchronized public static String addExpert(String login, String password) {
        String ans = "";
        try {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/Tanya/enter/expert.txt")));
            String log;
            String pass;
            String ratings;
            while ((log = buffer.readLine()) != null) {
                pass = buffer.readLine();
                ratings = buffer.readLine();
                if (log.equals(login)) {
                    ans = "Есть эксперт с таким логином!";
                    break;
                }
            }
            if (ans.equals("")) {
                ans = "Эксперт успешно добавлен!";
                File file = new File("D://JavaProject/Tanya/enter/expert.txt");
                System.out.println(file.exists() + " " + file.getAbsolutePath());

                FileWriter fileWriter = new FileWriter("D://JavaProject/Tanya/enter/expert.txt", true);
                System.out.println(login + " " + password );
                fileWriter.write(login + "\n");
                fileWriter.write(password + "\n");
                fileWriter.write("0 0 0" + "\n");
                fileWriter.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }

    public static List<String> showExperts() {
        File file = new File("D://JavaProject/Tanya/enter/expert.txt");
        System.out.println(file.exists() + " " + file.getAbsolutePath());

        ArrayList<String> res = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/Tanya/enter/expert.txt")));
            String log;
            String pass;
            String ratings;

            while ((log = bufferedReader.readLine()) != null) {
                pass = bufferedReader.readLine();
                ratings = bufferedReader.readLine();
                res.add(log + ";" + pass + ";" + ratings + ";");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        res.add(";;");
        return res;
    }

    public static String findExpert(String login) {
        File file = new File("D://JavaProject/Tanya/enter/expert.txt");
        System.out.println(file.exists() + " " + file.getAbsolutePath());
        StringBuilder res = new StringBuilder("");
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/Tanya/enter/expert.txt")));
            String log;
            String pass;
            String ratings;

            while ((log = bufferedReader.readLine()) != null) {
                pass = bufferedReader.readLine();
                ratings = bufferedReader.readLine();
                if (log.equals(login)) {
                    res.append(log + ";" + pass + ";" + ratings + ";");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res.toString();
    }

    synchronized public static String deleteExpert(String login) {
        String ans = "";
        try {
            BufferedReader bufferedReader;
            FileWriter fileWriter;
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/Tanya/enter/expert.txt")));
            String log;
            String pass;
            String ratings;
            while ((log = bufferedReader.readLine()) != null) {
                pass = bufferedReader.readLine();
                ratings = bufferedReader.readLine();
                if (log.equals(login)) {
                    ans = "Эксперт успешно удален!";
                }
            }

            if (!ans.equals("")) {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/Tanya/enter/expert.txt")));
                fileWriter = new FileWriter("D://JavaProject/Tanya/enter/buffer.txt", true);

                while ((log = bufferedReader.readLine()) != null) {
                    pass = bufferedReader.readLine();
                    ratings = bufferedReader.readLine();
                    if (!log.equals(login)) {
                        fileWriter.write(log + "\n");
                        fileWriter.write(pass + "\n");
                        fileWriter.write(ratings + "\n");
                    }
                }
                fileWriter.close();
                fileWriter = new FileWriter("D://JavaProject/Tanya/enter/expert.txt");
                fileWriter.write("");
                fileWriter.close();

                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/Tanya/enter/buffer.txt")));
                fileWriter = new FileWriter("D://JavaProject/Tanya/enter/expert.txt", true);
                while ((log = bufferedReader.readLine()) != null) {
                    pass = bufferedReader.readLine();
                    ratings = bufferedReader.readLine();

                    fileWriter.write(log + "\n");
                    fileWriter.write(pass + "\n");
                    fileWriter.write(ratings + "\n");
                    fileWriter.flush();
                }
                fileWriter = new FileWriter("D://JavaProject/Tanya/enter/buffer.txt");
                fileWriter.write("");
                fileWriter.close();
            } else {
                ans = "Нет эксперта с таким логином!";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }

//    synchronized public static String editExpert(String login, String competencyLevel) {
//        String ans = "";
//        try {
//            BufferedReader bufferedReader;
//            FileWriter fileWriter;
//            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/Tanya/enter/expert.txt")));
//            String log;
//            String pass;
//            String competLevel;
//            String ratings;
//            while ((log = bufferedReader.readLine()) != null) {
//                pass = bufferedReader.readLine();
//                competLevel = bufferedReader.readLine();
//                ratings = bufferedReader.readLine();
//                if (log.equals(login)) {
//                    ans = "Информация об эксперте успешно отредактирована!";
//                }
//            }
//
//            if (!ans.equals("")) {
//                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/Tanya/enter/expert.txt")));
//                fileWriter = new FileWriter("D://JavaProject/Tanya/enter/buffer.txt", true);
//
//                while ((log = bufferedReader.readLine()) != null) {
//                    pass = bufferedReader.readLine();
//                    competLevel = bufferedReader.readLine();
//                    ratings = bufferedReader.readLine();
//
//                    fileWriter.write(log + "\n");
//                    fileWriter.write(pass + "\n");
//                    if (!log.equals(login)) {
//                        fileWriter.write(competLevel + "\n");
//                    } else {
//                        fileWriter.write(competencyLevel + "\n");
//                    }
//                    fileWriter.write(ratings + "\n");
//
//                }
//                fileWriter.close();
//
//                fileWriter = new FileWriter("D://JavaProject/Tanya/enter/expert.txt");
//                fileWriter.write("");
//                fileWriter.close();
//
//                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/Tanya/enter/buffer.txt")));
//                fileWriter = new FileWriter("D://JavaProject/Tanya/enter/expert.txt", true);
//                while ((log = bufferedReader.readLine()) != null) {
//                    pass = bufferedReader.readLine();
//                    competLevel = bufferedReader.readLine();
//                    ratings = bufferedReader.readLine();
//
//                    fileWriter.write(log + "\n");
//                    fileWriter.write(pass + "\n");
//                    fileWriter.write(competLevel + "\n");
//                    fileWriter.write(ratings + "\n");
//                    fileWriter.flush();
//                }
//                fileWriter = new FileWriter("D://JavaProject/Tanya/enter/buffer.txt");
//                fileWriter.write("");
//                fileWriter.close();
//            } else {
//                ans = "Нет эксперта с таким логином!";
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return ans;
//    }

    synchronized public static String addGoal(String title, String description){
        String ans = "";
        try {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/Tanya/enter/goal.txt")));
            String tit;
            String descr;
            while ((tit = buffer.readLine()) != null) {
                descr = buffer.readLine();
                System.out.println(tit + " " + descr);
                if (tit.equals(title)) {
                    ans = "Есть цель с таким заголовком!";
                    break;
                }
            }
            buffer.close();

            if (ans.equals("")) {
                ans = "Цель успешно добавлена!";
                File file = new File("D://JavaProject/Tanya/enter/goal.txt");
                System.out.println(file.exists() + " " + file.getAbsolutePath());

                FileWriter fileWriter = new FileWriter("D://JavaProject/Tanya/enter/goal.txt", true);
                System.out.println(title + " " + description);
                fileWriter.write(title + "\n");
                fileWriter.write(description + "\n");
                fileWriter.flush();
                fileWriter.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }

    synchronized public static String deleteGoal(String title) {
        String ans = "";
        try {
            BufferedReader bufferedReader;
            FileWriter fileWriter;
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/Tanya/enter/goal.txt")));
            String tit;
            String descr;
            while ((tit = bufferedReader.readLine()) != null) {
                descr = bufferedReader.readLine();
                if (tit.equals(title)) {
                    ans = "Цель успешно удалена!";
                }
            }

            if (!ans.equals("")) {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/Tanya/enter/goal.txt")));
                fileWriter = new FileWriter("D://JavaProject/Tanya/enter/buffer.txt", true);

                while ((tit = bufferedReader.readLine()) != null) {
                    descr = bufferedReader.readLine();
                    if (!tit.equals(title)) {
                        fileWriter.write(tit + "\n");
                        fileWriter.write(descr + "\n");
                    }
                }
                fileWriter.close();
                fileWriter = new FileWriter("D://JavaProject/Tanya/enter/goal.txt");
                fileWriter.write("");
                fileWriter.close();

                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/Tanya/enter/buffer.txt")));
                fileWriter = new FileWriter("D://JavaProject/Tanya/enter/goal.txt", true);
                while ((tit = bufferedReader.readLine()) != null) {
                    descr = bufferedReader.readLine();
                    fileWriter.write(tit + "\n");
                    fileWriter.write(descr + "\n");
                    fileWriter.flush();
                }
                fileWriter = new FileWriter("D://JavaProject/Tanya/enter/buffer.txt");
                fileWriter.write("");
                fileWriter.close();
            } else {
                ans = "Нет цели с таким заголовком!";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }

    public static List<String> showGoals(){
        File file = new File("D://JavaProject/Tanya/enter/goal.txt");
        System.out.println(file.exists() + " " + file.getAbsolutePath());
        ArrayList<String> res = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/Tanya/enter/goal.txt")));
            String tit;
            String descr;
            while ((tit = bufferedReader.readLine()) != null) {
                descr = bufferedReader.readLine();
                res.add(tit + ";" + descr + ";");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        res.add(";;");
        return res;
    }

    synchronized public static String editExpertMark(String login, String ratings){
        String ans = "Оценки успешно занесены!";
        try {
            BufferedReader bufferedReader;
            ratings = ratings.trim();
            FileWriter fileWriter;
            String log;
            String pass;
            String rat;
            System.out.println(login + " " + ratings);
            if (!ans.equals("")) {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/Tanya/enter/expert.txt")));
                fileWriter = new FileWriter("D://JavaProject/Tanya/enter/buffer.txt", true);

                while ((log = bufferedReader.readLine()) != null) {
                    pass = bufferedReader.readLine();
                    rat = bufferedReader.readLine();
                    fileWriter.write(log + "\n");
                    fileWriter.write(pass + "\n");
                    if (!log.equals(login)) {
                        fileWriter.write(rat + "\n");
                    } else {
                        fileWriter.write(ratings + "\n");
                    }
                }
                fileWriter.close();

                fileWriter = new FileWriter("D://JavaProject/Tanya/enter/expert.txt");
                fileWriter.write("");
                fileWriter.close();

                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/Tanya/enter/buffer.txt")));
                fileWriter = new FileWriter("D://JavaProject/Tanya/enter/expert.txt", true);
                while ((log = bufferedReader.readLine()) != null) {
                    pass = bufferedReader.readLine();
                    rat = bufferedReader.readLine();

                    fileWriter.write(log + "\n");
                    fileWriter.write(pass + "\n");
                    fileWriter.write(rat + "\n");
                    fileWriter.flush();
                }
                fileWriter = new FileWriter("D://JavaProject/Tanya/enter/buffer.txt");
                fileWriter.write("");
                fileWriter.close();
            } else {
                ans = "Нет эксперта с таким логином!";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }

    public static String calculateNumberExperts(){
        Integer result = 0;
        String buf;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/Tanya/enter/expert.txt")));
            while ((buf=bufferedReader.readLine())!=null){
                result++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result);
        result/=3;
        System.out.println(result);
        return result.toString();
    }

    public static String calculateNumberGoals(){
        Integer result = 0;
        String buf;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/Tanya/enter/goal.txt")));
            while ((buf=bufferedReader.readLine())!=null){
                result++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result);
        result/=2;
        System.out.println(result);
        return result.toString();
    }
}
