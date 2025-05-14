package com.example.cinevibes.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.cinevibes.R;
import com.example.cinevibes.database.FilmRoomDatabase;
import com.example.cinevibes.model.Film;

import java.util.List;

public class FilmRecyclerAdapter extends RecyclerView.Adapter<FilmRecyclerAdapter.ViewHolder> {

    private int layout;
    private List<Film> filmList;
    private boolean listedVisible;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewTitle;
        private final CheckBox favouriteCheckbox;

        public ViewHolder(View view) {
            super(view);

            textViewTitle = view.findViewById(R.id.textViewTitle);
            favouriteCheckbox = view.findViewById(R.id.favoriteButton);
        }

        public TextView getTextViewTitle() {
            return textViewTitle;
        }

        public CheckBox getFavouriteCheckbox() {
            return favouriteCheckbox; }
    }

    public FilmRecyclerAdapter(int layout, List<Film> filmList, boolean listedVisible) {
        this.layout = layout;
        this.filmList = filmList;
        this.listedVisible = listedVisible;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(layout, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getTextViewTitle().setText(filmList.get(position).getTitle());

        viewHolder.getFavouriteCheckbox().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //Film currentFilm = filmList.get(viewHolder.getAdapterPosition());

               // currentFilm.setLiked(b);

               //FilmRoomDatabase.getDatabase(viewHolder.getTextViewTitle().getContext()).
                       // filmDao().updateFilm(currentFilm);

                if(b) {
                    FilmRoomDatabase.getDatabase(viewHolder.getTextViewTitle().getContext()).
                            filmDao().insertAll(filmList.get(position));
                } else {
                    FilmRoomDatabase.getDatabase(viewHolder.getTextViewTitle().getContext()).
                            filmDao().delete(filmList.get(position));
                }
            }
        });

        if (listedVisible == false) {
            viewHolder.getFavouriteCheckbox().setVisibility(View.INVISIBLE);
        }
    }


    @Override
    public int getItemCount() {
        return filmList.size();
    }
}
