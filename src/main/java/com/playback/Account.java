package com.playback;

import java.util.*;

/**
 * Account Resourse.
 *
 */

public abstract class Account {

    int streamAllowed;
    HashSet<Resolution> allowedResolutions;

    public abstract HashSet<Resolution> getAllowedResolutions();

    public abstract int getStreamAllowed();

}
