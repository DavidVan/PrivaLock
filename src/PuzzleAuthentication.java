import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by David on 4/30/2016.
 */
public class PuzzleAuthentication extends Authentication {

    public PuzzleAuthentication() {
        setAuthenticationType(AuthenticationType.PUZZLE);
        setHashedForm(new byte[0]);
    }

    public PuzzleAuthentication(AuthenticationObject authObj) {
        setAuthenticationType(AuthenticationType.PUZZLE);
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
            throw new AlreadySetUpException("Puzzle already set up!");
        }
    }

    @Override
    public boolean checkAuthentication(AuthenticationObject authObj) {
        if (!isSetUp()) {
            return false;
        }
        else {
            try {
                // The puzzle in this object.
                MessageDigest systemHashedContent = MessageDigest.getInstance("MD5");
                systemHashedContent.update(this.getHashedForm());
                // The user-entered puzzle.
                MessageDigest userHashedContent = MessageDigest.getInstance("MD5");
                userHashedContent.update(authObj.getHashedForm());
                if (MessageDigest.isEqual(systemHashedContent.digest(), userHashedContent.digest())) {
                    return true; // Looks like it's the same puzzle.
                }
            }
            catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return false; // Wrong puzzle.
        }
    }

}
