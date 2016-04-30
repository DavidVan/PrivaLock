/**
 * Created by David on 4/29/2016.
 */
public class AuthenticationObject {

    /*
        Class to represent a form of authentication.
        For example, representing a SHA512 hash.
     */

    private byte[] hashedForm; // Stores hash.

    public AuthenticationObject(byte[] hashedForm) {
        this.hashedForm = hashedForm;
    }

    public byte[] getHashedForm() {
        return hashedForm;
    }

    @Override
    public String toString() {
        return byteToHex(this.hashedForm);
    }

    public String byteToHex(byte[] hashedContent) {
        String output = "";
        for (int i = 0; i < hashedContent.length; i++) {
            output += String.format("%02X", hashedContent[i]);
        }
        return output;
    }

}
