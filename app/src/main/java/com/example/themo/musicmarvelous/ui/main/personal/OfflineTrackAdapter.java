package com.example.themo.musicmarvelous.ui.main.personal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.themo.musicmarvelous.R;
import com.example.themo.musicmarvelous.constants.Constants;
import com.example.themo.musicmarvelous.data.model.Track;
import com.example.themo.musicmarvelous.ui.main.TrackListener;

import java.util.List;

public class OfflineTrackAdapter extends RecyclerView.Adapter<OfflineTrackAdapter.ViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<Track> mTracks;
    private TrackListener mTrackListener;

    public OfflineTrackAdapter(Context context, TrackListener trackListener) {
        mContext = context;
        mTrackListener = trackListener;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.item_track, viewGroup, false);
        return new ViewHolder(view, mTracks, mTrackListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bindView(i);
    }

    @Override
    public int getItemCount() {
        return mTracks != null ? mTracks.size() : 0;
    }

    public void setTracks(List<Track> tracks) {
        mTracks = tracks;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Context mContext;
        private TrackListener mTrackListener;
        private List<Track> mTracks;
        private TextView mTextViewTitle;
        private TextView mTextViewArtist;
        private ImageView mImageViewTrack;
        private ImageView mImageViewOptions;

        public ViewHolder(@NonNull View itemView, List<Track> tracks, TrackListener trackListener) {
            super(itemView);
            this.mContext = itemView.getContext();
            this.mTracks = tracks;
            this.mTrackListener = trackListener;
            this.mTextViewTitle = itemView.findViewById(R.id.text_title);
            this.mTextViewArtist = itemView.findViewById(R.id.text_artist);
            this.mImageViewTrack = itemView.findViewById(R.id.image_track);
            this.mImageViewOptions = itemView.findViewById(R.id.image_options);
            this.mImageViewOptions.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        public void bindView(int position) {
            Track track = mTracks.get(position);
            Glide.with(mContext).load(track.getArtworkUrl())
                    .apply(new RequestOptions()
                            .override(Constants.DEFAULT_ITEM_SIZE)
                            .centerCrop()
                            .error(R.drawable.logo_app))
                    .into(mImageViewTrack);
            mTextViewTitle.setText(track.getTitle());
            mTextViewArtist.setText(track.getPublisherAlbumTitle());
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.image_options:
                    handleOption();
                    break;
                default:
                    handlePlayTrack();
            }
        }

        private void handleOption() {
            PopupMenu popupMenu = new PopupMenu(mContext, mImageViewOptions);
            popupMenu.inflate(R.menu.menu_item_offline_track);
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.action_play:
                            handlePlayTrack();
                            return true;
                        case R.id.action_delete:
                            return true;
                        default:
                            return false;
                    }
                }
            });
            popupMenu.show();
        }

        private void handlePlayTrack() {
            if (mTrackListener == null) return;
            mTrackListener.onPlayTrack(getAdapterPosition(), mTracks);
        }
    }
}
