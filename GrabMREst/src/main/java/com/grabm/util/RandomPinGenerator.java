package com.grabm.util;

import java.util.Random;

/**
 *
 * @author Sachith Dickwella
 */
public abstract class RandomPinGenerator {

    private static final int[] SPECIAL_CHAR = {48, 57};

    public static String getPIN() {
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int nmbr = random.nextInt(SPECIAL_CHAR[1] - SPECIAL_CHAR[0]) + SPECIAL_CHAR[0];
            builder.append((char) nmbr);
        }
        return builder.toString();
    }
}
