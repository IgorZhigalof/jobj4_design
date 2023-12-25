package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.*;

class AnalysisTest {
    @Test
    void whenSingleTimeInterval(@TempDir Path tempDir) {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.println("200 10:56:01\n"
                        + "300 10:57:01\n"
                        + "400 10:58:01\n"
                        + "300 10:59:01\n"
                        + "200 11:01:02\n"
                        + "200 11:02:02"
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        File target  = tempDir.resolve("target.txt").toFile();
        Analysis analysis = new Analysis();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder result = new StringBuilder();
        try (BufferedReader input = new BufferedReader(new FileReader(target))) {
            input.lines().forEach(result::append);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat("10:58:01;10:59:01;").hasToString(result.toString());
    }

    @Test
    void whenSeveralTimeInterval(@TempDir Path tempDir) {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.println("200 10:56:01\n"
                    + "300 10:57:01\n"
                    + "400 10:58:01\n"
                    + "300 10:59:01\n"
                    + "400 11:01:02\n"
                    + "200 11:02:02"
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        File target  = tempDir.resolve("target.txt").toFile();
        Analysis analysis = new Analysis();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder result = new StringBuilder();
        try (BufferedReader input = new BufferedReader(new FileReader(target))) {
            input.lines().forEach(result::append);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat("10:58:01;10:59:01;11:01:02;11:02:02;").hasToString(result.toString());
    }
}