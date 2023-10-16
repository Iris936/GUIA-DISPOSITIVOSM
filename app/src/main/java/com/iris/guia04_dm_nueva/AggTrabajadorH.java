package com.iris.guia04_dm_nueva;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.iris.guia04_dm_nueva.databinding.ActivityAggTrabajadorHBinding;


public class AggTrabajadorH extends AppCompatActivity {

    private ActivityAggTrabajadorHBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RepositorioTrabajador dbSource = ServiceLocator.getInstance().getDBSource();
        super.onCreate(savedInstanceState);
        binding = ActivityAggTrabajadorHBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.BotonAgregar.setOnClickListener(v -> {

            if (validateFields()){
                if (dbSource.getTrabajadorById(binding.edId.getText().toString())!= null){
                    Toast.makeText(getApplicationContext(), "No puede utilizar el ID", Toast.LENGTH_SHORT).show();
                }
                else{

                    if( dbSource.AddTrabajador(new TrabajadorPorHora(
                            binding.edId.getText().toString(),
                            binding.edNombre.getText().toString(),
                            binding.edApellido.getText().toString(),
                            Integer.parseInt(binding.edHoras.getText().toString()),
                            Float.parseFloat(binding.etValor.getText().toString() )))){

                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                        alertDialogBuilder.setTitle("Trabajador registrado...");
                        alertDialogBuilder.setMessage("Registrado con éxito!!");
                        alertDialogBuilder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });

                        alertDialogBuilder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialogInterface) {
                                finish();
                            }
                        });

                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                    }
                    else{
                        new AlertDialog.Builder(AggTrabajadorH.this)
                                .setTitle("Error al agregar...")
                                .setMessage("Registro no guardado")
                                .setPositiveButton("Aceptar", null)
                                .show();
                    }
                }
            }
        });

    }

    private boolean validateFields(){
        if (binding.edId.getText().toString().isEmpty() ||
                binding.edNombre.getText().toString().isEmpty()||
                binding.edApellido.getText().toString().isEmpty() ||
                binding.edEdad.getText().toString().isEmpty() ||
                binding.etValor.getText().toString().isEmpty()||
                binding.edHoras.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Llene todos los campos, por favor.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
