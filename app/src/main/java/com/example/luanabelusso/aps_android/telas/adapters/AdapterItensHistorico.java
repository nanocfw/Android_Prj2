package com.example.luanabelusso.aps_android.telas.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.luanabelusso.aps_android.R;
import com.example.luanabelusso.aps_android.banco.controllers.ControllerSorteio;
import com.example.luanabelusso.aps_android.entidades.Sorteio;
import com.example.luanabelusso.aps_android.telas.VisualizarSorteioActivity;
import com.example.luanabelusso.aps_android.util.Util;

import java.util.List;


public class AdapterItensHistorico extends ArrayAdapter<Sorteio> {

    private class SorteioHolder {
        TextView txtData;
        TextView txtCriterio;
        ImageButton btnVisualizar;
    }

    public AdapterItensHistorico(Context context, List<Sorteio> objects) {
        super(context, R.layout.activity_adapter_historico, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        SorteioHolder sorteioHolder;
        if (v == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            v = layoutInflater.inflate(R.layout.activity_adapter_historico, null);
        }
        sorteioHolder = (SorteioHolder) v.getTag();

        if (sorteioHolder == null) {
            sorteioHolder = new SorteioHolder();
            sorteioHolder.txtData = v.findViewById(R.id.txtData);
            sorteioHolder.txtCriterio = v.findViewById(R.id.txtCriterio);
            sorteioHolder.btnVisualizar = v.findViewById(R.id.btnVisualizar);
            sorteioHolder.btnVisualizar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Sorteio sorteio = (Sorteio) v.getTag();
                    ControllerSorteio.getInstance().setCurrentSorteio(sorteio);
                    Intent tela = new Intent(getContext(), VisualizarSorteioActivity.class);
                    getContext().startActivity(tela);
                }
            });
            v.setTag(sorteioHolder);
        }

        Sorteio sorteio = getItem(position);
        if (sorteio != null) {
            sorteioHolder.txtData.setText(Util.dateTimeToStr(sorteio.getDataSorteio()));
            sorteioHolder.txtCriterio.setText(sorteio.getTipoCriterio().toString());
            sorteioHolder.btnVisualizar.setTag(sorteio);
        }
        return v;
    }
}
