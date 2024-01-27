package qualite_log.data_import.tool;

import static org.junit.Assert.assertEquals;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.junit.Test;

import qualite_log.tool.Encryption;

public class EncryptionTest {
    @Test
    public void givenString_whenEncrypt_thenSuccess()
        throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException,
        BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException { 
        
        String input = "passwordsecurisé";

        String cipherText = Encryption.encrypt(input);
        String plainText = Encryption.decrypt(cipherText);
        
        assertEquals(input, plainText);
    }
}
