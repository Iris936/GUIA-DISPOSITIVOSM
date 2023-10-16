package com.iris.guia04_dm_nueva;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;

import com.iris.guia04_dm_nueva.databinding.ActivityTipoTrabajadorBinding;

public class TipoTrabajador extends AppCompatActivity {

    private ActivityTipoTrabajadorBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTipoTrabajadorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.Siguiente.setOnClickListener(v -> {
            RadioButton selectedRadioButton = findViewById(binding.Trabajadores.getCheckedRadioButtonId());
            boolean idSelected = selectedRadioButton.getText().toString().contains("hora");
            finish();
            startActivity(new Intent(TipoTrabajador.this, idSelected ? AggTrabajadorH.class : AggTrabajadorC.class));
        });
    }
}

