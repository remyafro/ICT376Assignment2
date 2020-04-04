package com.example.ict376_assignment2;

import android.app.Activity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends Activity {

    MainPageFragment mainPageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // By default Android will retain the fragment objects after rotation
        if (savedInstanceState == null) {
            mainPageFragment = MainPageFragment.newInstance();
            getFragmentManager().beginTransaction().add(R.id.mainpage_fragment, mainPageFragment).commit();

        }else{
            mainPageFragment = (MainPageFragment)getFragmentManager().findFragmentById(R.id.mainpage_fragment);
        }
    }
}
