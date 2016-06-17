package com.grabm.util;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import org.apache.log4j.Logger;

/**
 *
 * @author Sachith Dickwella
 */
public class Encryptor {

    /**
     * Instantiate the log4j reference.
     */
    public static final Logger LOGGER = Logger.getLogger(Encryptor.class);

    public static final Charset CHARSET_UTF8 = Charset.forName("UTF-8");

    /**
     * Digest algorithm list enum.
     */
    public static enum Algorithm {

        MD5, SHA_1, SHA_256, SHA_384, SHA_512;

        @Override
        public String toString() {
            switch (this) {
                case SHA_1:
                    return "SHA-1";
                case SHA_256:
                    return "SHA-256";
                case SHA_384:
                    return "SHA-384";
                case SHA_512:
                    return "SHA-512";
                case MD5:
                    return "MD5";
                default:
                    return "";
            }
        }
    }

    public static String encrypt(Algorithm algorithm, String value) {
        try {

            MessageDigest digest = MessageDigest.getInstance(algorithm.toString());
            byte[] buffer = digest.digest(value.getBytes(CHARSET_UTF8));

            StringBuilder builder = new StringBuilder();
            for (byte bit8 : buffer) {
                builder.append(String.format("%x", bit8));
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException ex) {
            LOGGER.error(Arrays.asList(ex.getStackTrace()));
        }
        return null;
    }
}
