package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            sources.forEach(x -> packFile(zip, x.toFile(), target));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void packFile(ZipOutputStream zip, File source, File target) {
        try {
            zip.putNextEntry(
                    new ZipEntry(target
                                    .toPath()
                                    .toAbsolutePath()
                                    .relativize(source
                                            .toPath()
                                            .toAbsolutePath())
                                    .toFile()
                                    .getPath()
                    )
            );
            try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(output.readAllBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(output.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void validate(String excludeArgument, Path outputArgument, Path directoryArgument) {
        if (!excludeArgument.startsWith(".")) {
            throw new IllegalArgumentException("The 'e' argument must start with a dot");
        }
        if (excludeArgument.length() < 2) {
            throw new IllegalArgumentException("The 'e' argument must contain 2 or more characters");
        }
        if (Files.notExists(directoryArgument)) {
            throw new IllegalArgumentException(String.format("The file %s doesn't exist", directoryArgument.toAbsolutePath()));
        }
        if (!Files.isDirectory(directoryArgument)) {
            throw new IllegalArgumentException(String.format("The file %s isn't a directory", directoryArgument.toAbsolutePath()));
        }
        if (!outputArgument.toFile().getName().endsWith(".zip")) {
            throw new IllegalArgumentException("An output directory must have a 'zip' extension");
        }
    }

    public static void main(String[] args) {
        Zip zip = new Zip();
        ArgsName argsName = ArgsName.of(args);
        validate(argsName.get("e"), Path.of(argsName.get("o")), Path.of(argsName.get("d")));
        try {
            zip.packFiles(
                    Search.search(
                            Path.of(argsName.get("d")),
                            x -> !x.toFile().getName().endsWith(argsName.get("e"))),
                    new File(argsName.get("o")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}