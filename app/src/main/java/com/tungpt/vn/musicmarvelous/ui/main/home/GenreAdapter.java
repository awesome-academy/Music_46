package com.tungpt.vn.musicmarvelous.ui.main.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.tungpt.vn.musicmarvelous.R;
import com.tungpt.vn.musicmarvelous.data.model.Genre;

import java.util.List;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.ViewHolder> {

    private List<Genre> mGenres;
    private Context mContext;
    private LayoutInflater mInflater;
    private OnGenreClickListener mListener;

    public GenreAdapter(List<Genre> genres, Context context) {
        mGenres = genres;
        mContext = context;
    }

    @NonNull
    @Override
    public GenreAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(parent.getContext());
        }
        View view = mInflater.from(parent.getContext())
                .inflate(R.layout.item_genre, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreAdapter.ViewHolder viewHolder, int i) {
        Genre genre = mGenres.get(i);
        viewHolder.bindView(genre, mListener);
    }

    @Override
    public int getItemCount() {
        return mGenres != null ? mGenres.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewGenre;
        private ImageView mImageViewGenre;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewGenre = itemView.findViewById(R.id.title_genre);
            mImageViewGenre = itemView.findViewById(R.id.image_genre);
        }

        public void bindView(final Genre genre, final OnGenreClickListener listener) {
            mTextViewGenre.setText(genre.getGenre());
            Glide.with(mImageViewGenre)
                    .load(genre.getGenreImage())
                    .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(mImageViewGenre);
            mImageViewGenre.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onGenreClicked(genre.getGenreName());
                }
            });
        }
    }

    public void setGenres(List<Genre> genres) {
        this.mGenres = genres;
        notifyDataSetChanged();
    }

    public void setOnGenreClickListener(OnGenreClickListener listener) {
        mListener = listener;
    }


    interface OnGenreClickListener {
        void onGenreClicked(String genre);
    }
}
