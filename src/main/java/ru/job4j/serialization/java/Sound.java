package ru.job4j.serialization.java;

public class Sound {
    private String sound = "Roar";
    public void sound() {
        System.out.println(sound);
    }

    @Override
    public String toString() {
        return "Sound{"
                + "sound='" + sound + '\''
                + '}';
    }
}