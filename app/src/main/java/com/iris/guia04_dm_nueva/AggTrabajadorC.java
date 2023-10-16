package com.iris.guia04_dm_nueva;
import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.iris.guia04_dm_nueva.databinding.ActivityAggTrabajadorCBinding;

public class AggTrabajadorC extends AppCompatActivity {

    private ActivityAggTrabajadorCBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAggTrabajadorCBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        RepositorioTrabajador dbSource = ServiceLocator.getInstance().getDBSource();

        binding.BotonAgregar.setOnClickListener(v -> {
            if (validateFields()) {
                if (dbSource.getTrabajadorById(binding.etID.getText().toString()) != null) {
                    Toast.makeText(getApplicationContext(), "No puede utilizar el ID", Toast.LENGTH_SHORT).show();
                } else {
                    TrabajadorPorTiempoCompleto nuevoTrabajador = new TrabajadorPorTiempoCompleto(
                            binding.etID.getText().toString(),
                            binding.etNombre.getText().toString(),
                            binding.etApellido.getText().toString(),
                            Float.parseFloat(binding.etSalario.getText().toString())
                    );

                    if (dbSource.AddTrabajador(nuevoTrabajador)) {
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                        alertDialogBuilder.setTitle("Trabajador Registrado...");
                        alertDialogBuilder.setMessage("Registrado con éxito!!");
                        alertDialogBuilder.setPositiveButton("Aceptar", (dialog, which) -> finish());

                        alertDialogBuilder.setOnCancelListener(dialogInterface -> finish());

                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                    } else {
                        new AlertDialog.Builder(AggTrabajadorC.this)
                                .setTitle("Error al agregar...")
                                .setMessage("No se pudo guardar el registro...")
                                .setPositiveButton("Aceptar", null)
                                .show();
                    }
                }
            }
        });
    }

    private boolean validateFields() {
        if (binding.etID.getText().toString().isEmpty() ||
                binding.etNombre.getText().toString().isEmpty() ||
                binding.etApellido.getText().toString().isEmpty() ||
                binding.etEdad.getText().toString().isEmpty() ||
                binding.etSalario.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Llene todos los campos, por favor.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
