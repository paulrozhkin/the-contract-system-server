package com.itmo.goblinslayersystemserver.security;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;

public class Auth {

    private static Auth instance;
    private static HashMap<String, String> passports;
    private static Base64.Encoder enc;
    private static Base64.Decoder dec;


    private Auth() {
        enc = Base64.getEncoder();
        dec = Base64.getDecoder();
    }

    public static Auth getInstance(){
        if (instance == null) {
            instance = new Auth();

            return instance;
        }

        return instance;
    }

    public String createToken(String login) {
        String token = enc.encodeToString(login.getBytes(StandardCharsets.UTF_8));
        passports.put(token, login);

        return token;
    }

    public String getAuthorizeUser(String token) {
        return passports.get(token);
    }

    public String getUserLoginFromToken(String token) {
        byte[] decodedByteArray = dec.decode(token);

        return new String(decodedByteArray);
    }
}
