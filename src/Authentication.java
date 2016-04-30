/**
 * Created by David on 4/29/2016.
 */
public abstract class Authentication {

    public static class AlreadySetUpException extends Exception {
        public AlreadySetUpException(String message) {
            super(message);
        }
    }

    /*
        Base class for various ways of authentication.
     */

    private AuthenticationType authType;
    private byte[] encryptedForm; // Stores hash

    public Authentication() {

    }

    public Authentication(AuthenticationType authType) {
        this.authType = authType;
    }

    public enum AuthenticationType {
        PASSWORD, PUZZLE, FINGERPRINT, FACE, IRIS
    }

    public AuthenticationType getAuthenticationType() {
        return this.authType;
    }

    public byte[] getEncryptedForm() {
        return this.encryptedForm;
    }

    public void setAuthenticationType(AuthenticationType authType) {
        this.authType = authType;
    }

    public void setEncryptedForm(byte[] encryptedForm) {
        this.encryptedForm = encryptedForm;
    }

    public abstract boolean setUpAuthentication(AuthenticationObject authObj) throws AlreadySetUpException;

    public abstract boolean isAuthenticated();

    public abstract boolean checkAuthentication(AuthenticationObject authObj);
}
