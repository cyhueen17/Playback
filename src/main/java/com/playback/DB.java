package com.playback;

import java.util.*;

/**
 * DB Resourse.
 *
 * This class acting as the database, which has some repopulated dummy data
 * to use while performing validation API operations
 *
 */

public class DB {
    private static DB dbInstance;

    HashMap<Integer, User> userMap;
    HashMap<String, Viewable> viewableMap;
    HashMap<String, Account> accountMap;
    HashMap<String, HashSet<Resolution>> deviceMap;

    public static DB getInstance() {
        if (dbInstance == null) {
            dbInstance = new DB();
        }
        return dbInstance;
    }

    private DB() {
        userMap = new HashMap<Integer, User>();
        viewableMap = new HashMap<String, Viewable>();
        accountMap = new HashMap<String, Account>();
        deviceMap = new HashMap<String, HashSet<Resolution>>();
        initializeDB();
    }

    public void initializeDB() {
        /* Populating accounts */
        accountMap.put("basic", new BasicAccount());
        accountMap.put("standard", new StandardAccount());
        accountMap.put("premium", new PremiumAccount());

        /* Populating devices */
        HashSet<Resolution> mobile = new HashSet<Resolution>();
        mobile.add(Resolution.BASIC);
        deviceMap.put("mobile", mobile);

        HashSet<Resolution> laptop = new HashSet<Resolution>();
        laptop.add(Resolution.BASIC);
        laptop.add(Resolution.HD);
        laptop.add(Resolution.ULTRAHD);
        deviceMap.put("laptop", laptop);

        HashSet<Resolution> tablet = new HashSet<Resolution>();
        tablet.add(Resolution.BASIC);
        tablet.add(Resolution.HD);
        deviceMap.put("tablet", tablet);

        HashSet<Resolution> tv = new HashSet<Resolution>();
        tv.add(Resolution.BASIC);
        tv.add(Resolution.HD);
        tv.add(Resolution.ULTRAHD);
        deviceMap.put("tv", tv);

        /* Populating dummy users */
        User ychen = new User(1, "ychen", "premium", "USA");
        ychen.activateUser();
        userMap.put(1, ychen);
        User emily = new User(2, "emily", "basic", "China");
        emily.activateUser();
        userMap.put(2, emily);
        User jane = new User(3, "jane", "standard", "USA");
        userMap.put(3, jane);
        userMap.put(4, new User(4, "steve", "standard", "India"));
        User mark = new User(5, "mark", "premium", "Spain");
        mark.activateUser();
        userMap.put(5, mark);

        /* Populating dummy viewables */
        viewableMap.put("walkingdead", new Viewable(1, "walkingdead", new String[]{"USA", "China"},
                new Resolution[]{Resolution.BASIC}));
        viewableMap.put("gameofthrones", new Viewable(2, "gameofthrones", new String[]{"USA", "Spain"},
                new Resolution[]{Resolution.BASIC, Resolution.HD, Resolution.ULTRAHD}));
        viewableMap.put("starwars", new Viewable(3, "starwars", new String[]{"USA", "China", "India", "Spain"},
                new Resolution[]{Resolution.HD, Resolution.ULTRAHD}));
        viewableMap.put("residentevil", new Viewable(4, "residentevil", new String[]{"USA", "China"},
                new Resolution[]{Resolution.BASIC, Resolution.HD}));
        viewableMap.put("themandalorian", new Viewable(5, "themandalorian", new String[]{"USA"},
                new Resolution[]{Resolution.ULTRAHD}));
    }

    public Account getAccount(String accountType) {
        return accountMap.get(accountType);
    }

    public User getUser(int id) {
        if (!userMap.containsKey(id))
            return null;
        return userMap.get(id);
    }

    public Viewable getViewable(String title) {
        if (!viewableMap.containsKey(title))
            return null;
        return viewableMap.get(title);
    }

    public HashSet<Resolution> getDeviceResolution(String device) {
        if (!deviceMap.containsKey(device))
            return null;
        return deviceMap.get(device);
    }
}