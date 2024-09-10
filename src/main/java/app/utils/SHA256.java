package app.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 {

    private static final MessageDigest digest;

    static {
        try {
            digest = MessageDigest.getInstance("SHA3-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String hashString(String string) {
        final byte[] hashbytes = digest.digest(string.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(hashbytes);
    }

    private static String bytesToHex(byte[] hashbytes) {
        StringBuilder hex = new StringBuilder();
        for (byte b : hashbytes) {
            hex.append(String.format("%02X", b));
        }
        return hex.toString();
    }
}
