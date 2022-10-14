package core;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Token {
    public String token;

    public void store() {

    }

    public void searchUser() {

    }

    protected byte[] encrypt(String data) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        return digest.digest(data.getBytes(StandardCharsets.UTF_8));
    }
}
