package com.ghawk1ns.server.heartbeat;

import com.ghawk1ns.Logger;
import com.ghawk1ns.server.TwilioManager;
import com.ghawk1ns.server.Util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class HeartBeatManager {

    private static final org.slf4j.Logger logger = Logger.get(HeartBeatManager.class);
    private final Map<String, ClientInfo> clients;
    private final Map<String, ScheduledFuture<?>> alarms;
    private final ScheduledExecutorService scheduledExecutorService;

    public HeartBeatManager() {
        this.clients = new HashMap<>();
        this.alarms= new HashMap<>();
        scheduledExecutorService = Executors.newScheduledThreadPool(10);
    }

    private void alert(String clientId, String rigId) {
        String msg = String.format("Heartbeat stopped for %s", key(clientId, rigId));
        logger.info(msg);
        if (clientId.contains(clientId)) {
            ClientInfo clientInfo = clients.get(clientId);
            if (!Util.nullOrEmpty(clientInfo.sms)) {
                TwilioManager.send(clientInfo.sms, msg);
            }
        }
    }

    public synchronized void update(String clientId, String rigId) {
        // Try to initialize a new client
        clients.computeIfAbsent(clientId, s -> ClientInfo.fromClientId(clientId));
        ClientInfo clientInfo = clients.get(clientId);
        clientInfo.rigs.put(rigId, System.currentTimeMillis());
        // cancel the current alarm and create a new one
        String key = key(clientId, rigId);
        alarms.computeIfPresent(key, (s, alarm) -> {
            logger.info("Heartbeat received for {}", key);
            alarm.cancel(true);
            return null;
        });
        alarms.computeIfAbsent(key, k -> createAlarm(clientInfo, rigId));
    }

    public synchronized void remove(String clientId, String rigId) {
        // Try to initialize a new client
        String key = key(clientId, rigId);
        if (clients.containsKey(clientId)) {
            // cancel the current alarm and create a new one
            alarms.computeIfPresent(key, (s, alarm) -> {
                logger.info("removing Heartbeat for {}", key);
                alarm.cancel(true);
                return null;
            });
        }  else {
            logger.info("Can't remove, No current heartbeat for {}", key);
        }
    }

    private ScheduledFuture<?> createAlarm(ClientInfo clientinfo, String rigId) {
        logger.info("New Heartbeat created for {}-{}", clientinfo.clientId, rigId);
        // TODO: Think about making duration configurable
        return scheduledExecutorService.schedule(() -> alert(clientinfo.clientId, rigId), 10, TimeUnit.MINUTES);
    }

    private String key(String clientId, String rigId) {
        return String.format("%s-%s", clientId, rigId);
    }
}
