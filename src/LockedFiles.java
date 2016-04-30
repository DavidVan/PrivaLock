import java.io.File;
import java.io.FilePermission;
/**
 * Created by knnth on 4/29/2016.
 */

public class LockedFiles {
    private String path;
    private regularSecurityManager AccessHandler = new regularSecurityManager("david");
    private FilePermission permission;
    private File check;
    public LockedFiles(String file){
        check = new File(file);
        check.setReadable(false);
        check.setWritable(false);
        path = file;
        permission = new FilePermission(path, "write");
    }

    public void hasAccess(){
        if(AccessHandler != null){
            AccessHandler.checkRead(path);
        }
    }

}
