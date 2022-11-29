package core;

import java.security.MessageDigest;
import java.util.Base64;
import java.util.Random;

import classes.BaseClass;
import classes.User;

public class Token extends BaseClass {
    public String token;
    public String id_user;

    public String store(String id_user) {
        String token = Token.sha256(Token.randomString() + id_user);
        String query = String.format("insert into user_token (id_user, token) values ('%s', '%s')", id_user, token);
        this.token = token;
        this.id_user = id_user;
        this.conn.executeQuery(query);
        return token;
    }

    public boolean destroy(String authorization) {
		String auth = authorization.substring(7);
        String query = String.format("delete from user_token where token = '%s'", auth);
        this.conn.executeQuery(query);
        return true;
    }

    public String basicAuth(String authorization) {
        String[] parts = Token.base64_decode(authorization).split(":");
        String username = parts[0];
        String password = Token.sha256(parts[1]);
        String where = String.format("username='%s' and password='%s'", username, password);

        User user = new User();

        user.getUserDatabase(where);
        String id_user = user.getId();
        Boolean is_admin = user.getIsAdmin();
        String admin = "false";
        if (is_admin) {
            admin = "true";
        }
        username = user.getUsername();

        if (id_user != "") {
            String token = this.store(id_user);
            String[] keys = {"username", "token", "id_user", "is_admin"};
            String[] values = {username, token, id_user, admin};
            return Helper.simpleJson(keys, values);
        }

        return "";
    }

    public static String sha256(final String base) {
        try{
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            final byte[] hash = digest.digest(base.getBytes("UTF-8"));
            final StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                final String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1)
                  hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(Exception ex){
           throw new RuntimeException(ex);
        }
    }

    public static String base64_encode(final String str) {
        return Base64.getEncoder().encodeToString(str.getBytes());
    }

    public static String base64_decode(final String str) {
        byte[] decodedBytes = Base64.getDecoder().decode(str);
        return new String(decodedBytes);
    }

    public static String randomString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
              (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();
        return generatedString;
    }
}
