package com.example.mobilneaplikacijeprojekat;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import Model.Movie;
import Model.Review;
import adapter.ReviewAdapter;
import util.SessionManager;
import view.ReviewViewModel;

public class MovieDetailsFragment extends Fragment {
    private static final String ARG_MOVIE_ID = "movie_id";

    private long movieId;
    private TextView titleText, genreText, yearText, descriptionText, avgRatingText;
    private RatingBar userRatingBar;
    private EditText commentInput;
    private Button btnSubmitReview;
    private RecyclerView reviewsRecyclerView;
    private ReviewViewModel reviewViewModel;
    private ReviewAdapter reviewAdapter;
    private SessionManager sessionManager;

    public static MovieDetailsFragment newInstance(long movieId) {
        MovieDetailsFragment fragment = new MovieDetailsFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_MOVIE_ID, movieId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            movieId = getArguments().getLong(ARG_MOVIE_ID);
        }
        sessionManager = new SessionManager(requireContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inf, @Nullable ViewGroup parent, @Nullable Bundle s) {
        View v = inf.inflate(R.layout.fragment_movie_details, parent, false);

        titleText = v.findViewById(R.id.titleText);
        genreText = v.findViewById(R.id.genreText);
        yearText = v.findViewById(R.id.yearText);
        descriptionText = v.findViewById(R.id.descriptionText);
        avgRatingText = v.findViewById(R.id.avgRatingText);
        userRatingBar = v.findViewById(R.id.userRatingBar);
        commentInput = v.findViewById(R.id.commentInput);
        btnSubmitReview = v.findViewById(R.id.btnSubmitReview);
        reviewsRecyclerView = v.findViewById(R.id.reviewsRecyclerView);

        setupRecyclerView();
        setupViewModel();
        loadMovieDetails();

        btnSubmitReview.setOnClickListener(view -> submitReview());

        return v;
    }

    private void setupRecyclerView() {
        reviewAdapter = new ReviewAdapter();
        reviewsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        reviewsRecyclerView.setAdapter(reviewAdapter);
    }

    private void setupViewModel() {
        reviewViewModel = new ViewModelProvider(this).get(ReviewViewModel.class);

        reviewViewModel.getReviewsForMovie(movieId).observe(getViewLifecycleOwner(), reviews -> {
            if (reviews != null) {
                reviewAdapter.setReviews(reviews);
            }
        });

        reviewViewModel.getAverageRating(movieId).observe(getViewLifecycleOwner(), avgRating -> {
            if (avgRating != null) {
                avgRatingText.setText(String.format("Prosečna ocena: %.1f", avgRating));
            }
        });
    }

    private void loadMovieDetails() {
        // Load movie details using MovieViewModel
        // This would be implemented based on your existing pattern
    }

    private void submitReview() {
        if (!sessionManager.isLoggedIn()) {
            Toast.makeText(getContext(), "Morate biti ulogovani", Toast.LENGTH_SHORT).show();
            return;
        }

        float rating = userRatingBar.getRating();
        String comment = commentInput.getText().toString().trim();

        if (rating == 0) {
            Toast.makeText(getContext(), "Unesite ocenu", Toast.LENGTH_SHORT).show();
            return;
        }

        Long userId = sessionManager.getUserId();
        if (userId == null) return;

        Review review = new Review();
        review.setMovieId(movieId);
        review.setUserId(userId);
        review.setRating((int) rating);
        review.setComment(TextUtils.isEmpty(comment) ? null : comment);
        review.setTimestamp(System.currentTimeMillis());

        reviewViewModel.insertReview(review);

        userRatingBar.setRating(0);
        commentInput.setText("");
        Toast.makeText(getContext(), "Recenzija dodana", Toast.LENGTH_SHORT).show();
    }
}