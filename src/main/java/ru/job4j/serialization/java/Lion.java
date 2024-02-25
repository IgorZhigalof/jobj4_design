package ru.job4j.serialization.java;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;

@XmlRootElement(name = "lion")
@XmlAccessorType(XmlAccessType.FIELD)
public class Lion {
    @XmlAttribute
    private boolean isAdult;

    @XmlAttribute
    private byte age;
    private String breed;
    private Sound sound;
    private String[] offspring;

    public Lion() { }

    public Lion(boolean isAdult, byte age, String breed, Sound sound, String[] offspring) {
        this.isAdult = isAdult;
        this.age = age;
        this.offspring = offspring;
        this.breed = breed;
        this.sound = sound;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public byte getAge() {
        return age;
    }

    public String getBreed() {
        return breed;
    }

    public Sound getSound() {
        return sound;
    }

    public String[] getOffspring() {
        return offspring;
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


