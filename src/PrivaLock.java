import javax.swing.*;
import java.io.File;
import java.io.IOException;
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

        System.out.println("PrivaLock Menu:\n" +
                "1) Choose Files to Encrypt\n" +
                "2) Choose Files to Decrypt\n" +
                "3) Settings\n" +
                "4) Exit\n");
        int option = 0;
        while (option != 4){
            option = scan.nextInt();


            switch (option){

                case 1:
                    int showOpenDialog = fileChooser.showOpenDialog(null);
                    File[] uploadDir = fileChooser.getSelectedFiles();
                    for(File f : uploadDir){
                        String file = f.toString();
                        File myFile = new File(file);
                        File encrypted = new File(file + ".encrypted");
                        System.out.println("Enter Password for Encryption: ");
                        encryption = new CryptoUtility(scan.next());
                        encryption.encrypt(myFile, encrypted);
                        myFile.delete();
                    }
                    break;
                case 2:
                    int showOpenDialog2 = fileChooser.showOpenDialog(null);
                    File[] uploadDir2 = fileChooser.getSelectedFiles();
                    for(File f : uploadDir2) {
                        String file = f.toString();
                        File myFile = new File(file);
                        System.out.println("Enter Password for Encryption: ");
                        encryption = new CryptoUtility(scan.next());
                        File decrypted = new File(file.substring(0,file.lastIndexOf('.')));
                        encryption.decrypt(myFile, decrypted);
                        myFile.delete();
                    }
                    break;
                case 3:
                    break;
            }
        }





    }
}
