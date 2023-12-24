package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Analysis {
    public void unavailable(String source, String target) {
        writeToFile(
                analyzeData(
                        readerFromFile(source)
                ),
                target
        );

    }

    private List<String> analyzeData(List<String> content) {
        List<String> result = new ArrayList<>();
        List<String> whenIsAvailable = Arrays.asList("200", "300");
        boolean isUnavailable = false;
        String[] split;
        String separator;
        for (String line : content) {
            split = line.split(" ");
            if (isUnavailable == whenIsAvailable.contains(split[0])) {
                isUnavailable = !isUnavailable;
                separator = result.size() % 2 == 0 ? ";" : ";" + System.lineSeparator();
                result.add(split[1] + separator);
            }
        }
        return result;
    }

    private List<String> readerFromFile(String source) {
        List<String> content = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            content = reader.lines().toList();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return content;
    }

    private void writeToFile(List<String> content, String target) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(target))) {
            for (String s : content) {
                out.write(s);
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