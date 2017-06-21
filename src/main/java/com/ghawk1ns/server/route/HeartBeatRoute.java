package com.ghawk1ns.server.route;


import com.ghawk1ns.Logger;
import com.ghawk1ns.server.Util;
import com.ghawk1ns.server.heartbeat.HeartBeatManager;
import com.ghawk1ns.server.model.RigStats;
import com.ghawk1ns.server.model.Session;
import com.ghawk1ns.server.response.BaseResponse;
import com.ghawk1ns.server.response.HeartBeatResponse;
import spark.Request;
import spark.Response;

import static com.ghawk1ns.server.io.Mappers.lenient_mapper;

public class HeartBeatRoute extends BaseRoute {

    private static final org.slf4j.Logger logger = Logger.get(HeartBeatRoute.class);
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
            try {
                session.stats = lenient_mapper.readValue(request.bodyAsBytes(), RigStats.class);
            } catch (Exception e) {
                logger.error(String.format("Invalid stats for %s - ", session.user.clientId), e);
            }
            manager.update(session, session.rigId);
        }
        return new HeartBeatResponse(response, 200, false);
    }

    @Override
    public String path() {
        return Util.createPath(API_VER_1, API_PATH, "heartbeat");
    }
}
