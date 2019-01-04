package com.example.themo.musicmarvelous.ui.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.themo.musicmarvelous.R;
import com.example.themo.musicmarvelous.data.model.Track;

import java.util.List;

public class BaseTrackAdapter<V extends BaseTrackAdapter.BaseViewHolder>
        extends RecyclerView.Adapter<V> {

    protected Context mContext;
    protected TrackListener mTrackListener;
    protected List<Track> mTracks;
    protected LayoutInflater mInflater;

    public BaseTrackAdapter(Context context, TrackListener trackListener) {
        mContext = context;
        mTrackListener = trackListener;
        mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public V onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull V holder, int i) {
        Track track = mTracks.get(i);
        holder.bindData(track);
    }

    @Override
    public int getItemCount() {
        return mTracks != null ? mTracks.size() : 0;
    }

    public static abstract class BaseViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        protected Context mContext;
        protected TrackListener mTrackListener;
        protected List<Track> mTracks;
        protected ImageView mImageViewTrack;
        protected ImageView mImageViewOptions;
        protected TextView mTextViewTitle;
        protected TextView mTextViewArtist;

        public BaseViewHolder(View itemView, List<Track> tracks, TrackListener trackListener) {
            super(itemView);
            this.mContext = itemView.getContext();
            this.mTrackListener = trackListener;
            this.mTracks = tracks;
            mImageViewTrack = itemView.findViewById(R.id.image_track);
            mImageViewOptions = itemView.findViewById(R.id.image_options);
            mTextViewTitle = itemView.findViewById(R.id.text_title);
            mTextViewArtist = itemView.findViewById(R.id.text_artist);
            setupUI(itemView);
        }

        protected void bindData(Track track) {
            if (mTracks == null) return;
            Glide.with(mContext).load(track.getArtworkUrl()).into(mImageViewTrack);
            mTextViewTitle.setText(track.getTitle());
            mTextViewArtist.setText(track.getPublisherAlbumTitle());
        }

        protected void handlePlayTracks() {
            if (mTrackListener == null) return;
            mTrackListener.onPlayTrack(getAdapterPosition(), mTracks);
        }

        protected abstract void setupUI(View view);

        protected abstract void handleOptions();

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.image_options:
                    handleOptions();
                    break;
                default:
                    handlePlayTracks();
                    break;
            }
        }
    }
}
