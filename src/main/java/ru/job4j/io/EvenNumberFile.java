package ru.job4j.io;

import java.io.FileInputStream;
import java.util.Objects;

public class EvenNumberFile {
    public static void main(String[] args) {
        StringBuilder text = new StringBuilder();
        try (FileInputStream in = new FileInputStream("data/even.txt")) {
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] content = text.toString().split(System.lineSeparator());
        for (String s : content) {
            System.out.println(s + " " + (Integer.parseInt(s) % 2 == 0));
        }
    }
}