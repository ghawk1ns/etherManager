package com.ghawk1ns.server;


import java.util.Arrays;

public enum UserAuth {

    me("gMe", "kfgK#hgZHgg(B70B^(*n4", "18327581044");

    public final String clientId;
    public final String token;
    public final String sms;

    UserAuth(String clientId, String token, String number) {
        this.clientId = clientId;
        this.token = token;
        this.sms = number;
    }

    public static UserAuth getUser(String clientId, String token) {
        return Arrays.stream(UserAuth.values())
                .filter(u -> u.clientId.equals(clientId))
                .filter(u -> u.token.equals(token))
                .findFirst()
                .orElse(null);
    }
}
