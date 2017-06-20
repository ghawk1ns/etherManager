package com.ghawk1ns.server.response;

public class GenericSuccess extends GenericMessage {

    public GenericSuccess(spark.Response response, String message) {
        super(response,200, message);
    }
}