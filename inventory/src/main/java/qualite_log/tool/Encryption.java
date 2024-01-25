package qualite_log.tool;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

public class Encryption {
    private static final int KEY_SIZE = 128;
    private static final int IV_SIZE = 12; // GCM mode requires a 12-byte IV
    private static final int TAG_SIZE = 128; // Size of authentication tag in bits
    private static SecretKey secretKey;

    public static String encrypt(String str) {
        try {
            // Generate a random key for AES
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(KEY_SIZE);
            secretKey = keyGen.generateKey();

            // Generate a random IV for GCM
            SecureRandom secureRandom = new SecureRandom();
            byte[] ivBytes = new byte[IV_SIZE];
            secureRandom.nextBytes(ivBytes);
            GCMParameterSpec gcmSpec = new GCMParameterSpec(TAG_SIZE, ivBytes);

            // Initialize the cipher
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, gcmSpec);

            // Encrypt the data
            byte[] strBytes = str.getBytes(StandardCharsets.UTF_8);
            byte[] encrypted = cipher.doFinal(strBytes);

            // Concatenate the IV with the encrypted data
            byte[] result = new byte[IV_SIZE + encrypted.length];
            System.arraycopy(ivBytes, 0, result, 0, IV_SIZE);
            System.arraycopy(encrypted, 0, result, IV_SIZE, encrypted.length);

            return Base64.getEncoder().encodeToString(result);

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
                | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        return null;
    }

    public static String decrypt(String encryptedText) {
        try {
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);

            // Extract the IV from the first IV_SIZE bytes
            byte[] ivBytes = new byte[IV_SIZE];
            System.arraycopy(encryptedBytes, 0, ivBytes, 0, IV_SIZE);
            GCMParameterSpec gcmSpec = new GCMParameterSpec(TAG_SIZE, ivBytes);

            // Initialize the cipher
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, gcmSpec);

            // Decrypt the data (starting from index IV_SIZE)
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes, IV_SIZE, encryptedBytes.length - IV_SIZE);

            return new String(decryptedBytes, StandardCharsets.UTF_8);

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
                | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        return null;
    }
}
