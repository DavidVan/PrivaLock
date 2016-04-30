import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by David on 4/30/2016.
 */
public class FileAuthentication extends Authentication {

    public FileAuthentication() {
        setAuthenticationType(AuthenticationType.FILE);
        setData(new byte[0]);
    }

    public FileAuthentication(AuthenticationObject authObj) {
        setAuthenticationType(AuthenticationType.FILE);
        setData(authObj.getData());
        setSetUp(true);
    }

    @Override
    public boolean setUpAuthentication(AuthenticationObject authObj) throws AlreadySetUpException {
        if (!isSetUp()) {
            setData(authObj.getData());
            return true;
        }
        else {
            throw new AlreadySetUpException("File already set up!");
        }
    }

    @Override
    public boolean checkAuthentication(AuthenticationObject authObj) {
        if (!isSetUp()) {
            return false;
        }
        else {
            try {
                // The file in this object.
                MessageDigest systemHashedContent = MessageDigest.getInstance("MD5");
                systemHashedContent.update(this.getData());
                // The user-entered file.
                MessageDigest userHashedContent = MessageDigest.getInstance("MD5");
                userHashedContent.update(authObj.getData());
                if (MessageDigest.isEqual(systemHashedContent.digest(), userHashedContent.digest())) {
                    return true; // Looks like it's the same file.
                }
            }
            catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return false; // Wrong file.
        }
    }

}
