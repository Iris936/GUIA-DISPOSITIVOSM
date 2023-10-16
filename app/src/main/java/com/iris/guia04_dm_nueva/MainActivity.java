package com.iris.guia04_dm_nueva;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.iris.guia04_dm_nueva.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.BAgregarTrabajador.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, TipoTrabajador.class));
        });

        binding.BMostrar.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ListarTrabajadores.class));
        });

        binding.BAcerca.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AcercaDe.class);
                startActivity(intent);
            }
        });
    }
}

