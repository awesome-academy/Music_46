package com.tungpt.vn.musicmarvelous.data.source.local;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.tungpt.vn.musicmarvelous.R;
import com.tungpt.vn.musicmarvelous.constants.Constants;
import com.tungpt.vn.musicmarvelous.data.model.Track;
import com.tungpt.vn.musicmarvelous.data.source.TrackDataSource;
import com.tungpt.vn.musicmarvelous.data.source.sqlite.TrackDatabaseHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TrackLocalDataSource implements TrackDataSource.LocalDataSource {

    private static final String MESSAGE = "FAILURE";
    private static TrackLocalDataSource sInstance;
    private TrackDatabaseHelper mDatabaseHelper;
    private Context mContext;

    public TrackLocalDataSource(Context context) {
        mContext = context;
        mDatabaseHelper = TrackDatabaseHelper.getInstance(context);
    }

    public static TrackLocalDataSource getInstance() {
        return sInstance;
    }

    public static void init(Context context) {
        if (sInstance == null) {
            sInstance = new TrackLocalDataSource(context);
        }
    }

    @Override
    public void getOfflineTracksInFolder(String folderName,
                                         TrackDataSource.OnFetchDataListener<Track> listener) {
        List<Track> tracks = new ArrayList<>();
        ContentResolver resolver = mContext.getContentResolver();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection = new String[]{
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DURATION
        };
        String[] selectionArgs = new String[]{
                new StringBuilder()
                        .append(Constants.PERCENT).append(folderName).append(Constants.PERCENT)
                        .toString()
        };
        String sortByTitleAscending = String.format(Constants.DB_SORT_COLUMN_ASC,
                MediaStore.Audio.Media.TITLE);
        Cursor cursor = resolver.query(uri,
                projection,
                null,
                selectionArgs,
                sortByTitleAscending);

        if (cursor == null) {
            listener.onFetchDataFailure(new Exception(MESSAGE));
            return;
        }
        while (cursor.moveToNext()) {
            Track track = parseTrackFromRow(cursor);
            tracks.add(track);
        }
        cursor.close();
        listener.onFetchDataSuccess(tracks);
    }

    @Override
    public void getOfflineTracks(TrackDataSource.OnFetchDataListener<Track> listener) {
        List<Track> tracks = new ArrayList<>();
        ContentResolver resolver = mContext.getContentResolver();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection = new String[]{
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DURATION
        };
        String sortByTitleAscending = String.format(Constants.DB_SORT_COLUMN_ASC,
                MediaStore.Audio.Media.TITLE);
        Cursor cursor = resolver.query(uri, projection, null, null,
                sortByTitleAscending);

        if (cursor == null) {
            listener.onFetchDataFailure(new Exception(MESSAGE));
            return;
        }
        while (cursor.moveToNext()) {
            Track track = parseTrackFromRow(cursor);
            tracks.add(track);
        }
        cursor.close();
        listener.onFetchDataSuccess(tracks);
    }

    @Override
    public boolean deleteOfflineTrack(Track track) {
        File file = new File(track.getUri());
        return file.delete();
    }

    @Override
    public boolean deleteTrack(Track track) {
        mDatabaseHelper.deleteTrack(track);
        return true;
    }

    @Override
    public List<Track> getTracksFavorite() {
        return mDatabaseHelper.getAllFavoriteTrack();
    }

    @Override
    public void addTrackToFavorite(Track track, TrackDataSource.OnQueryDatabaseListener listener) {
        if (isTrackInFavorite(track)) return;
        if (mDatabaseHelper.getTrackById(track.getId()) == null) {
            mDatabaseHelper.insertTrack(track);
        }
        mDatabaseHelper.addTrackToFavorite(track);
        listener.onQuerySuccess(mContext.getString(R.string.msg_added));
    }

    @Override
    public void deleteTrackFavorite(Track track, TrackDataSource.OnQueryDatabaseListener listener) {
        mDatabaseHelper.removeTrackFromFavorite(track);
        listener.onQuerySuccess(mContext.getString(R.string.msg_deleted));
    }

    @Override
    public boolean isTrackInFavorite(Track track) {
        return mDatabaseHelper.isTrackInFavorite(track);
    }

    private Track parseTrackFromRow(Cursor cursor) {
        Track track = new Track.TrackBuilder().build();
        track.setId(cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media._ID)));
        track.setTitle(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)));
        track.setPublisherAlbumTitle(cursor.getString(
                cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)));
        track.setUri(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA)));
        track.setDuration(cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION)));
        return track;
    }
}
