import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by David on 4/30/2016.
 */
public class FileAuthentication extends Authentication {

    public FileAuthentication() {
        setAuthenticationType(AuthenticationType.FILE);
        setHashedForm(new byte[0]);
    }

    public FileAuthentication(AuthenticationObject authObj) {
        setAuthenticationType(AuthenticationType.FILE);
        setHashedForm(authObj.getHashedForm());
        setSetUp(true);
    }

    @Override
    public boolean setUpAuthentication(AuthenticationObject authObj) throws AlreadySetUpException {
        if (!isSetUp()) {
            setHashedForm(authObj.getHashedForm());
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
                MessageDigest systemHashedContent = MessageDigest.getInstance("SHA-512");
                systemHashedContent.update(this.getHashedForm());
                // The user-entered file.
                MessageDigest userHashedContent = MessageDigest.getInstance("SHA-512");
                userHashedContent.update(authObj.getHashedForm());
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
