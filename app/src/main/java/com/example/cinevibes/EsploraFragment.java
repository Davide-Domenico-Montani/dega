package com.example.cinevibes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cinevibes.model.Film;
import com.example.cinevibes.model.FilmApiResponse;
import com.example.cinevibes.util.Constants;
import com.example.cinevibes.util.JSONParserUtils;

import java.io.IOException;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EsploraFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EsploraFragment extends Fragment {

    public static final String TAG = EsploraFragment.class.getName();


    public EsploraFragment() {
        // Required empty public constructor
    }

    public static EsploraFragment newInstance() {
        EsploraFragment fragment = new EsploraFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_esplora, container, false);
        // return inflater.inflate(R.layout.fragment_esplora, container, false);

        JSONParserUtils jsonParserUtils = new JSONParserUtils(getContext());

        try {
            FilmApiResponse response = jsonParserUtils.parseJSONFileWithGSon(Constants.SAMPLE_JSON_FILENAME);

            Log.d("DEBUG", "Sono qui");
            List<Film> filmList = response.getResults();

            int a = 0;
           // Log.i(TAG, response.getTotal_results() + "aaaa");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}