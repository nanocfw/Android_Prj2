package com.example.luanabelusso.aps_android.banco.controllers;

import com.example.luanabelusso.aps_android.banco.DataBase;

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

    public List<ItemSorteio> getListaItens(int id) {
        return null;
    }
}
