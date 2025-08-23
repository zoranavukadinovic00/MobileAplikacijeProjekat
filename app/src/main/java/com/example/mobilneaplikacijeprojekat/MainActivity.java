package com.example.mobilneaplikacijeprojekat;



import android.os.Bundle;
import android.service.voice.VoiceInteractionSession;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


public class MainActivity extends AppCompatActivity {

    public VoiceInteractionSession onBackPressedDispatcher;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            replaceFragment(new LoginFragment(), false);
        }
    }

    public void replaceFragment(Fragment fragment, boolean addToBackStack) {
        var tx = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment);
        if (addToBackStack) tx.addToBackStack(null);
        tx.commit();
    }
}
