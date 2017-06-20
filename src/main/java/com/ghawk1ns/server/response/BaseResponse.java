package com.ghawk1ns.server.response;


import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ghawk1ns.server.io.Mappers;

public abstract class BaseResponse {

    public final int code;

    public BaseResponse(spark.Response response, int code) {
        this.code = code;
        if (response != null) {
            response.status(code);
            response.type("application/json");
        }
    }

    /**
     *
     * @return An associated response specific to a response implementation
     */
    abstract ObjectNode responseData();

    @Override
    public String toString() {
        return Mappers.lenient_mapper.createObjectNode()
                .put("status", code)
                .setAll(responseData())
                .toString();
    }
}
