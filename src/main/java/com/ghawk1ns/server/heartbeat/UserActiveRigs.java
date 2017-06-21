package com.ghawk1ns.server.heartbeat;


import com.ghawk1ns.Logger;
import com.ghawk1ns.server.TwilioManager;
import com.ghawk1ns.server.UserAuth;
import com.ghawk1ns.server.model.RigStats;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class UserActiveRigs {

    private static final org.slf4j.Logger logger = Logger.get(UserActiveRigs.class);
    public final UserAuth user;
    // Key -> rig name
    // Val -> Future alarm
    public final Map<String, ScheduledFuture<?>> rigs;

    // TODO: Use CircularFifoQueue<> and keep last n stats
    public RigStats lastStats;

    private UserActiveRigs(UserAuth user) {
        this.user = user;
        rigs = new HashMap<>();
    }

    public long timeSinceLastGPUCountNotification;
    public long gpuCountThrottleLength = TimeUnit.HOURS.toMillis(1);

    public long timeSinceLastGPUTmpNotification;
    public long gpuTmpThrottleLength = TimeUnit.MINUTES.toMillis(10);

    public long lastHashNotification;
    public long hashThrottleLength = TimeUnit.MINUTES.toMillis(10);

    public static UserActiveRigs fromUser(UserAuth user) {
        // TODO: Load info out of DB
        // rigs and sms can be dynamically added
        return new UserActiveRigs(user);
    }

    synchronized void updateStats(RigStats currentStats, String rigId) {
        if (this.lastStats != null && currentStats != null) {
            // notifications
            gpuCount(currentStats, rigId);
            gpuTmps(currentStats, rigId);
            hashRate(currentStats, rigId);
            // Log for now
            if (lastStats.autorebooted < currentStats.autorebooted) {
                logger.warn("{}-{} autorebooted:{}", user.clientId, rigId, currentStats.autorebooted);
            }
            logger.info("{}-{} uptime:{} minerTime:{}", user.clientId, rigId, currentStats.rigUptime, currentStats.minerUptime);
        }
        this.lastStats = currentStats;
    }

    private void hashRate(RigStats currentStats, String rigId) {
        double lastHash = lastStats.hash;
        double currentHash = currentStats.hash;
        if (lastHash > currentHash) {
            logger.info("hash decrease for {}-{} last:{} now:{}", user.clientId, rigId, lastHash, currentHash);
            final long now = System.currentTimeMillis();
            if (now - lastHashNotification > hashThrottleLength) {
                TwilioManager.send(user.sms, String.format("hash decrease for %s-%s last:%s now:%s",
                        user.clientId, rigId, lastHash, currentHash));
                lastHashNotification = now;
            }
        }
    }

    private void gpuTmps(RigStats currentStats, String rigId) {
        StringBuilder msg = new StringBuilder("GPU Temp: ");
        boolean found = false;
        for (int i = 0; i < currentStats.temps.size(); i++) {
            double tmp = currentStats.temps.get(i);
            if (tmp > 75) {
                found = true;
                msg.append(String.format("gpu%s:%s ", i, tmp));
            }
        }
        if (found) {
            final long now = System.currentTimeMillis();;
            if (now - timeSinceLastGPUTmpNotification > gpuTmpThrottleLength) {
                msg.append(currentStats.status);
                TwilioManager.send(user.sms, msg.toString());
                timeSinceLastGPUTmpNotification = now;
            }
        }
    }

    private void gpuCount(RigStats currentStats, String rigId) {
        // Check GPU count
        int lastGPUCount = lastStats.gpus;
        int currentGPUCount = currentStats.gpus;
        if (lastGPUCount > currentGPUCount) {
            logger.info("gpu count down for {}-{}: last {} current {} status {}",
                    user.clientId, rigId, lastGPUCount, currentGPUCount, currentStats.status);
            final long now = System.currentTimeMillis();
            if (now - timeSinceLastGPUCountNotification > gpuCountThrottleLength) {
                TwilioManager.send(user.sms, String.format("Lost GPUs last:%s now:%s status:%s",
                        lastGPUCount, currentGPUCount, currentStats.status));
                timeSinceLastGPUCountNotification = now;
            }
        }
    }
}