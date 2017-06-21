package com.ghawk1ns.server.heartbeat;

import com.ghawk1ns.Logger;
import com.ghawk1ns.server.TwilioManager;
import com.ghawk1ns.server.UserAuth;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class HeartBeatManager {

    private static final org.slf4j.Logger logger = Logger.get(HeartBeatManager.class);
    private final Map<String, UserActiveRigs> activeRigs;
    private final ScheduledExecutorService scheduledExecutorService;

    public HeartBeatManager() {
        this.activeRigs = new HashMap<>();
        scheduledExecutorService = Executors.newScheduledThreadPool(10);
    }

    private void alert(UserAuth user, String rigId) {
        String msg = String.format("Heartbeat stopped for %s", key(user.clientId, rigId));
        logger.info(msg);
        TwilioManager.send(user.sms, msg);
    }

    public synchronized void update(UserAuth user, String rigId) {
        // Try to initialize a new client
        activeRigs.computeIfAbsent(user.clientId, s -> UserActiveRigs.fromUser(user));
        UserActiveRigs UserRigs = activeRigs.get(user.clientId);
        UserRigs.rigs.computeIfPresent(rigId, (s, alarm) -> {
            boolean cancel = alarm.cancel(true);
            logger.info("Heartbeat received for {}-{}: success cancel existing alarm: {}", user.clientId, rigId, cancel);
            return null;
        });
        UserRigs.rigs.computeIfAbsent(rigId, k -> createAlarm(user, rigId));
    }

    public synchronized void stop(UserAuth user, String rigId) {
        if (activeRigs.containsKey(user.clientId)) {
            activeRigs.get(user.clientId)
                    .rigs.computeIfPresent(rigId, (s, alarm) -> {
                boolean cancel = alarm.cancel(true);
                logger.info("removing Heartbeat for {}-{} success:", user.clientId, rigId, cancel);
                return null;
            });
        }  else {
            logger.info("Can't stop, No current heartbeat for {}-{}", user.clientId, rigId);
        }
    }

    private ScheduledFuture<?> createAlarm(UserAuth user, String rigId) {
        logger.info("New Heartbeat created for {}-{}", user.clientId, rigId);
        // TODO: Think about making duration configurable
        return scheduledExecutorService.schedule(() -> alert(user, rigId), 5, TimeUnit.MINUTES);
    }

    private String key(String clientId, String rigId) {
        return String.format("%s-%s", clientId, rigId);
    }
}
