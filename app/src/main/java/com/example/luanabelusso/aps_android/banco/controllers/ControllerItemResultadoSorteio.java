package com.example.luanabelusso.aps_android.banco.controllers;

import android.database.Cursor;

import com.example.luanabelusso.aps_android.banco.DataBase;

import java.util.ArrayList;
import java.util.List;

import com.example.luanabelusso.aps_android.entidades.ItemResultadoSorteio;

/**
 * Created by Marciano on 19/11/2017.
 */

public class ControllerItemResultadoSorteio extends DataBase {
    private static ControllerItemResultadoSorteio instance = null;

    public static ControllerItemResultadoSorteio getInstance() {
        if (instance == null)
            instance = new ControllerItemResultadoSorteio();
        return instance;
    }

    public boolean salvarItemResultado(ItemResultadoSorteio itemResultadoSorteio) {
        return false;
    }

    public ItemResultadoSorteio selecionarItemResultado(int id) {
        String where = ItemResultadoSorteio.ID + " = " + id;
        Cursor dados = selecionar(ItemResultadoSorteio.TABELA, where, new ItemResultadoSorteio().getAllFields());
        if (dados == null || dados.getCount() == 0)
            return null;

        ItemResultadoSorteio aux = new ItemResultadoSorteio();
        aux.setId(dados.getInt(dados.getColumnIndexOrThrow(ItemResultadoSorteio.ID)));
        aux.setSorteio(dados.getInt(dados.getColumnIndexOrThrow(ItemResultadoSorteio.SORTEIO)));
        aux.setResultado(dados.getInt(dados.getColumnIndexOrThrow(ItemResultadoSorteio.RESULTADO)));
        return aux;
    }

    public List<ItemResultadoSorteio> getListaItensResultado(int idSorteio) {
        List<ItemResultadoSorteio> aux = new ArrayList<>();
        String where = ItemResultadoSorteio.SORTEIO + " = " + idSorteio;
        Cursor dados = selecionar(ItemResultadoSorteio.TABELA, where, new ItemResultadoSorteio().getAllFields());
        if (dados == null || dados.getCount() == 0)
            return aux;
        try {
            while (dados.moveToNext()) {
                ItemResultadoSorteio itemResultadoSorteio = new ItemResultadoSorteio();
                itemResultadoSorteio.setId(dados.getInt(dados.getColumnIndexOrThrow(ItemResultadoSorteio.ID)));
                itemResultadoSorteio.setSorteio(dados.getInt(dados.getColumnIndexOrThrow(ItemResultadoSorteio.SORTEIO)));
                itemResultadoSorteio.setResultado(dados.getInt(dados.getColumnIndexOrThrow(ItemResultadoSorteio.RESULTADO)));
                aux.add(itemResultadoSorteio);
            }
            return aux;
        } finally {
            dados.close();
        }
    }

    public boolean deletarItemResultado(int id) {
        String where = ItemResultadoSorteio.ID + " = " + id;
        return delete(ItemResultadoSorteio.TABELA, where) > 0;
    }
}
