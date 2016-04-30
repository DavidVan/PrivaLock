import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.nio.file.Files;
/**
 * Created by knnth on 4/29/2016.
 */
public class Settings implements Serializable{
    private String username; //username used to define who is locking the files.
    private String password = null; //password saved in order to lock files.
    private ArrayList<String> fileLock = new ArrayList<String>();

    public Settings() {
        password = null;
        username = null;
    }

    public Settings(String username , String password){
        this.username = username;
        this.password = password;
    }

    public void save() {
        File settingsfile = new File(System.getProperty("user.home") + "\\PrivaLock\\Setttings\\u.ser");
        settingsfile.getParentFile().mkdirs(); //creates the directories needed relative the parent path of the file.

        Settings current = new Settings("Ryan", "password");
        try {
            FileOutputStream fileOut = new FileOutputStream(settingsfile); //new FileOutputStream to create the .ser file.
            ObjectOutputStream out = new ObjectOutputStream(fileOut); //new ObjectOutputStream to write objects in file.
            out.writeObject(current); //writes the setting object in the file.
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in: "+  settingsfile.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadSave(){
        /**
         * Should load the custom save file/settings file that was created from save. (Add a parameter to the method)
         */
    }

    public void setPassword(String password){

        this.password = password;

    }

    public void chooseFiles(String file){
        fileLock.add(file);
    }

}
