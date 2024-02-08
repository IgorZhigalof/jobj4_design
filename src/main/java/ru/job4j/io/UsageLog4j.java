package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        boolean first = true;
        String second = "2";
        byte third = 3;
        short fourth = 4;
        int fifth = 5;
        long sixth = 6L;
        float seventh = 7F;
        double eighth = 8;
        LOG.debug("first: {}, second: {}, third: {}, fourth: {}, fifth: {}, sixth: {}, seventh: {}, eighth: {}",
                first, second, third, fourth, fifth, sixth, seventh, eighth
                );
    }
}