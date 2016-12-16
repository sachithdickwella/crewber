package com.earcs.grabm.util;

import java.util.Random;

/**
 *
 * @author Roshin Perera
 */
public abstract class RandomString {

    private static final int[] SPECIAL_CHAR = {35, 36},
            UPPERCASE_CHAR = {64, 90},
            LOWERCASE_CHAR = {97, 122};

    public static String getRandomString() {
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            int end = random.nextInt(LOWERCASE_CHAR[1] - LOWERCASE_CHAR[0]) + LOWERCASE_CHAR[0];
            builder.append((char) end);
        }
        for (int i = 0; i < 5; i++) {
            int start = random.nextInt(UPPERCASE_CHAR[1] - UPPERCASE_CHAR[0]) + UPPERCASE_CHAR[0];
            builder.append((char) start);
        }
        int middle = random.nextInt(SPECIAL_CHAR[1] - SPECIAL_CHAR[0]) + SPECIAL_CHAR[0];
        builder.append((char) middle);
        return builder.toString();
    }
}
