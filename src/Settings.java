import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.nio.file.Files;
/**
 * Created by knnth on 4/29/2016.
 */
public class Settings implements Serializable{

    private String password = null;
    private ArrayList<String> fileLock = new ArrayList<String>();
    public Settings() {
        this.password = null;
    }

    public Settings(String password){
        this.password = password;
    }

    public void save()throws IOException{
        Path settings= Paths.get(System.getProperty("user.home")+"\\PrivaLock\\Setttings");
        System.out.println("!!!");
        System.out.println(settings.toString());
        System.out.println("!!!");
        if(!Files.exists(settings)){
        }
        /**
         * You want to be able to save the current state of settings.. Implement here. If there was a setting left unmarked, will prompt error accordingly
         */
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
