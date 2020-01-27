package com.josh.earthquake.Util;

import java.util.Random;

public class Constants {
    public static final String URL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.geojson";
    public static final int LIMIT = 40;

    //random generator
    public static int randomInt(int max, int min) {
        return new Random().nextInt(max - min) + min;

    }
}
