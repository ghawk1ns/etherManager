package com.ghawk1ns.server.response;

public class GenericError extends GenericMessage {

    public GenericError(spark.Response response, String message) {
        super(response, 404, message);
    }
}