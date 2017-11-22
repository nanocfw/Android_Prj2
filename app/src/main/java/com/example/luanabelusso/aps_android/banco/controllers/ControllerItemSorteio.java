package com.example.luanabelusso.aps_android.banco.controllers;

import android.database.Cursor;

import com.example.luanabelusso.aps_android.banco.DataBase;

import java.util.ArrayList;
import java.util.List;

import com.example.luanabelusso.aps_android.entidades.ItemSorteio;

/**
 * Created by Marciano on 19/11/2017.
 */

public class ControllerItemSorteio extends DataBase {
    private static ControllerItemSorteio instance = null;

    public static ControllerItemSorteio getInstance() {
        if (instance == null)
            instance = new ControllerItemSorteio();
        return instance;
    }

    public boolean salvarItem(ItemSorteio itemSorteio) {
        return false;
    }

    public ItemSorteio selecionarItem(int id) {
        String where = ItemSorteio.ID + " = " + id;
        Cursor dados = selecionar(ItemSorteio.TABELA, where, new ItemSorteio().getAllFields());
        if (dados == null || dados.getCount() == 0)
            return null;

        ItemSorteio aux = new ItemSorteio();
        aux.setId(dados.getInt(dados.getColumnIndexOrThrow(ItemSorteio.ID)));
        aux.setSorteio(dados.getInt(dados.getColumnIndexOrThrow(ItemSorteio.SORTEIO)));
        aux.setDescricao(dados.getString(dados.getColumnIndexOrThrow(ItemSorteio.DESCRICAO)));
        return aux;
    }

    public List<ItemSorteio> getListaItens(int idSorteio) {
        List<ItemSorteio> aux = new ArrayList<>();
        String where = ItemSorteio.SORTEIO + " = " + idSorteio;
        Cursor dados = selecionar(ItemSorteio.TABELA, where, new ItemSorteio().getAllFields());
        if (dados == null || dados.getCount() == 0)
            return aux;
        try {
            while (dados.moveToNext()) {
                ItemSorteio itemSorteio = new ItemSorteio();
                itemSorteio.setId(dados.getInt(dados.getColumnIndexOrThrow(ItemSorteio.ID)));
                itemSorteio.setSorteio(dados.getInt(dados.getColumnIndexOrThrow(ItemSorteio.SORTEIO)));
                itemSorteio.setDescricao(dados.getString(dados.getColumnIndexOrThrow(ItemSorteio.DESCRICAO)));
                aux.add(itemSorteio);
            }
            return aux;
        } finally {
            dados.close();
        }

    }

    public boolean deletarItemSorteio(int id) {
        String where = ItemSorteio.ID + " = " + id;
        return delete(ItemSorteio.TABELA, where) > 0;
    }
}
