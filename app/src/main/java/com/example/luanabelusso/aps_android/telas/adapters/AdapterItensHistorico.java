package com.example.luanabelusso.aps_android.telas.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.luanabelusso.aps_android.R;
import com.example.luanabelusso.aps_android.entidades.Sorteio;

import java.util.List;


public class AdapterItensHistorico extends ArrayAdapter<Sorteio> {

    private class SorteioHolder {
        TextView txtData;
        TextView txtCriterio;
        ImageButton btnVisualizar;
    }

    public AdapterItensHistorico(Context context,List<Sorteio> objects) {
        super(context, R.layout.activity_adapter_historico, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        SorteioHolder sorteioHolder;
        if (v == null){
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            v = layoutInflater.inflate(R.layout.activity_adapter_historico,null);
        }
        sorteioHolder = (SorteioHolder) v.getTag();

        if (sorteioHolder == null){
            sorteioHolder = new SorteioHolder();
            sorteioHolder.txtData = v.findViewById(R.id.txtData);
            sorteioHolder.txtCriterio = v.findViewById(R.id.txtCriterio);
            sorteioHolder.btnVisualizar = v.findViewById(R.id.btnVisualizar);
            v.setTag(sorteioHolder);
        }

        Sorteio sorteio = getItem(position);
        if (sorteio != null){
            sorteioHolder.txtData.setText(sorteio.getDataSorteio().toString());
            sorteioHolder.txtCriterio.setText(sorteio.getTipoCriterio().toString());
        }
        return super.getView(position, convertView, parent);


    }
}
