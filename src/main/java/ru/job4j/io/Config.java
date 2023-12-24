package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() throws IllegalArgumentException {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            reader.lines()
                    .filter(x -> !x.isBlank() && !x.trim().startsWith("#"))
                    .forEach(this::addValue);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private void addValue(String content) throws IllegalArgumentException {
        isCorrect(content);
        int indexOfEquals = content.indexOf("=");
        String key = content.substring(0, indexOfEquals);
        String value = content.substring(indexOfEquals + 1);
        values.put(key, value);
    }

    private void isCorrect(String content) {
        String[] split = content.split("=");
        if (!content.contains("=")) {
            throw new IllegalArgumentException("Missing equals in a line");
        }
        if (split[0].isEmpty()) {
            throw new IllegalArgumentException("Missing a key in a line");
        }
        if (split.length < 2) {
            throw new IllegalArgumentException("Missing a value in a line");
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
    }

}