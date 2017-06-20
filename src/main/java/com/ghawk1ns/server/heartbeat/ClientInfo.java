package com.ghawk1ns.server.heartbeat;


import java.util.HashMap;
import java.util.Map;

public class ClientInfo {

    public final String clientId;
    public final String sms;

    // Key -> rig name
    // Val -> Last check-in time
    public final Map<String, Long> rigs;

    private ClientInfo(String clientId, String sms) {
        this.clientId = clientId;
        this.sms = sms;
        rigs = new HashMap<>();
    }

    public static ClientInfo fromClientId(String clientId) {
        // TODO: Load info out of DB
        // rigs and sms can be dynamically added
        return new ClientInfo(clientId, "18327581044");
    }



//    public ClientInfo(String clientId, List<String> rigIds) {
//        this.clientId = clientId;
//        rigs = rigIds.stream().collect((Collectors.toMap(id -> id, id -> System.currentTimeMillis())));
//    }
}