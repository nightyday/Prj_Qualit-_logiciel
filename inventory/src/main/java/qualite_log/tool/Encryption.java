package qualite_log.tool;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/*
 * @author arthur
 * 
 * Pour cette classe j'ai ré-utilisé le code à : 
 * https://www.baeldung.com/java-aes-encryption-decryption
 */
public class Encryption {
    private static SecretKey secretKey = generateKey();
    private static GCMParameterSpec iv = generateIv();

    public static String encrypt(String input) {
        try {
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);

            byte[] cipherText = cipher.doFinal(input.getBytes());

            return Base64.getEncoder().encodeToString(cipherText);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException |
                InvalidAlgorithmParameterException | InvalidKeyException |
                BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();

            return "";
        }      
    }

    public static String decrypt(String cipherText)  {
        try {
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding"); 
            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);

            byte[] plainText = cipher.doFinal(Base64.getDecoder()
                    .decode(cipherText));

            return new String(plainText);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException |
                InvalidAlgorithmParameterException | InvalidKeyException |
                BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();

            return "";
        }
    }

    private static SecretKey generateKey() {
        String password = "nfg-G6bv?8PNta1t";
        String salt = "AL$hTFrP*]fX3JP#";

        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);

            return new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");

        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            e.printStackTrace();

            return null;
        }
    }

    private static GCMParameterSpec generateIv() {
        byte[] iv = "LTP.DA.Tj2pX$yx?".getBytes();
        return new GCMParameterSpec(128, iv);
    }
}
