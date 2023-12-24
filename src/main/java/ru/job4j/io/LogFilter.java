package ru.job4j.io;

import java.io.*;
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
                if ("404".equals(words[words.length - 2])) {
                    content.add(line);
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return content;
    }

    public void saveTo(String out) {
        var data = filter();
        try (PrintWriter outer = new PrintWriter(new BufferedWriter(new FileWriter(out)))) {
            data.forEach(outer::println);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new LogFilter("data/log.txt").saveTo("data/404.txt");
    }
}