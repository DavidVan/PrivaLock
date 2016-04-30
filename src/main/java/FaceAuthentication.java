/**
 * Created by David on 4/30/2016.
 */
public class FaceAuthentication extends Authentication {

    public FaceAuthentication() {
        setAuthenticationType(AuthenticationType.FACE);
        setData(new byte[0]);
    }

    public FaceAuthentication(AuthenticationObject authObj) {
        setAuthenticationType(AuthenticationType.FACE);
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
            throw new AlreadySetUpException("Face already set up!");
        }
    }

    @Override
    public boolean checkAuthentication(AuthenticationObject authObj) {
        if (!isSetUp()) {
            return false;
        }
        else {
        }
    }

}
