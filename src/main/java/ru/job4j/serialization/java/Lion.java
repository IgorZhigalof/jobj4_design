package ru.job4j.serialization.java;

import java.util.Arrays;

public class Lion {
    private boolean isAdult;
    private byte age;
    private String breed;
    private Sound sound;
    private String[] offspring;

    public Lion(boolean isAdult, byte age, String breed, Sound sound, String[] offspring) {
        this.isAdult = isAdult;
        this.age = age;
        this.offspring = offspring;
        this.breed = breed;
        this.sound = sound;
    }

    @Override
    public String toString() {
        return "Lion{"
                + "isAdult=" + isAdult
                + ", age=" + age
                + ", breed='" + breed + '\''
                + ", sound=" + sound
                + ", offspring=" + Arrays.toString(offspring)
                + '}';
    }
}


