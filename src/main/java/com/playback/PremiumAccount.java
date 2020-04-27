package com.playback;

import java.util.*;

/**
 * Premium Account Resourse.
 *
 */

public class PremiumAccount extends Account {

    public PremiumAccount() {
        streamAllowed = 4;
        allowedResolutions = new HashSet<Resolution>();
        allowedResolutions.add(Resolution.BASIC);
        allowedResolutions.add(Resolution.HD);
        allowedResolutions.add(Resolution.ULTRAHD);
    }

    public HashSet<Resolution> getAllowedResolutions() {
        return allowedResolutions;
    }

}