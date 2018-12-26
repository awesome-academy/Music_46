package com.example.themo.musicmarvelous.ui.main.home;

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
import com.example.themo.musicmarvelous.data.model.Genre;

import java.util.List;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.ViewHolder> {

    private List<Genre> mGenres;
    private Context mContext;

    public GenreAdapter(List<Genre> genres, Context context) {
        mGenres = genres;
        mContext = context;
    }

    @NonNull
    @Override
    public GenreAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_genre, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreAdapter.ViewHolder viewHolder, int i) {
        Genre genre = mGenres.get(i);
        viewHolder.bindView(genre);
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

        public void bindView(Genre genre) {
            mTextViewGenre.setText(genre.getGenre());
            Glide.with(mImageViewGenre)
                    .load(genre.getGenreImage())
                    .into(mImageViewGenre);
        }
    }
}
