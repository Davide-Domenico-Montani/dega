
package com.example.cinevibes.ui.home.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cinevibes.R;
//import com.example.cinevibes.adapter.FilmRecyclerAdapter;
//import com.example.cinevibes.database.FilmRoomDatabase;
import com.example.cinevibes.adapter.FilmRecyclerAdapter;
import com.example.cinevibes.database.FilmRoomDatabase;
import com.example.cinevibes.model.Film;
import com.example.cinevibes.model.FilmApiResponse;
import com.example.cinevibes.util.Constants;
import com.example.cinevibes.util.JSONParserUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ListFragment extends Fragment {

    public static final String TAG = ListFragment.class.getName();

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        List<Film> filmList =
                FilmRoomDatabase.getDatabase(getContext())
                        .filmDao().getAll();

        FilmRecyclerAdapter adapter =
                new FilmRecyclerAdapter(R.layout.card_film, filmList, false);

        recyclerView.setAdapter(adapter);

        return view;

    }
}
