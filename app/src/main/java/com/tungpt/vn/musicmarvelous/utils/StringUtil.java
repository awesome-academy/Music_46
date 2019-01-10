package com.tungpt.vn.musicmarvelous.utils;

import com.tungpt.vn.musicmarvelous.BuildConfig;
import com.tungpt.vn.musicmarvelous.constants.Constants;

import java.util.concurrent.TimeUnit;

public class StringUtil {

    public static String formatTrackURL(String genre, int limit, int offSet) {
        return String.format(Constants.TRACK_QUERY_FORMAT, Constants.BASE_URL,
                Constants.GENRE, genre, Constants.CLIENT_ID,
                BuildConfig.API_KEY, Constants.LIMIT, limit, Constants.OFFSET, offSet);
    }

    public static String formatSearchTrackURL(String name, int offSet) {
        return String.format(Constants.SEARCH_QUERY_FORMAT, Constants.BASE_URL,
                Constants.SEARCH_TRACK, name, Constants.OFFSET, offSet,
                Constants.CLIENT_ID, BuildConfig.API_KEY);
    }

    public static String formatTrackStreamURL(String uri) {
        return String.format("%s/%s?%s=%s", uri, Constants.STREAM,
                Constants.CLIENT_ID, BuildConfig.API_KEY);
    }

    public static String convertMilisecondToTimer(long milliseconds) {
        return String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(milliseconds) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliseconds)),
                TimeUnit.MILLISECONDS.toSeconds(milliseconds) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliseconds)));
    }

    public static String formatStringNumberOfItems(int num, String itemName) {
        return String.format("%d %s", num, itemName);
    }
}
