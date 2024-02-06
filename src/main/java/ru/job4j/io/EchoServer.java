package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    ArrayList<String> content = new ArrayList<>();
                    for (String string = input.readLine(); string != null && !string.isEmpty(); string = input.readLine()) {
                        content.add(string);
                        System.out.println(string);
                    }
                    Pattern pattern = Pattern.compile("Bye HTTP");
                    if (pattern.matcher(content.get(0)).find()) {
                        server.close();
                    }
                    output.flush();
                }
            }
        }
    }
}