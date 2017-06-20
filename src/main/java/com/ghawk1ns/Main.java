package com.ghawk1ns;

import com.ghawk1ns.server.Server;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        // TODO: Switch to a logger implementation that switches file every 24 hours @midnight
        Path logPath = Paths.get("log/server.log");
        System.setProperty(org.slf4j.impl.SimpleLogger.LOG_FILE_KEY, logPath.toString());
        Server server = new Server();
        server.start();
    }
}
