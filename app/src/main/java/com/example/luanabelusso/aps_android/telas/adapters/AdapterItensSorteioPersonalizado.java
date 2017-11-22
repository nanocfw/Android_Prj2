package com.example.luanabelusso.aps_android.telas.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.luanabelusso.aps_android.R;

import java.util.List;

import com.example.luanabelusso.aps_android.banco.controllers.ControllerItemSorteio;
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

    public AdapterItensSorteioPersonalizado(Context context, List<ItemSorteio> objects) {
        super(context, R.layout.activity_adapter_itens, objects);
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
            itemSorteioHolder.btnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final ItemSorteio itemSorteio = (ItemSorteio) v.getTag();
                    final EditText input = new EditText(getContext());
                    input.setInputType(InputType.TYPE_CLASS_TEXT);
                    input.setText(itemSorteio.getDescricao());

                    new AlertDialog.Builder(getContext())
                            .setTitle("Informe a descrição do item")
                            .setView(input)
                            .setPositiveButton(R.string.STR_OK, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String descricao = input.getText().toString();
                                    itemSorteio.setDescricao(descricao);
                                    ControllerItemSorteio.getInstance().salvarItem(itemSorteio);
                                    notifyDataSetChanged();
                                }
                            })
                            .setNegativeButton(R.string.STR_CANCEL, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            }).show();
                }
            });
            itemSorteioHolder.btnRemover.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final ItemSorteio itemSorteio = (ItemSorteio) v.getTag();
                    new AlertDialog.Builder(getContext())
                            .setTitle(R.string.STR_CONFIRME)
                            .setMessage(R.string.STR_CONFIRME_DELETE_ITEM)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton(R.string.STR_SIM, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    ControllerItemSorteio.getInstance().deletarItemSorteio(itemSorteio.getId());
                                    remove(itemSorteio);
                                }
                            })
                            .setNegativeButton(R.string.STR_NAO, null).show();
                }
            });

            v.setTag(itemSorteioHolder);
        }

        ItemSorteio itemSorteio = getItem(position);

        if (itemSorteio != null) {
            itemSorteioHolder.txtDescricao.setText(itemSorteio.getDescricao());
            itemSorteioHolder.btnEditar.setTag(itemSorteio);
            itemSorteioHolder.btnRemover.setTag(itemSorteio);
        }

        return v;
    }
}
