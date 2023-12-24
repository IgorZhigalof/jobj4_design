package ru.job4j.map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class User {
    String name;
    int children;
    Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        String name = "name";
        int children = 1;
        Calendar birthday = new Calendar.Builder()
                .setDate(1995, 11, 11)
                .build();
        User user = new User(name, children, birthday);
        User user1 = new User(name, children, birthday);
        int hash = user.hashCode();
        int hash1 = user1.hashCode();
        Map<User, Object> map = new HashMap<>(8);
        int bucket = hash & 7;
        int bucket1 = hash1 & 7;
        map.put(user, new Object());
        map.put(user1, new Object());
        map.keySet().forEach(System.out::println);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }
}
