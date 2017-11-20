package com.example.luanabelusso.aps_android.telas.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.luanabelusso.aps_android.R;

import java.util.List;

import com.example.luanabelusso.aps_android.entidades.ItemSorteio;

/**
 * Created by Marciano on 19/11/2017.
 */

public class AdapterItensSorteioPersonalizado extends ArrayAdapter<ItemSorteio> {

    private class ItemSorteioHolder {
        TextView txtDescricao;
        ImageButton btnEditar;
        ImageButton btnRemover;
    }

    public AdapterItensSorteioPersonalizado(Context context, int resource, List<ItemSorteio> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        ItemSorteioHolder itemSorteioHolder;

        if (v == null) {
            LayoutInflater li = LayoutInflater.from(getContext());
            v = li.inflate(R.layout.activity_adapter_itens, null);
        }

        itemSorteioHolder = (ItemSorteioHolder) v.getTag();
        if (itemSorteioHolder == null) {
            itemSorteioHolder = new ItemSorteioHolder();
            itemSorteioHolder.txtDescricao = v.findViewById(R.id.txtDescricao);
            itemSorteioHolder.btnEditar = v.findViewById(R.id.btnEditar);
            itemSorteioHolder.btnRemover = v.findViewById(R.id.btnDeletar);
            v.setTag(itemSorteioHolder);
        }

        ItemSorteio itemSorteio = getItem(position);

        if (itemSorteio != null) {
            itemSorteioHolder.txtDescricao.setText(itemSorteio.getDescricao());
        }

        return v;
    }
}
