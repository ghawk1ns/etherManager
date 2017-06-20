package com.ghawk1ns.server.route;

import com.ghawk1ns.server.UserAuth;
import com.ghawk1ns.server.model.Session;
import com.ghawk1ns.server.response.BaseResponse;
import com.ghawk1ns.server.response.GenericError;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Each route defines an action such that the path ver/path/action routes to a specific Route
 *
 */
public abstract class BaseRoute implements Route {

    // versions
    public static String API_VER_1 = "v1";

    // paths/
    public static String API_PATH = "api";
    public static String AUTH_PATH = "auth";

    // all requests must include param clientId
    public static String CLIENT_ID_KEY = "clientId";
    public static String RIG_ID_KEY = "rigId";

    public static String AUTH_HEADER = "Authorization";

    private final boolean requireAuth;

    protected BaseRoute(boolean requireAuth) {
        this.requireAuth = requireAuth;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        Session session = null;
        if (requireAuth) {
            String clientId = request.queryParams(CLIENT_ID_KEY);
            String auth = request.headers(AUTH_HEADER);
            UserAuth user = UserAuth.getUser(clientId, auth);

            if (user == null) {
                return new GenericError(response, "Missing token, clientId and/or rigId");
            } else {
                session = new Session(user, request.queryParams(RIG_ID_KEY));
            }
        }
        return handle(request, response, session);
    }

    /**
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public abstract BaseResponse handle(Request request, Response response, Session session) throws Exception;

    /**
     *
     * @return the path of the route
     */
    public abstract String path();
}
