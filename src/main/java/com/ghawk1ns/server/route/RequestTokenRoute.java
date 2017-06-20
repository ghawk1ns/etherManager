package com.ghawk1ns.server.route;

import com.ghawk1ns.server.Util;
import com.ghawk1ns.server.model.Session;
import com.ghawk1ns.server.response.BaseResponse;
import com.ghawk1ns.server.response.GenericSuccess;
import spark.Request;
import spark.Response;

public class RequestTokenRoute extends BaseRoute {

    public RequestTokenRoute() {
        super(false);
    }

    @Override
    public BaseResponse handle(Request request, Response response, Session session) throws Exception {
        return new GenericSuccess(response, "OATH isn't supported at this time");
    }

    @Override
    public String path() {
        return Util.createPath(API_VER_1, AUTH_PATH, "token");
    }
}
