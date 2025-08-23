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

import java.util.ArrayList;

import Model.User;
import db.AppDatabase;

public class RegisterFragment extends Fragment {

    private EditText inputUsername, inputEmail, inputPassword;
    private Button btnCreateAccount;
    private ProgressBar progress;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inf, @Nullable ViewGroup parent, @Nullable Bundle s) {
        View v = inf.inflate(R.layout.fragment_register, parent, false);
        inputUsername    = v.findViewById(R.id.inputUsername);
        inputEmail       = v.findViewById(R.id.inputEmail);
        inputPassword    = v.findViewById(R.id.inputPassword);
        btnCreateAccount = v.findViewById(R.id.btnCreateAccount);
        progress         = v.findViewById(R.id.progress);

        btnCreateAccount.setOnClickListener(view -> doRegister());
        return v;
    }

    private void doRegister() {
        final String u = inputUsername.getText().toString().trim();
        final String e = inputEmail.getText().toString().trim();
        final String p = inputPassword.getText().toString();

        // Minimalna validacija
        if (TextUtils.isEmpty(u) || TextUtils.isEmpty(e) || TextUtils.isEmpty(p)) {
            Toast.makeText(getContext(), "Popuni sva polja", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(e).matches()) {
            Toast.makeText(getContext(), "Neispravan email format", Toast.LENGTH_SHORT).show();
            return;
        }

        // UI state
        progress.setVisibility(View.VISIBLE);
        btnCreateAccount.setEnabled(false);

        new Thread(() -> {
            String error = null;

            try {
                AppDatabase db = AppDatabase.getInstance(requireContext());

                if (db.userDao().countByUsername(u) > 0) {
                    error = "Username je zauzet";
                } else if (db.userDao().countByEmail(e) > 0) {
                    error = "Email je zauzet";
                } else {
                    User user = new User();
                    user.setUsername(u);
                    user.setEmail(e);
                    user.setPassword(p);
                    user.setWatchedMovies(new ArrayList<String>());
                    user.setWishList(new ArrayList<String>());

                    long id = db.userDao().insert(user);
                    if (id <= 0) error = "Greška pri kreiranju";
                }
            } catch (Exception ex) {
                error = "Greška: " + ex.getMessage();
            }

            final String finalError = error;

            if (!isAdded()) return; // fragment možda više nije prikazan

            requireActivity().runOnUiThread(() -> {
                progress.setVisibility(View.GONE);
                btnCreateAccount.setEnabled(true);

                if (finalError != null) {
                    Toast.makeText(getContext(), finalError, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Nalog kreiran, uloguj se.", Toast.LENGTH_SHORT).show();
                    requireActivity().getOnBackPressedDispatcher().onBackPressed();
                }
            });
        }).start();
    }
}
