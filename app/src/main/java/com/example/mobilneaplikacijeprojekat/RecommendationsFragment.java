package com.example.mobilneaplikacijeprojekat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import Model.Movie;
import adapter.MovieAdapter;
import service.RecommendationService;
import util.SessionManager;

public class RecommendationsFragment extends Fragment implements MovieAdapter.OnMovieClickListener {
    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    private ProgressBar progressBar;
    private TextView emptyText;
    private RecommendationService recommendationService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inf, @Nullable ViewGroup parent, @Nullable Bundle s) {
        View v = inf.inflate(R.layout.fragment_recommendations, parent, false);

        recyclerView = v.findViewById(R.id.recyclerView);
        progressBar = v.findViewById(R.id.progressBar);
        emptyText = v.findViewById(R.id.emptyText);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MovieAdapter(this);
        recyclerView.setAdapter(adapter);

        recommendationService = new RecommendationService(requireContext());

        loadRecommendations();

        return v;
    }

    private void loadRecommendations() {
        SessionManager sessionManager = new SessionManager(requireContext());
        Long userId = sessionManager.getUserId();

        if (userId == null) {
            showEmpty("Morate biti ulogovani za preporuke");
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        emptyText.setVisibility(View.GONE);

        recommendationService.generateRecommendations(userId, new RecommendationService.RecommendationCallback() {
            @Override
            public void onRecommendationsReady(List<Movie> recommendations) {
                if (!isAdded()) return;

                requireActivity().runOnUiThread(() -> {
                    progressBar.setVisibility(View.GONE);

                    if (recommendations.isEmpty()) {
                        showEmpty("Nema preporuka. Ocenite više filmova za bolje preporuke.");
                    } else {
                        adapter.setMovies(recommendations);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
    }

    private void showEmpty(String message) {
        recyclerView.setVisibility(View.GONE);
        emptyText.setText(message);
        emptyText.setVisibility(View.VISIBLE);
    }

    @Override
    public void onMovieClick(Movie movie) {
        MovieDetailsFragment fragment = MovieDetailsFragment.newInstance(movie.getId());
        ((MainActivity) requireActivity()).replaceFragment(fragment, true);
    }
}