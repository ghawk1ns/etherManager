package com.ghawk1ns.server.route;


import com.ghawk1ns.server.Util;
import com.ghawk1ns.server.heartbeat.HeartBeatManager;
import com.ghawk1ns.server.model.Session;
import com.ghawk1ns.server.response.BaseResponse;
import com.ghawk1ns.server.response.HeartBeatResponse;
import spark.Request;
import spark.Response;

public class HeartBeatRoute extends BaseRoute {

    private final HeartBeatManager manager;

    // True if clientId-rigId should be removed from heartbeat monitoring
    public static String PARAM_STOP = "stop";

    public HeartBeatRoute(HeartBeatManager manager) {
        super(true);
        this.manager = manager;
    }

    @Override
    public BaseResponse handle(Request request, Response response, Session session) throws Exception {
        String remove = request.queryParamOrDefault(PARAM_STOP, "false");
        if ("true".equals(remove)) {
            manager.stop(session.user, session.rigId);
        } else {
            manager.update(session.user, session.rigId);
        }
        return new HeartBeatResponse(response, 200, false);
    }

    @Override
    public String path() {
        return Util.createPath(API_VER_1, API_PATH, "heartbeat");
    }
}
