package com.example.mobilneaplikacijeprojekat;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import Model.User;
import db.AppDatabase;

public class LoginFragment extends Fragment {

    private EditText inputUsername, inputPassword;
    private Button btnLogin, btnGoToRegister;
    private ProgressBar progress;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inf, @Nullable ViewGroup parent, @Nullable Bundle s) {
        View v = inf.inflate(R.layout.fragment_login, parent, false);
        inputUsername    = v.findViewById(R.id.inputUsername);
        inputPassword    = v.findViewById(R.id.inputPassword);
        btnLogin         = v.findViewById(R.id.btnLogin);
        btnGoToRegister  = v.findViewById(R.id.btnGoToRegister);
        progress         = v.findViewById(R.id.progress);

        btnLogin.setOnClickListener(view -> doLogin());
        btnGoToRegister.setOnClickListener(view ->
                ((MainActivity) requireActivity()).replaceFragment(new RegisterFragment(), true)
        );
        return v;
    }

    private void doLogin() {
        final String u = inputUsername.getText().toString().trim();
        final String p = inputPassword.getText().toString();

        if (TextUtils.isEmpty(u) || TextUtils.isEmpty(p)) {
            Toast.makeText(getContext(), "Popuni sva polja", Toast.LENGTH_SHORT).show();
            return;
        }

        progress.setVisibility(View.VISIBLE);
        new Thread(() -> {
            User user = AppDatabase.getInstance(requireContext())
                    .userDao()
                    .getByUsernameAndPassword(u, p);

            requireActivity().runOnUiThread(() -> {
                progress.setVisibility(View.GONE);
                if (user != null && user.getId() != null) {
                    new util.SessionManager(requireContext()).saveUserId(user.getId());
                    ((MainActivity) requireActivity()).replaceFragment(new MovieListFragment(), false);
                } else {
                    Toast.makeText(getContext(), "Pogrešni podaci", Toast.LENGTH_SHORT).show();
                }
            });
        }).start();
    }
}
