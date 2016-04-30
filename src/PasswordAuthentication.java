import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by David on 4/29/2016.
 */
public class PasswordAuthentication extends Authentication {

    private boolean setUp = false;

    public PasswordAuthentication() {
        setAuthenticationType(AuthenticationType.PASSWORD);
        setHashedForm(new byte[1]);
    }

    public PasswordAuthentication(AuthenticationObject authObj) {
        setAuthenticationType(AuthenticationType.PASSWORD);
        setHashedForm(authObj.getHashedForm());
        setUp = true;
    }

    @Override
    public boolean setUpAuthentication(AuthenticationObject authObj) throws AlreadySetUpException {
        if (!setUp) {
            setHashedForm(authObj.getHashedForm());
            return true;
        }
        else {
            throw new AlreadySetUpException("Password already set up!");
        }
    }

    @Override
    public boolean isAuthenticated() {
        return this.getAuthenticationType() != null;
    }

    @Override
    public boolean checkAuthentication(AuthenticationObject authObj) {
        if (!setUp) {
            return false;
        }
        else {
            try {
                // The password in this object.
                MessageDigest systemEncryptedContent = MessageDigest.getInstance("SHA-512");
                systemEncryptedContent.update(this.getHashedForm());
                // The user-entered password.
                MessageDigest userEncryptedContent = MessageDigest.getInstance("SHA-512");
                userEncryptedContent.update(authObj.getHashedForm());
                if (MessageDigest.isEqual(systemEncryptedContent.digest(), userEncryptedContent.digest())) {
                    return true; // Looks like it's the same password.
                }
            }
            catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return false; // Wrong password.
        }
    }
}
