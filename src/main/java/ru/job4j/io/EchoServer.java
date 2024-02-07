package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    ArrayList<String> content = new ArrayList<>();
                    for (String string = input.readLine(); string != null && !string.isEmpty(); string = input.readLine()) {
                        content.add(string);
                    }
                    String line = content.get(0);
                    int start = line.indexOf("msg=") + 4;
                    int end = line.indexOf(" HTTP/");
                    String message = line.substring(start, end);
                    switch (message) {
                        case "Exit" -> server.close();
                        case "Hello" -> output.write(("HTTP/1.1 200 OK\r\n\r\n" + "Hello").getBytes());
                        default -> output.write(("HTTP/1.1 200 OK\r\n\r\n" + "What").getBytes());
                    }
                    output.flush();
                }
            }
        }
    }
}