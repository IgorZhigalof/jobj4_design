package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        ArrayList<String> content = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader("data/log.txt"))) {
            String line;
            while ((line = in.readLine()) != null) {
                String[] words = line.split(" ");
                if (words[words.length - 2].equals("404")) {
                    content.add(line);
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return content;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter("data/log.txt");
        logFilter.filter().forEach(System.out::println);

    }
}