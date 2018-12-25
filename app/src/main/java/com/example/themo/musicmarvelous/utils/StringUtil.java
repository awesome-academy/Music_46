package com.example.themo.musicmarvelous.utils;

import com.example.themo.musicmarvelous.BuildConfig;
import com.example.themo.musicmarvelous.constants.Constants;

public class StringUtil {

    public static String formatTrackURL(String genre, int limit, int offSet) {
        return String.format(Constants.TRACK_QUERY_FORMAT, Constants.BASE_URL,
                Constants.GENRE, genre, Constants.CLIENT_ID,
                BuildConfig.API_KEY, Constants.LIMIT, limit, Constants.OFFSET, offSet);
    }
}
