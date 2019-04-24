package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import static Server.ServerFunctions.*;

public class Server {
    private static Socket clientSocket;
    private static ServerSocket server;
    private static BufferedReader in;
    private static BufferedWriter out;

    public static void main(String[] args) throws IOException {
        try {
            server = new ServerSocket(4004);
            System.out.println("Server are started!");

            while (true) {
                clientSocket = server.accept();
                new ServerThread(clientSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
            //clientSocket.close();
        } finally {
            server.close();
            clientSocket.close();
        }
    }
}
