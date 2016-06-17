package com.grabm.tracker;



import java.net.Socket;
import java.net.ServerSocket;
import java.io.IOException;

public class Server {

    private final ServerSocket serverSocket;

    public Server(int serverPort) throws IOException {
        serverSocket = new ServerSocket(serverPort);
    }

    public void waitResponse() {
        while (true) {
            try {
                Socket sock = serverSocket.accept();
                ServerHandler handler = new ServerHandler(sock);
                handler.start();
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }
}
