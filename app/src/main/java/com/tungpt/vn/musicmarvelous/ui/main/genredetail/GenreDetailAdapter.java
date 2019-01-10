package com.tungpt.vn.musicmarvelous.ui.main.genredetail;

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
import com.tungpt.vn.musicmarvelous.R;
import com.tungpt.vn.musicmarvelous.constants.Constants;
import com.tungpt.vn.musicmarvelous.data.model.Track;
import com.tungpt.vn.musicmarvelous.ui.main.TrackListener;

import java.util.ArrayList;
import java.util.List;

public class GenreDetailAdapter extends RecyclerView.Adapter<GenreDetailAdapter.GenreDetailViewHolder> {
    private TrackListener mTrackListener;
    private Context mContext;
    private List<Track> mTracks;
    private LayoutInflater mInflater;

    public GenreDetailAdapter(TrackListener trackListener, Context context) {
        mTrackListener = trackListener;
        mContext = context;
    }

    @NonNull
    @Override
    public GenreDetailAdapter.GenreDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        mInflater = LayoutInflater.from(mContext);
        View view = mInflater.inflate(R.layout.item_track, parent, false);
        return new GenreDetailViewHolder(view, mTrackListener, mTracks);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreDetailAdapter.GenreDetailViewHolder genreDetailViewHolder, int i) {
        genreDetailViewHolder.bindData(i);
    }

    @Override
    public int getItemCount() {
        return mTracks == null ? 0 : mTracks.size();
    }

    public class GenreDetailViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTextViewTitle;
        private ImageView mImageViewTrack;
        private ImageView mImageViewOptions;
        private TextView mTextViewArtist;
        private TrackListener mTrackListener;
        private Track mCurrentTrack;
        private List<Track> mTracks;
        private Context mContext;

        public GenreDetailViewHolder(@NonNull View itemView, TrackListener trackListener, List<Track> tracks) {
            super(itemView);
            this.mTrackListener = trackListener;
            this.mTracks = tracks;
            mImageViewTrack = itemView.findViewById(R.id.image_track);
            mImageViewOptions = itemView.findViewById(R.id.image_options);
            mTextViewTitle = itemView.findViewById(R.id.text_title);
            mTextViewArtist = itemView.findViewById(R.id.text_artist);

            mImageViewOptions.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        public void bindData(int position) {
            if (mTracks == null) return;
            mCurrentTrack = mTracks.get(position);
            Glide.with(mContext).load(mCurrentTrack.getArtworkUrl())
                    .apply(new RequestOptions()
                            .override(Constants.ITEM_SIZE)
                            .fitCenter()
                            .error(R.drawable.logo_app))
                    .into(mImageViewTrack);
            mTextViewTitle.setText(mCurrentTrack.getTitle());
            mTextViewArtist.setText(mCurrentTrack.getPublisherAlbumTitle());
        }

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

        private void handleOptions() {
            PopupMenu popupMenu = new PopupMenu(mContext, mImageViewOptions);
            popupMenu.inflate(R.menu.menu_item_track);
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.action_add_to_now_playing:
                            mTrackListener.onAddToNowPlaying(mCurrentTrack);
                            return true;
                        case R.id.action_add_to_favorite:
                            mTrackListener.onAddToFavorite(mCurrentTrack);
                            return true;
                        case R.id.action_download:
                            mTrackListener.downloadTrack(mCurrentTrack);
                            return true;
                        default:
                            return false;
                    }
                }
            });
            popupMenu.show();
        }

        private void handlePlayTracks() {
            if (mTrackListener == null) return;
            mTrackListener.onPlayTrack(getAdapterPosition(), mTracks);
        }
    }

    public void updateListTrack(List<Track> tracks) {
        if (tracks == null) return;
        if (mTracks == null) {
            mTracks = new ArrayList<>();
        }
        int startPosition = mTracks.size();
        mTracks.addAll(tracks);
        notifyItemRangeInserted(startPosition, tracks.size());
    }

    public void clearData() {
        if (mTracks != null) {
            mTracks.clear();
            notifyDataSetChanged();
        }
    }
}
