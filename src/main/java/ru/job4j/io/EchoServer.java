package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    output.write(("HTTP/1.1 200 OK\r\n\r\n").getBytes());
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
                        case "Hello" -> output.write("Hello".getBytes());
                        default -> output.write("What".getBytes());
                    }
                    output.flush();
                } catch (IOException e) {
                    LOG.error("Error in EchoServer", e);
                }
            }
        } catch (IOException e) {
            LOG.error("Error in EchoServer", e);
        }
    }
}