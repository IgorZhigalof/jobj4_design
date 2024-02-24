package ru.job4j.serialization.java;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GSONUsage {
    public static void main(String[] args) {
        final Lion lion = new Lion(true, (byte) 20, "breed", new Sound(), new String[]{"First", "Second"});
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(lion));
        String lionJson =
                "{"
                        + "\"isAdult\":true,"
                        + "\"age\":20,"
                        + "\"breed\":\"breed\","
                        + "\"sound\":{\"sound\":\"Roar\"},"
                        + "\"offspring\":[\"First\",\"Second\"]"
                        + "}";
        Lion lionMod = gson.fromJson(lionJson, Lion.class);
        System.out.println(lionMod);

    }
}
