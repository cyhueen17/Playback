package com.playback;

import java.util.*;

/**
 * Standard Account Resourse.
 *
 */

public class StandardAccount extends Account {

    public StandardAccount() {
        streamAllowed = 2;
        allowedResolutions = new HashSet<Resolution>();
        allowedResolutions.add(Resolution.BASIC);
        allowedResolutions.add(Resolution.HD);
    }

    public HashSet<Resolution> getAllowedResolutions() {
        return allowedResolutions;
    }

    public int getStreamAllowed() {
        return streamAllowed;
    }

}
