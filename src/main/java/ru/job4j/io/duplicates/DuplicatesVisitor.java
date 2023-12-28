package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, List<Path>> files = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {
        FileProperty property = new FileProperty(
                file.toFile().getUsableSpace(),
                file.toFile().getName());
        if (files.containsKey(property)) {
            files.get(property).add(file.toAbsolutePath());
        } else {
            files.put(property, new ArrayList<>(List.of(file.toAbsolutePath())));
        }
        return super.visitFile(file, attributes);
    }

    public void printAllDuplicates() {
        files.keySet().stream()
                .filter(x -> files.get(x).size() > 1)
                .flatMap(x -> files.get(x).stream())
                .forEach(System.out::println);
    }
}