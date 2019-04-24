package Server;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

import static Server.ServerFunctions.*;
import static Server.ServerFunctions.showExperts;

public class ServerThread extends Thread {

    private Socket clientSocket; // сокет, через который сервер общается с клиентом,
    // кроме него - клиент и сервер никак не связаны
    private BufferedReader in; // поток чтения из сокета
    private BufferedWriter out; // поток записи в сокет

    public ServerThread(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        // если потоку ввода/вывода приведут к генерированию исключения, оно проброситься дальше
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        start(); // вызываем run()
    }
    @Override
    public void run() {
        try {
            while (true) {
                String message = in.readLine();
                String msg[];
                msg = message.split(";", 10);
                switch (msg[0]) {
                    case "authorization":
                        out.write(authorization(msg[1], msg[2], msg[3]) + System.lineSeparator());
                        out.flush();
                        break;
                    case "addUser":
                        out.write(addUser(msg[1], msg[2]) + System.lineSeparator());
                        out.flush();
                        break;
                    case "showUsers":
                        List<String> list = showUsers();
                        for (String i : list) {
                            out.write(i + System.lineSeparator());
                            out.flush();
                        }
                        break;
                    case "deleteUser":
                        out.write(deleteUser(msg[1]) + System.lineSeparator());
                        out.flush();
                        break;
                    case "addExpert":
                        out.write(addExpert(msg[1], msg[2]) + System.lineSeparator());
                        out.flush();
                        break;
                    case "showExperts":
                        list = showExperts();
                        for (String i : list) {
                            out.write(i + System.lineSeparator());
                            out.flush();
                        }
                        break;
                    case "findExpert":
                        out.write(findExpert(msg[1]) + System.lineSeparator());
                        out.flush();
                        break;
                    case "deleteExpert":
                        out.write(deleteExpert(msg[1]) + System.lineSeparator());
                        out.flush();
                        break;
                    case "editExpert":
//                        out.write(editExpert(msg[1],msg[2]) + System.lineSeparator());
//                        out.flush();
                        break;
                    case "addGoal":
                        out.write(addGoal(msg[1],msg[2]) +  System.lineSeparator());
                        out.flush();
                        break;
                    case "deleteGoal":
                        out.write(deleteGoal(msg[1]) +  System.lineSeparator());
                        out.flush();
                        break;
                    case "showGoals":
                        list = showGoals();
                        for (String i : list) {
                            out.write(i + System.lineSeparator());
                            out.flush();
                        }
                        break;
                    case "estimateGoals":
                        list = showGoals();
                        for (String i : list) {
                            out.write(i + System.lineSeparator());
                            out.flush();
                        }
                        out.write(editExpertMark(msg[1], in.readLine()) + System.lineSeparator());
                        out.flush();
                        break;
                    case "showResults":
                        out.write(calculateNumberExperts()+System.lineSeparator());
                        out.flush();
                        list = showGoals();
                        out.write((list.size()-1) + System.lineSeparator());
                        out.flush();
                        for (String i : list) {
                            out.write(i + System.lineSeparator());
                            out.flush();
                        }
                        list = showExperts();
                        for (String i : list) {
                            out.write(i + System.lineSeparator());
                            out.flush();
                        }
                        break;
                    case "exit":
                        clientSocket.close();
                        return;
                }
            }
        }catch (SocketException e){
            System.out.println("Ошибка на клиенте!");
        }
        catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
