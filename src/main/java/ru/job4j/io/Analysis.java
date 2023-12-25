package ru.job4j.io;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
            BufferedWriter out = new BufferedWriter(new FileWriter(target))) {
                List<String> whenIsAvailable = Arrays.asList("200", "300");
                boolean isUnavailable = false;
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] split = line.split(" ");
                    if (isUnavailable == whenIsAvailable.contains(split[0])) {
                        isUnavailable = !isUnavailable;
                        String separator = isUnavailable ? ";" : ";" + System.lineSeparator();
                        out.write(split[1] + separator);
                    }
                }
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}