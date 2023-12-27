package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, Path> files = new HashMap<>();
    private Set<FileProperty> duplicatedFiles = new HashSet<>();

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {
        FileProperty property = new FileProperty(
                file.toFile().getUsableSpace(),
                file.toFile().getName());
        if (duplicatedFiles.contains(property)) {
            System.out.println(file.toAbsolutePath());
        } else if (files.containsKey(property)) {
            duplicatedFiles.add(property);
            System.out.println(file.toAbsolutePath());
            System.out.println(files.get(property));
            files.remove(property);
        } else {
            files.put(property, file.toAbsolutePath());
        }
        return super.visitFile(file, attributes);
    }
}