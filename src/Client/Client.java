package Client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

import static Client.Menu.firstMenu;

public class Client {
    private static Socket clientSocket;
    public static BufferedReader in;
    public static BufferedWriter out;
    public static Scanner reader;
    private static Connector connector = () -> {
        try {
            clientSocket = new Socket("localhost", 4004);
            reader = new Scanner(System.in);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    };
    public static void main(String[] args) {
        connector.connect();
        firstMenu();
    }
}