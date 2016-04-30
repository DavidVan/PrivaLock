import java.io.Serializable;

/**
 * Created by David on 4/29/2016.
 */
public class AuthenticationObject implements Serializable{

    /*
        Class to represent a form of authentication.
        For example, representing a SHA512 hash.
     */

    private byte[] data; // Stores data.

    public AuthenticationObject(byte[] data) {
        this.data = data;
    }

    public byte[] getData() {
        return data;
    }

    @Override
    public String toString() {
        return byteToHex(this.data); // If data is a hash, returns hex view of it.
    }

    public String byteToHex(byte[] hashedContent) {
        String output = "";
        for (int i = 0; i < hashedContent.length; i++) {
            output += String.format("%02X", hashedContent[i]);
        }
        return output;
    }

}
