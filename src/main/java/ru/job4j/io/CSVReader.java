package ru.job4j.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CSVReader {
    public static void handle(ArgsName argsName) throws IOException {
        try (Scanner scanner = new Scanner(new File(argsName.get("path")));
             BufferedWriter writer = new BufferedWriter(new FileWriter(argsName.get("out"), true))) {
            String[] columns = scanner
                    .nextLine()
                    .split(argsName.get("delimiter"));
            ArrayList<Integer> necessaryColumns = getNumbersOfColumns(
                    columns,
                    argsName.get("filter")
            );
            String firstLineToWrite = necessaryColumns
                    .stream()
                    .map(x -> columns[x])
                    .collect(Collectors.joining(argsName.get("delimiter")));
            writer.write(firstLineToWrite + System.lineSeparator());
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(argsName.get("delimiter"), -1);
                String toWrite = necessaryColumns
                        .stream()
                        .map(x -> line[x])
                        .collect(Collectors.joining(argsName.get("delimiter")));
                writer.write(toWrite + System.lineSeparator());
            }
        } catch (IOException e) {
            throw e;
        }
    }

    private static ArrayList<Integer> getNumbersOfColumns(String[] columns, String rawParams) throws IOException {
        String[] params = rawParams.split(",");
        ArrayList<Integer> numbersOfColumns = new ArrayList<>();
        for (String param : params) {
            for (int i = 0; i < columns.length; i++) {
                if (columns[i].equals(param)) {
                    numbersOfColumns.add(i);
                }
            }

        }
        if (numbersOfColumns.isEmpty()) {
            throw new IOException("There are no columns matching the filter");
        }
        return numbersOfColumns;
    }

    private static void validate(ArgsName argsName) {
        if (!Pattern.compile("\\S+\\.csv").matcher(argsName.get("path")).matches()) {
            throw new IllegalArgumentException("Incorrect input of the path param");
        }
        if (argsName.get("delimiter").length() == 0) {
            throw new IllegalArgumentException("Missing the delimiter param");
        }
        String out = argsName.get("out");
        if (!"stdout".equals(out) && !Files.isDirectory(Path.of(out).getParent())) {
            throw new IllegalArgumentException("Incorrect input of the out param");
        }
    }

    public static void main(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        validate(argsName);
        try {
            handle(argsName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}