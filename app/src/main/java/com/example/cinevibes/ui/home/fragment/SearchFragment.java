package com.example.cinevibes.ui.home.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cinevibes.EsploraFragment;
import com.example.cinevibes.R;
//import com.example.cinevibes.adapter.FilmRecyclerAdapter;
import com.example.cinevibes.adapter.FilmRecyclerAdapter;
import com.example.cinevibes.model.Film;
import com.example.cinevibes.model.FilmApiResponse;
import com.example.cinevibes.util.Constants;
import com.example.cinevibes.util.JSONParserUtils;
import com.example.cinevibes.util.ResponseCallback;

import java.io.IOException;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment implements ResponseCallback {


    public static final String TAG = EsploraFragment.class.getName();


    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        return fragment;
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

        JSONParserUtils jsonParserUtils = new JSONParserUtils(getContext());

        try {
            FilmApiResponse response = jsonParserUtils.parseJSONFileWithGSon(Constants.SAMPLE_JSON_FILENAME);
            //Log.i(TAG, response.getTotal_results() + "Total result to String");
            List<Film> filmList = response.getResults();

            FilmRecyclerAdapter adapter = new FilmRecyclerAdapter(R.layout.card_film, filmList, true);

            recyclerView.setAdapter(adapter);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return view;
    }

    @Override
    public void onSuccess(List<Film> filmList, long lastUpdate) {

    }

    @Override
    public void onFailure(String errorMessage) {

    }
}