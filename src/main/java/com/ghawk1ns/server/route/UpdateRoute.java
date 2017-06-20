package com.ghawk1ns.server.route;

import com.ghawk1ns.server.Util;
import com.ghawk1ns.server.model.RigManager;
import com.ghawk1ns.server.model.Session;
import com.ghawk1ns.server.response.BaseResponse;
import com.ghawk1ns.server.response.GenericSuccess;
import spark.Request;
import spark.Response;

import static com.ghawk1ns.server.io.Mappers.lenient_mapper;

public class UpdateRoute extends BaseRoute {

    public UpdateRoute() {
        super(true);
        // todo logging and DB connection
    }

    public BaseResponse handle(Request request, Response response, Session session) throws Exception {
        RigManager rigManager = lenient_mapper.readValue(request.bodyAsBytes(), RigManager.class);
        _update(rigManager);
        response.status(200);
        return new GenericSuccess(response, "updated");
    }

    @Override
    public String path() {
        return Util.createPath(API_VER_1, API_PATH, "update");
    }

    private void _update(RigManager rigManager) {
        // TODO: save stats
    }
}
