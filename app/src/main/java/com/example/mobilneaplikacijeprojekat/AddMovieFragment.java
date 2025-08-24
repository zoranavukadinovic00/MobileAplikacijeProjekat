package com.example.mobilneaplikacijeprojekat;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import Model.Movie;
import view.MovieViewModel;

public class AddMovieFragment extends Fragment {
    private EditText titleInput, genreInput, yearInput, descriptionInput, imageUrlInput;
    private Button btnSave, btnCancel;
    private MovieViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inf, @Nullable ViewGroup parent, @Nullable Bundle s) {
        View v = inf.inflate(R.layout.fragment_add_movie, parent, false);

        titleInput = v.findViewById(R.id.titleInput);
        genreInput = v.findViewById(R.id.genreInput);
        yearInput = v.findViewById(R.id.yearInput);
        descriptionInput = v.findViewById(R.id.descriptionInput);
        imageUrlInput = v.findViewById(R.id.imageUrlInput);
        btnSave = v.findViewById(R.id.btnSave);
        btnCancel = v.findViewById(R.id.btnCancel);

        viewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        btnSave.setOnClickListener(view -> saveMovie());
        btnCancel.setOnClickListener(view -> requireActivity().getOnBackPressedDispatcher().onBackPressed());

        return v;
    }

    private void saveMovie() {
        String title = titleInput.getText().toString().trim();
        String genre = genreInput.getText().toString().trim();
        String yearStr = yearInput.getText().toString().trim();
        String description = descriptionInput.getText().toString().trim();
        String imageUrl = imageUrlInput.getText().toString().trim();

        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(genre) || TextUtils.isEmpty(yearStr)) {
            Toast.makeText(getContext(), "Popunite obavezna polja", Toast.LENGTH_SHORT).show();
            return;
        }

        int year;
        try {
            year = Integer.parseInt(yearStr);
            if (year < 1888 || year > 2030) {
                Toast.makeText(getContext(), "Unesite validnu godinu", Toast.LENGTH_SHORT).show();
                return;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), "Godina mora biti broj", Toast.LENGTH_SHORT).show();
            return;
        }

        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setGenre(genre);
        movie.setYear(year);
        movie.setDescription(description);
        movie.setImageUrl(imageUrl);
        movie.setRating(0.0);

        viewModel.insert(movie);
        Toast.makeText(getContext(), "Film je dodat", Toast.LENGTH_SHORT).show();
        requireActivity().getOnBackPressedDispatcher().onBackPressed();
    }
}