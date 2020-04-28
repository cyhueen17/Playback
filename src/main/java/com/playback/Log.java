package com.playback;

import java.util.*;

/**
 * Log.
 *
 * This class acting as the log tracker, which keeps track of all the validation even
 * which happened.
 *
 */

public class Log {
    private static Log logInstance;

    HashSet<String> log;

    public static Log getInstance() {
        if (logInstance == null) {
            logInstance = new Log();
        }
        return logInstance;
    }

    private Log() {
        log = new HashSet<String>();
    }

    public void addLog(String logMsg) {
        log.add(logMsg);
    }

    public HashSet<String> getAllLogs() {
        return log;
    }
}
