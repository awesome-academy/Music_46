package com.example.themo.musicmarvelous.ui.main.favorite;

import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import com.example.themo.musicmarvelous.R;
import com.example.themo.musicmarvelous.data.model.Track;
import com.example.themo.musicmarvelous.ui.main.BaseTrackAdapter;
import com.example.themo.musicmarvelous.ui.main.TrackListener;

import java.util.List;

public class FavoriteAdapter extends BaseTrackAdapter<FavoriteAdapter.FavoriteViewHolder> {
    public FavoriteAdapter(Context context, TrackListener trackListener) {
        super(context, trackListener);
    }

    public class FavoriteViewHolder extends BaseTrackAdapter.BaseViewHolder {
        public FavoriteViewHolder(View itemView, List<Track> tracks, TrackListener trackListener) {
            super(itemView, tracks, trackListener);
        }

        @Override
        protected void setupUI(View view) {
        }

        @Override
        protected void handleOptions() {
            PopupMenu popupMenu = new PopupMenu(mContext, mImageViewOptions);
            popupMenu.inflate(R.menu.menu_item_offline_track);
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.action_play:
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
    }
}
