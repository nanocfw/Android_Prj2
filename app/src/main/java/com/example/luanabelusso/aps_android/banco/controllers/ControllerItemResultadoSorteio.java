package com.example.luanabelusso.aps_android.banco.controllers;

import com.example.luanabelusso.aps_android.banco.DataBase;

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
        return null;
    }

    public List<ItemResultadoSorteio> getListaItensResultado(int id) {
        return null;
    }

    public boolean deletarItemResultado(int id) {
        return false;
    }
}
