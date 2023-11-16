package ru.job4j.question;

import java.util.*;

public class Analyze {
    public static Info diff(Set<User> previous, Set<User> current) {
        List<Integer> previousIds = new ArrayList<>();
        int added = 0;
        int updated = 0;
        int preDeleted = 0;
        for (User user : previous) {
            previousIds.add(user.getId());
            if (!current.contains(user)) {
                preDeleted++;
            }
        }
        for (User user : current) {
            if (previous.contains(user)) {
                continue;
            }
            if (previousIds.contains(user.getId())) {
                updated++;
            } else {
                added++;
            }
        }
        return new Info(added, updated, preDeleted - updated);
    }
}