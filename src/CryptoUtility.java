import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by knnth on 4/30/2016.
 */
public class CryptoUtility {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORM = "AES";
    private static  byte[] password = null;

    public CryptoUtility(String password) throws NoSuchAlgorithmException {
        MessageDigest test = MessageDigest.getInstance("MD5");
        test.update(password.getBytes());
        AuthenticationObject name = new AuthenticationObject(test.digest());
        this.password = name.getHashedForm();
    }

    public static void encrypt(byte[] password, File myFile, File encryptTo) {
        doCrypto(Cipher.ENCRYPT_MODE, password, myFile, encryptTo);
    }

    public static void encrypt(File myFile, File output){
        doCrypto(Cipher.ENCRYPT_MODE, password, myFile, output);
    }

    public static void decrypt(byte[] password, File myFile, File decryptTo) {
        doCrypto(Cipher.DECRYPT_MODE, password, myFile, decryptTo);
    }

    public static void decrypt(File myFile, File decryptTo){
        doCrypto(Cipher.DECRYPT_MODE, password, myFile, decryptTo);
    }

    private static void doCrypto(int cipherMode, byte[] password, File myFile, File cryptoTo) {
        try {
            Key myPassword = new SecretKeySpec(password, ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORM);
            cipher.init(cipherMode, myPassword);
            FileInputStream input = new FileInputStream(myFile);
            byte[] inputBytes = new byte[(int) myFile.length()];
            input.read(inputBytes);
            byte[] outputBytes = cipher.doFinal(inputBytes);
            FileOutputStream convert = new FileOutputStream(cryptoTo);
            convert.write(outputBytes);
            input.close();
            convert.close();

        } catch (NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | IOException | InvalidKeyException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }
}
