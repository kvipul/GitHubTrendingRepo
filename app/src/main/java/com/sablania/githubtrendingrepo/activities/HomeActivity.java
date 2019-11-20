package com.sablania.githubtrendingrepo.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.sablania.githubtrendingrepo.R;
import com.sablania.githubtrendingrepo.fragments.HomePageFragment;


public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Fragment fragment = HomePageFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment, HomePageFragment.TAG).commitAllowingStateLoss();
    }
}
