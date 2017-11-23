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
    public static final String DATA_SORTEIO = "data_sorteio";

    int id;
    int qntResultados;
    int vlMinimo;
    int vlMaximo;
    TipoCriterio tipoCriterio;
    Date dataSorteio;
    List<ItemSorteio> itensSorteio;
    List<ItemResultadoSorteio> itensResultado;

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
        values.put(Sorteio.DATA_SORTEIO, Util.dateTimeToStrSql(this.dataSorteio));
        return values;
    }
}
