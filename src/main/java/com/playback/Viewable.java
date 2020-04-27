package com.playback;

import java.util.*;

/**
 * Viewable Resourse.
 *
 */

public class Viewable {

    int id;
    String title;
    HashSet<String> allowedCountries;
    HashSet<Resolution> resolutions;

    public Viewable(int id, String title, String[] countries, Resolution[] res) {
        this.id = id;
        this.title = title;

        allowedCountries = new HashSet<String>();
        for (int i = 0; i < countries.length; i++) {
            allowedCountries.add(countries[i]);
        }

        resolutions = new HashSet<Resolution>();
        for (int i = 0; i < res.length; i++) {
            this.resolutions.add(res[i]);
        }
    }

}