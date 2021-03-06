package com.example.luanabelusso.aps_android.banco.controllers;

import android.database.Cursor;

import com.example.luanabelusso.aps_android.banco.DataBase;
import com.example.luanabelusso.aps_android.util.Util;

import com.example.luanabelusso.aps_android.entidades.ItemResultadoSorteio;
import com.example.luanabelusso.aps_android.entidades.ItemSorteio;
import com.example.luanabelusso.aps_android.entidades.Sorteio;
import com.example.luanabelusso.aps_android.entidades.enums.TipoCriterio;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marciano on 19/11/2017.
 */

public class ControllerSorteio extends DataBase {
    private static ControllerSorteio instance = null;
    private Sorteio currentSorteio = null;

    public static ControllerSorteio getInstance() {
        if (instance == null)
            instance = new ControllerSorteio();
        return instance;
    }

    public Sorteio getCurrentSorteio() {
        return currentSorteio;
    }

    public void setCurrentSorteio(Sorteio currentSorteio) {
        this.currentSorteio = currentSorteio;
    }

    public boolean salvarSorteio(Sorteio sorteio) {
        Boolean result = false;


        if (sorteio.getId() > 0) {
            String where = Sorteio.ID + " = " + sorteio.getId();
            result = atualizaDados(Sorteio.TABELA, where, sorteio.getUpdateValues()) > 0;
        } else {
            sorteio.setId(insereDados(Sorteio.TABELA, sorteio.getUpdateValues()));
            result = sorteio.getId() > 0;
        }
        if (result) {
            for (ItemSorteio itemSorteio : sorteio.getItensSorteio()) {
                itemSorteio.setSorteio(sorteio.getId());
                ControllerItemSorteio.getInstance().salvarItem(itemSorteio);
            }

            for (ItemResultadoSorteio itemResultadoSorteio : sorteio.getItensResultado()) {
                itemResultadoSorteio.setSorteio(sorteio.getId());
                ControllerItemResultadoSorteio.getInstance().salvarItemResultado(itemResultadoSorteio);
            }

        }
        return result;
    }

    public Sorteio selecionarSorteio(int id) {
        String where = Sorteio.ID + " = " + id;
        Cursor dados = selecionar(Sorteio.TABELA, where, new Sorteio().getAllFields());
        if (dados == null || dados.getCount() == 0)
            return null;

        Sorteio aux = new Sorteio();
        aux.setId(dados.getInt(dados.getColumnIndexOrThrow(Sorteio.ID)));
        aux.setQntResultados(dados.getInt(dados.getColumnIndexOrThrow(Sorteio.QNT_RESULTADOS)));
        aux.setVlMinimo(dados.getInt(dados.getColumnIndexOrThrow(Sorteio.VL_MINIMO)));
        aux.setVlMaximo(dados.getInt(dados.getColumnIndexOrThrow(Sorteio.VL_MAXIMO)));
        aux.setTipoCriterio(TipoCriterio.values()[dados.getInt(dados.getColumnIndexOrThrow(Sorteio.TIPO_CRITERIO))]);
        aux.setDataSorteio(Util.strToDateTime(dados.getString(dados.getColumnIndexOrThrow(Sorteio.DATA_SORTEIO))));

        aux.setItensSorteio(ControllerItemSorteio.getInstance().getListaItens(aux.getId()));
        aux.setItensResultado(ControllerItemResultadoSorteio.getInstance().getListaItensResultado(aux.getId()));
        return aux;
    }

    public boolean deletarSorteio(int id) {
        String where = Sorteio.ID + " = " + id;
        return delete(Sorteio.TABELA, where) > 0;
    }

    public List<Sorteio> listarSorteios() {
        List<Sorteio> aux = new ArrayList<>();

        Cursor dados = selecionar(Sorteio.TABELA, "", new Sorteio().getAllFields());
        if (dados == null || dados.getCount() == 0)
            return aux;
        do {
            Sorteio sorteio = new Sorteio();
            sorteio.setId(dados.getInt(dados.getColumnIndexOrThrow(Sorteio.ID)));
            sorteio.setQntResultados(dados.getInt(dados.getColumnIndexOrThrow(Sorteio.QNT_RESULTADOS)));
            sorteio.setVlMinimo(dados.getInt(dados.getColumnIndexOrThrow(Sorteio.VL_MINIMO)));
            sorteio.setVlMaximo(dados.getInt(dados.getColumnIndexOrThrow(Sorteio.VL_MAXIMO)));
            sorteio.setTipoCriterio(TipoCriterio.values()[dados.getInt(dados.getColumnIndexOrThrow(Sorteio.TIPO_CRITERIO))]);
            sorteio.setDataSorteio(Util.strToDateTime(dados.getString(dados.getColumnIndexOrThrow(Sorteio.DATA_SORTEIO))));

            sorteio.setItensSorteio(ControllerItemSorteio.getInstance().getListaItens(sorteio.getId()));
            sorteio.setItensResultado(ControllerItemResultadoSorteio.getInstance().getListaItensResultado(sorteio.getId()));
            aux.add(sorteio);
        } while (dados.moveToNext());
        return aux;
    }
}
