import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by David on 4/29/2016.
 */
public class PasswordAuthentication extends Authentication {

    private boolean setUp = false;

    public PasswordAuthentication() {
        setAuthenticationType(AuthenticationType.PASSWORD);
        setEncryptedForm(new byte[1]);
    }

    public PasswordAuthentication(AuthenticationObject authObj) {
        setAuthenticationType(AuthenticationType.PASSWORD);
        setEncryptedForm(authObj.getEncryptedForm());
        setUp = true;
    }

    @Override
    public boolean setUpAuthentication(AuthenticationObject authObj) throws AlreadySetUpException {
        if (!setUp) {
            setEncryptedForm(authObj.getEncryptedForm());
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
        if (this.getEncryptedForm().length == 1) {
            return false;
        }
        else {
            try {
                // The password in this object.
                MessageDigest systemEncryptedContent = MessageDigest.getInstance("SHA-512");
                systemEncryptedContent.update(this.getEncryptedForm());
                // The user-entered password.
                MessageDigest userEncryptedContent = MessageDigest.getInstance("SHA-512");
                userEncryptedContent.update(authObj.getEncryptedForm());
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
