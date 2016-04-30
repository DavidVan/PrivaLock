/**
 * Created by David on 4/29/2016.
 */
public class AuthenticationObject {

    /*
        Class to represent a form of authentication.
        For example, representing a SHA512 hash.
     */

    private byte[] encryptedForm; // Stores hash.

    public AuthenticationObject(byte[] encryptedForm) {
        this.encryptedForm = encryptedForm;
    }

    public byte[] getEncryptedForm() {
        return encryptedForm;
    }

    @Override
    public String toString() {
        return byteToHex(this.encryptedForm);
    }

    public String byteToHex(byte[] encryptedContent) {
        String output = "";
        for (int i = 0; i < encryptedContent.length; i++) {
            output += String.format("%02X", encryptedContent[i]);
        }
        return output;
    }

}
