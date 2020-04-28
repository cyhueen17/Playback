package com.playback;

import java.util.*;

/**
 * Validation.
 *
 */

public class Validation {
    public Validation() {}

    public boolean checkValidation(int userId, String device, String viewableTitle, DB db, String ruleToExclude, Log log) {
        Date date = new Date();
        User user = db.getUser(userId);
        HashSet<Resolution> deviceResolution = db.getDeviceResolution(device);
        Viewable viewable = db.getViewable(viewableTitle.toLowerCase());

        boolean isValidUserStatus = true;
        if (ruleToExclude == null || ruleToExclude.equals("") || !ruleToExclude.equals("userstatus")) {
            isValidUserStatus = userValidation(user);
            if (!isValidUserStatus) {
                log.addLog(date.getTime()+ " User: " + user.getUserName() + " failed playback becuase of inactive user.");
                return isValidUserStatus;
            }
        }

        boolean isValidResolution = true;
        if (ruleToExclude == null || ruleToExclude.equals("") || !ruleToExclude.equals("resolution")) {
            isValidResolution = resolutionValidation(user, deviceResolution, viewable, db);
            if (!isValidResolution) {
                log.addLog(date.getTime()+ " User: " + user.getUserName() + " failed playback becuase of invalid resolution.");
                return isValidResolution;
            }
        }

        boolean isValidRegion = true;
        if (ruleToExclude == null || ruleToExclude.equals("") || !ruleToExclude.equals("allowedCountry")) {
            isValidRegion = regionValidation(user, viewable);
            if (!isValidRegion) {
                log.addLog(date.getTime()+ " User: " + user.getUserName() + " failed playback becuase of invalid region.");
                return isValidRegion;
            }
        }

        boolean isValidNumStream = true;
        if (ruleToExclude == null || ruleToExclude.equals("") || !ruleToExclude.equals("allowedStream")) {
            isValidNumStream = numStreamValidation(user, db);
            if (!isValidNumStream) {
                log.addLog(date.getTime()+ " User: " + user.getUserName() + " failed playback becuase of max num of stream reached.");
                return isValidNumStream;
            }
        }

        log.addLog(date.getTime()+ " User: " + user.getUserName() + " successful playback allowed for viewable: " + viewable.getTitle() + ".");

        return true;
    }

    public boolean userValidation(User user) {
        return user.isActive();
    }

    public boolean resolutionValidation(User user, HashSet<Resolution> deviceResolution, Viewable viewable, DB db) {
        Account account = db.getAccount(user.getAccountType());
        HashSet<Resolution> accountResolution = account.getAllowedResolutions();

        HashSet<Resolution> viewableResolution = viewable.getResolutions();

        boolean accountValid = checkResolution(viewableResolution, accountResolution);
        boolean deviceValid = checkResolution(viewableResolution, deviceResolution);

        return accountValid && deviceValid;
    }

    public boolean checkResolution(HashSet<Resolution> viewableResolution, HashSet<Resolution> allowedResolution) {
        for (Resolution resolution: viewableResolution) {
            if (allowedResolution.contains(resolution))
                return true;
        }
        return false;
    }

    public boolean numStreamValidation(User user, DB db) {
        String accountType = user.getAccountType();
        Account account = db.getAccount(accountType);

        if (user.getStreamInUse() < account.getStreamAllowed()) {
            user.addStream();
            return true;
        }
        return false;
    }

    public boolean regionValidation(User user, Viewable viewable) {
        HashSet<String> allowedCountries = viewable.getAllowedCountries();
        return allowedCountries.contains(user.getHomeCountry());
    }
}
