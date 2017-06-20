package com.ghawk1ns.server.response;


import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ghawk1ns.server.io.Mappers;

public class HeartBeatResponse extends BaseResponse {

    private boolean restart;

    public HeartBeatResponse(spark.Response response, int code, boolean restart) {
        super(response, code);
        this.restart = restart;
    }

    @Override
    ObjectNode responseData() {
        return Mappers.lenient_mapper.createObjectNode()
                .put("restart", restart);
    }
}
