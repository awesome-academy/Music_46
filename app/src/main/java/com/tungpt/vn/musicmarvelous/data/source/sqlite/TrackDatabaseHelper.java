package com.tungpt.vn.musicmarvelous.data.source.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.tungpt.vn.musicmarvelous.constants.Constants;
import com.tungpt.vn.musicmarvelous.constants.TrackEntity;
import com.tungpt.vn.musicmarvelous.data.model.Track;

import java.util.ArrayList;
import java.util.List;

public class TrackDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "TrackData.db";
    private static final String PRIMARY_KEY = " PRIMARY KEY";
    public static final String SELECT_ALL = "SELECT * FROM ";
    public static final String AND = " AND ";
    public static final String ON = " ON ";
    public static final String WHERE = " WHERE ";
    public static final String LIKE_CONDITION = " LIKE ?";
    public static final String INNER_JOIN = " INNER JOIN ";
    public static final String DOT = ".";
    public static final String EQUAL = " = ";
    public static final String EQUAL_CONDITION = " = ? ";
    private static final String OPEN_PARENTHESIS = " (";
    private static final String CLOSE_PARENTHESIS = ")";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String TEXT_TYPE = " TEXT";
    public static final String COMMA = ",";
    private static TrackDatabaseHelper sTrackDatabaseHelper;

    private static final String SQL_CREATE_TRACK_ENTRIES =
            "CREATE TABLE " + TrackEntry.TABLE_NAME_TRACK + OPEN_PARENTHESIS +
                    TrackEntity.ID + INTEGER_TYPE + " NOT NULL PRIMARY KEY " + COMMA +
                    TrackEntity.TITLE + TEXT_TYPE + COMMA +
                    TrackEntity.ARTWORK_URL + TEXT_TYPE + COMMA +
                    TrackEntity.DESCRIPTION + TEXT_TYPE + COMMA +
                    TrackEntity.DOWNLOADABLE + INTEGER_TYPE + COMMA +
                    TrackEntity.DURATION + INTEGER_TYPE + COMMA +
                    TrackEntity.GENRE + TEXT_TYPE + COMMA +
                    TrackEntity.URI + TEXT_TYPE + COMMA +
                    TrackEntity.ARTIST + TEXT_TYPE + CLOSE_PARENTHESIS;

    private static final String SQL_CREATE_FAVORITE_ENTRIES =
            "CREATE TABLE " + TrackEntry.TABLE_NAME_FAVORITE + OPEN_PARENTHESIS +
                    TrackEntity.ID + INTEGER_TYPE + " NOT NULL " + CLOSE_PARENTHESIS;

    public static TrackDatabaseHelper getInstance(@NonNull Context context) {
        if (sTrackDatabaseHelper == null) {
            sTrackDatabaseHelper = new TrackDatabaseHelper(context);
        }
        return sTrackDatabaseHelper;
    }

    public TrackDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TRACK_ENTRIES);
        db.execSQL(SQL_CREATE_FAVORITE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertTrack(Track track) {
        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TrackEntity.ID, track.getId());
        values.put(TrackEntity.TITLE, track.getTitle());
        values.put(TrackEntity.ARTWORK_URL, track.getArtworkUrl());
        values.put(TrackEntity.DESCRIPTION, track.getDescription());
        if (track.isDownloadable()) {
            values.put(TrackEntity.DOWNLOADABLE, 1);
        } else {
            values.put(TrackEntity.DOWNLOADABLE, 0);
        }
        values.put(TrackEntity.DURATION, track.getDuration());
        values.put(TrackEntity.GENRE, track.getGenre());
        values.put(TrackEntity.URI, track.getUri());
        values.put(TrackEntity.ARTIST, track.getPublisherAlbumTitle());

        database.insert(TrackEntry.TABLE_NAME_TRACK, null, values);
        database.close();
    }

    public List<Track> getAllTrack() {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(TrackEntry.TABLE_NAME_TRACK,
                null,
                null,
                null,
                null,
                null,
                null);
        List<Track> tracks = new ArrayList<>();
        while (cursor.moveToNext()) {
            tracks.add(parseTrackFromRow(cursor));
        }
        cursor.close();
        database.close();
        return tracks;
    }

    public Track getTrackById(int trackId) {
        SQLiteDatabase database = getReadableDatabase();
        String selection = String.format(Constants.DB_QUERY_EQUAL_SELECTION, TrackEntity.ID);
        String[] selectionArgs = new String[]{String.valueOf(trackId)};
        Cursor cursor = database.query(TrackEntry.TABLE_NAME_TRACK,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null);
        Track track = null;
        if (cursor.moveToNext()) {
            track = parseTrackFromRow(cursor);
        }
        cursor.close();
        database.close();
        return track;
    }

    public void deleteTrack(Track track) {
        SQLiteDatabase database = getWritableDatabase();
        String selection = String.format(Constants.DB_QUERY_EQUAL_SELECTION, TrackEntity.ID);
        String[] selectionArgs = new String[]{String.valueOf(track.getId())};
        database.delete(TrackEntry.TABLE_NAME_TRACK, selection, selectionArgs);
        database.close();
    }

    public void addTrackToFavorite(Track track) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TrackEntity.ID, track.getId());
        database.insert(TrackEntry.TABLE_NAME_FAVORITE, null, values);
        database.close();
    }

    public boolean isTrackInFavorite(Track track) {
        SQLiteDatabase database = getReadableDatabase();
        String selection = String.format(Constants.DB_QUERY_EQUAL_SELECTION, TrackEntity.ID);
        String[] selectionArgs = new String[]{String.valueOf(track.getId())};
        Cursor cursor = database.query(TrackEntry.TABLE_NAME_FAVORITE, null,
                selection, selectionArgs, null, null, null);
        boolean check = (cursor.getCount() > 0);
        cursor.close();
        database.close();
        return check;
    }

    public List<Track> getAllFavoriteTrack() {
        SQLiteDatabase database = getReadableDatabase();
        String query = new StringBuilder()
                .append(SELECT_ALL).append(TrackEntry.TABLE_NAME_TRACK)
                .append(INNER_JOIN).append(TrackEntry.TABLE_NAME_FAVORITE)
                .append(ON)
                .append(TrackEntry.TABLE_NAME_TRACK).append(DOT).append(TrackEntity.ID)
                .append(EQUAL)
                .append(TrackEntry.TABLE_NAME_FAVORITE).append(DOT).append(TrackEntity.ID)
                .toString();
        Cursor cursor = database.rawQuery(query, null);
        List<Track> tracks = new ArrayList<>();
        while (cursor.moveToNext()) {
            tracks.add(parseTrackFromRow(cursor));
        }
        cursor.close();
        database.close();
        return tracks;
    }

    public int getFavoriteTracksCount() {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(TrackEntry.TABLE_NAME_FAVORITE, null, null, null,
                null, null, null);
        int count = cursor.getCount();
        cursor.close();
        database.close();
        return count;
    }

    public void removeTrackFromFavorite(Track track) {
        SQLiteDatabase database = getWritableDatabase();
        String selection = String.format(Constants.DB_QUERY_EQUAL_SELECTION, TrackEntity.ID);
        String[] selectionArgs = {String.valueOf(track.getId())};
        database.delete(TrackEntry.TABLE_NAME_FAVORITE, selection, selectionArgs);
        database.close();
    }

    private Track parseTrackFromRow(Cursor cursor) {
        Track track = new Track.TrackBuilder().build();
        try {
            track.setId(cursor.getInt(cursor.getColumnIndexOrThrow(TrackEntity.ID)));
            track.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(TrackEntity.TITLE)));
            track.setArtworkUrl(cursor.getString(cursor.getColumnIndexOrThrow(TrackEntity.ARTWORK_URL)));
            track.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(TrackEntity.DESCRIPTION)));
            track.setDuration(cursor.getInt(cursor.getColumnIndexOrThrow(TrackEntity.DURATION)));
            track.setGenre(cursor.getString(cursor.getColumnIndexOrThrow(TrackEntity.GENRE)));
            track.setUri(cursor.getString(cursor.getColumnIndexOrThrow(TrackEntity.URI)));
            track.setPublisherAlbumTitle(cursor.getString(cursor.getColumnIndexOrThrow(TrackEntity.ARTIST)));

            int downloadable = cursor.getInt(cursor.getColumnIndexOrThrow(TrackEntity.DOWNLOADABLE));
            if (downloadable == 1) {
                track.setDownloadable(true);
            } else {
                track.setDownloadable(false);
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            track = null;
        }
        return track;
    }

    public static final class TrackEntry {
        static final String TABLE_NAME_TRACK = "tracks";
        static final String TABLE_NAME_FAVORITE = "favorites";
    }
}
