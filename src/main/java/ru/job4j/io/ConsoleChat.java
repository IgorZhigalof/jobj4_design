package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        boolean isActive = true;
        boolean shouldAnswer = true;
        Scanner scanner = new Scanner(System.in);
        List<String> phrases = readPhrases();
        while (isActive) {
            String content = scanner.nextLine();
            if (OUT.equals(content)) {
                isActive = false;
                shouldAnswer = false;
            } else if (STOP.equals(content)) {
                shouldAnswer = false;
            } else if (CONTINUE.equals(content)) {
                shouldAnswer = true;
            }
            if (shouldAnswer) {
                String phrase = phrases.get((int) (Math.random() * phrases.size()));
                System.out.println(phrase);
                saveLog(List.of(content, phrase));
            } else {
                saveLog(List.of(content));
            }

        }
    }

    private List<String> readPhrases() {
        List<String> content = new ArrayList<>(List.of("Some kind of error"));
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers))) {
            content = reader.lines().toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    private void saveLog(List<String> log) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            writer.write(
                          "user: "
                        + String.join(System.lineSeparator() + "bot: ", log)
                        + System.lineSeparator()
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("data/ConsoleChatLog", "data/ConsoleChatPhrases");
        consoleChat.run();
    }
}