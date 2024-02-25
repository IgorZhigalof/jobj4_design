package ru.job4j.serialization.java;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "sound")
public class Sound {
    @XmlAttribute
    private String sound = "Roar";

    public Sound() { }

    public String getSound() {
        return sound;
    }

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