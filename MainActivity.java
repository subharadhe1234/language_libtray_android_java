package com.example.machine_learnnig;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import com.example.machine_learnnig.databinding.ActivityMainBinding;

import   com.example.machine_learnnig.language.*;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private static final String TAG = "MainActivity_";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        load language which store in shared preference when the app start
        LanguageManager languageManager=new LanguageManager(this);
        languageManager.lodeLanguage();
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());


        /*
         * on click on the change language button
         * and call the show language dialog function
         * */
        binding.changeLanguage.setOnClickListener(v -> {
          // call this file 
            languageManager.showLanguageDialog();

        });

    } }
