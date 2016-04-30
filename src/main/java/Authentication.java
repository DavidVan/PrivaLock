package main.java;

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
    private byte[] data; // Stores hash
    private boolean setUp = false;

    public Authentication() {

    }

    public Authentication(AuthenticationType authType) {
        this.authType = authType;
    }

    public enum AuthenticationType {
        PASSWORD, FILE, PUZZLE, FINGERPRINT, FACE, IRIS
    }

    public AuthenticationType getAuthenticationType() {
        return this.authType;
    }

    public byte[] getData() {
        return this.data;
    }

    public void setAuthenticationType(AuthenticationType authType) {
        this.authType = authType;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public void setSetUp(boolean setUp) {
        this.setUp = setUp;
    }

    public boolean isSetUp() {
        return this.setUp;
    }

    public boolean isAuthenticated() {
        return this.getAuthenticationType() != null;
    }

    public abstract boolean setUpAuthentication(AuthenticationObject authObj) throws AlreadySetUpException;

    public abstract boolean checkAuthentication(AuthenticationObject authObj);
}
