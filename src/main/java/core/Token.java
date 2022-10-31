package core;

import java.security.MessageDigest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Random;

import classes.BaseClass;
import classes.User;

public class Token extends BaseClass {
    public String token;

    public String store(int user_id) {
        String token = Token.sha256(Token.randomString() + user_id);
        String query = String.format("insert into user_token (id_user, token) values (%s, '%s')", user_id, token);
        this.token = token;
        this.conn.executeQuery(query);
        return token;
    }

    public String basicAuth(String authorization) {
        String[] parts = Token.base64_decode(authorization).split(":");
        String username = parts[0];
        String password = parts[1];
        String where = String.format("username='%s' and password='%s'", username, password);

        User user = new User();

        user.getUserDatabase(where);
        int id_user = user.getId();

        if (id_user != -1) {
            return this.store(id_user);
        }

        return "";
    }

    public int auth(String token) {
        System.out.println(String.format("select id_user from user_token where token = '%s'", token));
        ResultSet rs = this.conn.select(String.format("select id_user from user_token where token = '%s'", token));
        int id_user = -1;
        try {
            while (rs.next()) {
                id_user = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            id_user = -1;
        }

        return id_user;
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
