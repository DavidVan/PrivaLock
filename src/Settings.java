import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * This Settings class will hold settings needed by the user in order to lock files
 * This will include the user name, password, and paths of Files the user would like to protect.
 **/
public class Settings implements Serializable {
    private String username; //username used to define who is locking the files.
    private AuthenticationObject password = null; //password saved in order to lock files.
    private ArrayList<String> fileLock = new ArrayList<String>();

    public Settings() {
        password = null;
        username = null;
    }

    public Settings(String username, AuthenticationObject password) {
        this.username = username;
        this.password = password;
    }

    public void save() {
        Path folderpath = Paths.get(System.getProperty("user.home") + "\\PrivaLock\\Setttings");
        Path filepath = Paths.get(folderpath.toString(), "\\" + username + "_u.ser");
        try {
            Files.createDirectories(folderpath); //creates the directories needed relative the parent path of the file.
            FileOutputStream fileOut = new FileOutputStream(filepath.toString()); //new FileOutputStream to create the .ser file.
            ObjectOutputStream out = new ObjectOutputStream(fileOut); //new ObjectOutputStream to write objects in file.
            out.writeObject(this); //writes the setting object in the file.
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in: " + filepath.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadSave(String username) {
        Path filepath = Paths.get(System.getProperty("user.home") + "\\PrivaLock\\Setttings\\" + username + "_u.ser");
        try {
            FileInputStream fileIn = new FileInputStream(filepath.toString()); //new FileInputStream to read file
            ObjectInputStream in = new ObjectInputStream(fileIn);   //new ObjectInputStream to read in Setting Object
            Settings temp = (Settings) in.readObject();  //temporary Setting object is needed for reading and loading
            System.out.println(temp.toString());
            this.username = temp.username;
            password = temp.password;
            fileLock = new ArrayList<String>(temp.fileLock);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //inserts file paths into the fileLock Array List. This is where all protected files are stored.
    public void chooseFiles(String file) {
        fileLock.add(file);
    }

    public String toString() {
        return "\nFiles:" + fileLock.toString()
                + "\nUser:" + username +
                "\nPassword:" + password;
    }
}
