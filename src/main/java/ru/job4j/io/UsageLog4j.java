package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        boolean first = true;
        String second = "1";
        byte third = 2;
        short fourth = 3;
        int fifth = 4;
        long sixth = 5L;
        float seventh = 6F;
        double eighth = 7;
        LOG.debug("first: {}, second: {}, third: {}, fourth: {}, fifth: {}, sixth: {}, seventh: {}, eighth: {}",
                first, second, third, fourth, fifth, sixth, seventh, eighth
                );
    }
}