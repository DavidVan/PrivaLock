import java.util.ArrayList;

/**
 * Created by knnth on 4/29/2016.
 */
public class Settings<V> {

    private String password = null;
    private ArrayList<V> fileLock;
    public Settings() {
        this.password = null;
    }

    public Settings(String password){
        this.password = password;
    }

    /**
     * You want to be able to save the current state of settings.. Implement here.
     */
    public void save(){

    }

    public void setPassword(String password){

        this.password = password;

    }

    public void chooseFiles(V file){
        fileLock.add(file);
    }

}
