package com.playback;

import java.util.*;
import javax.annotation.PostConstruct;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.BeanParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import java.awt.*;

import jdk.nashorn.internal.objects.annotations.Getter;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("playbackresource")
public class PlaybackResource {
    Validation validation;
    DB db;
    Log log;
    Date date = new Date();

    public PlaybackResource() {
        validation = new Validation();
        db = DB.getInstance();
        log = Log.getInstance();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String playback(ValidationParamReq validationParamReq) {

        int userId = validationParamReq.getUserId();
        if (validationParamReq.getUserId() == 0) {
            // throw excpetion
            return "Exception user is null!";
        }

        String deviceType = validationParamReq.getDeviceType();
        if (deviceType == null) {
            // throw excpetion
            return "Exception device type is null!";
        }

        String viewableId = validationParamReq.getViewableId();
        if (viewableId == null) {
            // throw excpetion
            return "Exception viewableId is null!";
        }

        String ruleToExclude = validationParamReq.getRuleToExclude();

        boolean disableValidation = validationParamReq.getDisableValidation();

        boolean isValid = false;
        if (disableValidation) {
            log.addLog(date.getTime() + " viewable is public, no validation is performed.");
            return "Playback allowed!";
        }

        isValid = validation.checkValidation(userId, deviceType, viewableId, db, ruleToExclude, log);

        if (isValid) {
            return "Playback allowed!";
        }
        return "Playback not allowed!";
    }

    @GET
    @Path("/log")
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        StringBuilder str = new StringBuilder();
        for (String log: log.getAllLogs()) {
            str.append(log + "\n");
        }
        return str.toString();
    }
}
