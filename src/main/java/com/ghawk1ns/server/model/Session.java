package com.ghawk1ns.server.model;

import com.ghawk1ns.server.UserAuth;

public class Session {

    public final UserAuth user;
    public final String rigId;
    public RigStats stats;

    public Session(UserAuth user, String rigId) {
        this.user = user;
        this.rigId = rigId;
    }
}
