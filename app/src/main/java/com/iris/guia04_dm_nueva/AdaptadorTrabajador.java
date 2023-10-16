package com.iris.guia04_dm_nueva;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorTrabajador extends RecyclerView.Adapter<VistaHolderTrabajador>{
    public ArrayList<Trabajador> datos;

    public interface OnItemLongClickListener {
        void onItemLongClick(int position);
    }

    public OnItemLongClickListener longClickListener;

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        longClickListener = listener;
    }
    public AdaptadorTrabajador(ArrayList<Trabajador> datos) {
        this.datos = datos;
    }

    @NonNull
    @Override
    public VistaHolderTrabajador onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trabajador,parent,false);
        return new VistaHolderTrabajador(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VistaHolderTrabajador holder, int position) {

        holder.getCodigoPersona().setText(String.valueOf(datos.get(position).getCPersona()));
        holder.getNombrePersona().setText(datos.get(position).getNPersona() + " " + datos.get(position).getAPersona());
        holder.getTipoTrabajador().setText(datos.get(position).getTipoTrabajador() == 1? "Tiempo Completo" : "Por Hora" );
        holder.getTotalPagar().setText(Float.toString(datos.get(position).getTPagar()));
    }
    @Override
    public int getItemCount() {

        return datos.size();
    }
    public Trabajador getTrabajador(int position) {

        return datos.get(position);
    }

    // Método para eliminar un trabajador en una posición dada
    public void removeTrabajador(int position) {
        datos.remove(position);
        notifyItemRemoved(position);
    }
}
