import javax.swing.*;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * Created by knnth on 4/29/2016.
 */
public class PrivaLock {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        JFileChooser fileChooser = new JFileChooser(System.getProperty("user.home") + "\\Desktop");
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        CryptoUtility encryption;
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Password for Encryption: ");
        encryption = new CryptoUtility(scan.next());
        System.out.println("\n\n");

        int option = 0;
        while (option != 4){
            System.out.println("\nPrivaLock Menu:\n" +
                    "1) Choose Files to Encrypt\n" +
                    "2) Choose Files to Decrypt\n" +
                    "3) Settings\n" +
                    "4) Exit\n");
            option = scan.nextInt();

            switch (option){
                case 1:
                    fileChooser.showOpenDialog(null);
                    File[] uploadDir = fileChooser.getSelectedFiles();
                    for(File f : uploadDir){
                        String file = f.toString();
                        File myFile = new File(file);
                        File encrypted = new File(file + ".encrypted");
                        encryption.encrypt(myFile, encrypted);
                        System.out.println("Successfully Encrypted: " + file);
                    }
                    break;
                case 2:
                    fileChooser.showOpenDialog(null);
                    File[] uploadDir2 = fileChooser.getSelectedFiles();
                    for(File f : uploadDir2) {
                        String file = f.toString();
                        File myFile = new File(file);
                        System.out.println("Enter Password for Encryption: ");
                        MessageDigest test = MessageDigest.getInstance("MD5");
                        test.update(scan.next().getBytes());
                        AuthenticationObject name = new AuthenticationObject(test.digest());
                        File decrypted = new File(file.substring(0,file.lastIndexOf('.')));
                        encryption.decrypt(name.getData(),myFile, decrypted);
                    }
                    break;
                case 3:
                    break;
            }
        }





    }
}
