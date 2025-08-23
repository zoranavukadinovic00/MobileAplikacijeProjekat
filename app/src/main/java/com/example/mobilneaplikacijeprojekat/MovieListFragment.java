package com.example.mobilneaplikacijeprojekat;

import android.os.Bundle;
import android.view.*;
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
