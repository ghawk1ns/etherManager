package com.ghawk1ns.server.heartbeat;


import com.ghawk1ns.server.UserAuth;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

public class UserActiveRigs {

    public final UserAuth user;
    // Key -> rig name
    // Val -> Future alarm
    public final Map<String, ScheduledFuture<?>> rigs;

    private UserActiveRigs(UserAuth user) {
        this.user = user;
        rigs = new HashMap<>();
    }

    public static UserActiveRigs fromUser(UserAuth user) {
        // TODO: Load info out of DB
        // rigs and sms can be dynamically added
        return new UserActiveRigs(user);
    }

}