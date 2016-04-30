/**
 * Created by David on 4/29/2016.
 */
public abstract class Authentication {

    /*
        Base class for various ways of authentication.
     */

    private AuthenticationType authType;

    public Authentication() {
        authType = AuthenticationType.PASSWORD;
    }

    public Authentication(AuthenticationType authType) {
        this.authType = authType;
    }

    public enum AuthenticationType {
        PASSWORD, PUZZLE, FINGERPRINT, FACE, IRIS
    }

    public AuthenticationType getAuthType() {
        return authType;
    }

    public void setAuthType(AuthenticationType authType) {
        this.authType = authType;
    }

    public abstract boolean setUpAuthentication();

    public abstract boolean isAuthenticated();

}
