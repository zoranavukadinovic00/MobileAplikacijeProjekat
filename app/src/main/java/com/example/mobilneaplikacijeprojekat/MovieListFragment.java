package com.example.mobilneaplikacijeprojekat;

import android.os.Bundle;
import android.view.*;
<<<<<<< HEAD
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.mobilneaplikacijeprojekat.R;

public class MovieListFragment extends Fragment {

    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inf, @Nullable ViewGroup parent, @Nullable Bundle s) {
        View v = inf.inflate(R.layout.fragment_movie_list, parent, false);
        ((TextView)v.findViewById(R.id.placeholderText)).setText("Movies list – sledeći korak: CRUD");
        return v;
    }
}
=======
import android.widget.SearchView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import Model.Movie;
import adapter.MovieAdapter;
import util.SessionManager;
import view.MovieViewModel;

public class MovieListFragment extends Fragment implements MovieAdapter.OnMovieClickListener {
    private MovieViewModel viewModel;
    private MovieAdapter adapter;
    private SearchView searchView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inf, @Nullable ViewGroup parent, @Nullable Bundle s) {
        View v = inf.inflate(R.layout.fragment_movie_list, parent, false);

        RecyclerView recyclerView = v.findViewById(R.id.recyclerView);
        FloatingActionButton fabAdd = v.findViewById(R.id.fabAddMovie);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MovieAdapter(this);
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        viewModel.getAllMovies().observe(getViewLifecycleOwner(), movies -> {
            if (movies != null) {
                adapter.setMovies(movies);
            }
        });

        fabAdd.setOnClickListener(v1 -> {
            if (new SessionManager(requireContext()).isLoggedIn()) {
                ((MainActivity) requireActivity()).replaceFragment(new AddMovieFragment(), true);
            } else {
                Toast.makeText(getContext(), "Morate biti ulogovani", Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.movie_list_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchMovies(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    viewModel.getAllMovies().observe(getViewLifecycleOwner(), adapter::setMovies);
                }
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            new SessionManager(requireContext()).logout();
            ((MainActivity) requireActivity()).replaceFragment(new LoginFragment(), false);
            return true;
        } else if (item.getItemId() == R.id.action_recommendations) {
            ((MainActivity) requireActivity()).replaceFragment(new RecommendationsFragment(), true);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void searchMovies(String query) {
        viewModel.searchMovies(query).observe(getViewLifecycleOwner(), adapter::setMovies);
    }

    @Override
    public void onMovieClick(Movie movie) {
        MovieDetailsFragment fragment = MovieDetailsFragment.newInstance(movie.getId());
        ((MainActivity) requireActivity()).replaceFragment(fragment, true);
    }
}
>>>>>>> d8badaf (Initial commit - MobileAplikacijeProjekat)
