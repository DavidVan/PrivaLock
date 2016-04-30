import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * Created by knnth on 4/29/2016.
 */
public class PrivaLock {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Scanner fileName = new Scanner(System.in);
        System.out.println("What is the filename you want to encrypt: ");
        String file = fileName.nextLine();
        File myFile = new File(file);
        File encrypted = new File(file + ".encrypted");
        CryptoUtility encryption = new CryptoUtility();
        encryption.encrypt(myFile, encrypted);
        myFile.delete();
        File decrypted = new File(file);
        encryption.decrypt(encrypted, decrypted);
    }
}
