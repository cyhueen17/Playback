package com.playback;

import java.util.*;

/**
 * Basic Account Resourse.
 *
 */

public class BasicAccount extends Account {

    public BasicAccount() {
        streamAllowed = 1;
        allowedResolutions = new HashSet<Resolution>();
        allowedResolutions.add(Resolution.BASIC);
    }

    public HashSet<Resolution> getAllowedResolutions() {
        return allowedResolutions;
    }

    public int getStreamAllowed() {
        return streamAllowed;
    }

}
