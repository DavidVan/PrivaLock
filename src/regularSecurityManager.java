import java.io.DataInputStream;
import java.io.FileDescriptor;
import java.io.IOException;

/**
 * Created by knnth on 4/30/2016.
 */
public class regularSecurityManager extends SecurityManager {
    private String password;

    public regularSecurityManager(String password){
        super();
        this.password = password;
    }
    private boolean hasAccess(){
        DataInputStream dis = new DataInputStream(System.in);
        String response;
        System.out.println("Password :");
        try {
            response = dis.readLine();
            if(response.equals(password))
                return true;
            else
                return false;
        }
        catch (IOException e){
            return false;
        }
    }

    public void checkRead(FileDescriptor filedescriptor) {
        if (!hasAccess())
            throw new SecurityException("Not a Chance!");
    }
    public void checkRead(String filename) {
        if (!hasAccess())
            throw new SecurityException("No Way!");
    }
    public void checkRead(String filename, Object executionContext) {
        if (!hasAccess())
            throw new SecurityException("Forget It!");
    }
    public void checkWrite(FileDescriptor filedescriptor) {
        if (!hasAccess())
            throw new SecurityException("Not!");
    }
    public void checkWrite(String filename) {
        if (!hasAccess())
            throw new SecurityException("Not Even!");
    }
}
