package com.example.luanabelusso.aps_android.entidades;

import android.content.ContentValues;

import com.example.luanabelusso.aps_android.util.Util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.luanabelusso.aps_android.entidades.enums.TipoCriterio;

import lombok.Data;

/**
 * Created by Marciano on 19/11/2017.
 */

@Data
public class Sorteio extends DefaultEntity {
    public static final String TABELA = "SORTEIO";
    public static final String ID = "_id";
    public static final String QNT_RESULTADOS = "qnt_resultados";
    public static final String VL_MINIMO = "vl_minimo";
    public static final String VL_MAXIMO = "vl_maximo";
    public static final String TIPO_CRITERIO = "tipo_criterio";
    public static final String CRITERIO_ALEATORIO = "criterio_aleatorio";
    public static final String DATA_SORTEIO = "data_sorteio";

    private int id;
    private int qntResultados;
    private int vlMinimo;
    private int vlMaximo;
    private TipoCriterio tipoCriterio;
    private boolean criterioAleatorio;
    private Date dataSorteio;
    private List<ItemSorteio> itensSorteio;
    private List<ItemResultadoSorteio> itensResultado;

    public Sorteio() {
        super();
        this.tipoCriterio = TipoCriterio.NUMEROS_ALEATORIOS;
        this.dataSorteio = new Date();
        this.itensSorteio = new ArrayList<>();
        this.itensResultado = new ArrayList<>();
    }

    @Override
    public String getScriptCreate() {
        return "CREATE TABLE " + TABELA + "(" +
                ID + " integer primary key autoincrement, " +
                QNT_RESULTADOS + " integer, " +
                VL_MINIMO + " integer, " +
                VL_MAXIMO + " integer, " +
                TIPO_CRITERIO + " smallint, " +
                CRITERIO_ALEATORIO + " boolean, " +
                DATA_SORTEIO + " datetime " +
                ")";
    }

    @Override
    public String[] getAllFields() {
        return new String[]{
                ID,
                QNT_RESULTADOS,
                VL_MINIMO,
                VL_MAXIMO,
                TIPO_CRITERIO,
                CRITERIO_ALEATORIO,
                DATA_SORTEIO
        };
    }

    @Override
    public ContentValues getUpdateValues() {
        ContentValues values = new ContentValues();
        values.put(Sorteio.QNT_RESULTADOS, this.qntResultados);
        values.put(Sorteio.VL_MINIMO, this.vlMinimo);
        values.put(Sorteio.VL_MAXIMO, this.vlMaximo);
        values.put(Sorteio.TIPO_CRITERIO, this.tipoCriterio.ordinal());
        values.put(Sorteio.CRITERIO_ALEATORIO, this.criterioAleatorio);
        values.put(Sorteio.DATA_SORTEIO, Util.dateTimeToStrSql(this.dataSorteio));
        return values;
    }

    public ArrayList<String> getStringItensResultado() {
        ArrayList<String> aux = new ArrayList<>();
        int r;
        for (int i = 0; i < itensResultado.size(); i++) {
            r = itensResultado.get(i).getResultado();
            if (tipoCriterio == TipoCriterio.NUMEROS_ALEATORIOS)
                aux.add(String.valueOf(r));
            else
                aux.add(itensSorteio.get(r).getDescricao());
        }
        return aux;
    }

    public ArrayList<String> getStringItens() {
        ArrayList<String> aux = new ArrayList<>();
        if (tipoCriterio == TipoCriterio.NUMEROS_ALEATORIOS)
            return aux;

        for (int i = 0; i < itensSorteio.size(); i++)
            aux.add(itensSorteio.get(i).getDescricao());

        return aux;
    }
}
