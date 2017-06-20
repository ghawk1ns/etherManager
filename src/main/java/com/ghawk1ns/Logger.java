package com.ghawk1ns;

import org.slf4j.LoggerFactory;

public class Logger {

    public static org.slf4j.Logger get(Class<?> clazz) {
        org.slf4j.Logger logger = LoggerFactory.getLogger(clazz);
        // Just in case we need to initialize each logger
        return logger;


    }
}
