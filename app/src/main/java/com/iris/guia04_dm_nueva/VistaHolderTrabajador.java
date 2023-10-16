package com.iris.guia04_dm_nueva;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class VistaHolderTrabajador extends RecyclerView.ViewHolder  {

    private TextView IdPersona;
    private TextView nomPersona;
    private TextView tipoTrabajador;
    private TextView totalPagar;


    public VistaHolderTrabajador(@NonNull View itemView) {
        super(itemView);
        this.IdPersona = itemView.findViewById(R.id.texIDPersona);
        this.nomPersona = itemView.findViewById(R.id.texnombrePersona);
        this.tipoTrabajador = itemView.findViewById(R.id.txTipoTrabajador);
        this.totalPagar = itemView.findViewById(R.id.txTotalTrabajador);
    }

    public TextView getCodigoPersona() {
        return IdPersona;
    }

    public TextView getNombrePersona() {
        return nomPersona;
    }

    public TextView getTipoTrabajador() {
        return tipoTrabajador;
    }

    public TextView getTotalPagar() {
        return totalPagar;
    }
}