package com.iris.guia04_dm_nueva;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.DialogInterface;
import android.os.Bundle;

import com.iris.guia04_dm_nueva.databinding.ActivityListarTrabajadoresBinding;

import java.util.ArrayList;
import java.util.List;

public class ListarTrabajadores extends AppCompatActivity implements AdaptadorTrabajador.OnItemLongClickListener {

    private ActivityListarTrabajadoresBinding binding;
    private AdaptadorTrabajador personaAdapter;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListarTrabajadoresBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        RepositorioTrabajador dbSource = ServiceLocator.getInstance().getDBSource();
        List<Trabajador> listaTrabajadores = dbSource.getAllListTrabajadores();

        // Configurando adaptador
        personaAdapter = new AdaptadorTrabajador((ArrayList<Trabajador>) listaTrabajadores);
        layoutManager = new LinearLayoutManager(this);

        binding.Trabajadores.setAdapter(personaAdapter);
        binding.Trabajadores.setLayoutManager(layoutManager);
        binding.Trabajadores.setHasFixedSize(true);

        personaAdapter.setOnItemLongClickListener(this);
    }

    private void editarTrabajador(int position) {
        Trabajador trabajador = personaAdapter.getTrabajador(position);
        // Aquí puedes agregar el código para editar un trabajador.
    }

    private void eliminarTrabajador(int position) {
        AlertDialog.Builder deleteBuilder = new AlertDialog.Builder(this);
        deleteBuilder.setTitle("Eliminar Trabajador")
                .setMessage("¿Estás seguro de que quieres eliminar este trabajador?")
                .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Trabajador trabajador = personaAdapter.getTrabajador(position);
                        RepositorioTrabajador dbSource = ServiceLocator.getInstance().getDBSource();
                        dbSource.removeTrabajador(trabajador);

                        personaAdapter.removeTrabajador(position);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    @Override
    public void onItemLongClick(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Opciones")
                .setItems(new CharSequence[]{"Editar", "Eliminar"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                editarTrabajador(position);
                                break;
                            case 1:
                                eliminarTrabajador(position);
                                break;
                        }
                    }
                })
                .show();
    }
}