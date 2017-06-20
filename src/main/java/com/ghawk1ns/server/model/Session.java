package com.ghawk1ns.server.model;

public class Session {

    public final String clientId;
    public final String rigId;

    public Session(String clientId, String rigId) {
        this.clientId = clientId;
        this.rigId = rigId;
    }
}
