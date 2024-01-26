package qualite_log.tool;

import javax.crypto.SecretKey;

public class Encryption {
    private static final int KEY_SIZE = 128;
    private static final int IV_SIZE = 12; // GCM mode requires a 12-byte IV
    private static final int TAG_SIZE = 128; // Size of authentication tag in bits
    private static SecretKey secretKey;

    public static String encrypt(String str) {

        return str;
    }

    public static String decrypt(String encryptedText) {
        return encryptedText;
    }
}
