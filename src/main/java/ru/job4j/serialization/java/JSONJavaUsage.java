package ru.job4j.serialization.java;

import org.json.JSONObject;

public class JSONJavaUsage {
    public static void main(String[] args) {
        final Lion lion = new Lion(true, (byte) 20, "breed", new Sound(), new String[]{"First", "Second"});
        JSONObject json = new JSONObject(lion);
        System.out.println(json);
    }
}
