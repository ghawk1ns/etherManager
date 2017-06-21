package com.ghawk1ns.server;

import com.ghawk1ns.Logger;
import com.ghawk1ns.server.heartbeat.HeartBeatManager;
import com.ghawk1ns.server.route.BaseRoute;
import com.ghawk1ns.server.route.HeartBeatRoute;
import com.ghawk1ns.server.route.UpdateRoute;
import spark.Spark;


public class Server {

    private org.slf4j.Logger logger = Logger.get(Server.class);
    private HeartBeatManager heartBeatManager;

    public Server() {
        final int port = 8130;
        Spark.port(port);
        logger.info("Starting on port {}", port);
        // TODO: HTTPS
        // String keyStoreLocation = "deploy/keystore.jks";
        // String keyStorePassword = "password";
        // secure(keyStoreLocation, keyStorePassword, null, null);

        TwilioManager.init();
        heartBeatManager = new HeartBeatManager();

    }

    public void start() {
        Spark.init();
        post(new HeartBeatRoute(heartBeatManager));
        // TODO logging and auth
        post(new UpdateRoute());
    }

    private static void post(BaseRoute handler) {
        Spark.post(handler.path(), handler);
    }

    private static void get(BaseRoute handler) {
        Spark.get(handler.path(), handler);
    }
}