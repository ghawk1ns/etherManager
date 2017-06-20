package com.ghawk1ns.server.response;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ghawk1ns.server.io.Mappers;

public class GenericMessage extends BaseResponse {

    private final String message;

    public GenericMessage(spark.Response response, int code, String message) {
        super(response, code);
        this.message = message;
    }

    @Override
    ObjectNode responseData() {
        return Mappers.lenient_mapper.createObjectNode()
            .put("message", message);
    }
}
