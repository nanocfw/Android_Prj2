package com.example.luanabelusso.aps_android.entidades;

import android.content.ContentValues;

import lombok.Data;

/**
 * Created by Marciano on 19/11/2017.
 */
@Data
public class ItemResultadoSorteio extends DefaultEntity {
    public static final String TABELA = "ITEM_RESULTADO_SORTEIO";
    public static final String ID = "_id";
    public static final String SORTEIO = "sorteio_id";
    public static final String RESULTADO = "resultado";

    private int id;
    private int sorteio;
    private int resultado;// quando for item da lista, o resultado será o index, caso seja números, será o numero sorteado

    public ItemResultadoSorteio() {
        super();
    }

    public ItemResultadoSorteio(int resultado) {
        this.resultado = resultado;
    }

    @Override
    public String getScriptCreate() {
        return "CREATE TABLE " + TABELA + "(" +
                ID + " integer primary key autoincrement, " +
                SORTEIO + " integer, " +
                RESULTADO + " integer " +
                ")";
    }

    @Override
    public String[] getAllFields() {
        return new String[]{
                ID,
                SORTEIO,
                RESULTADO
        };
    }

    @Override
    public ContentValues getUpdateValues() {
        ContentValues values = new ContentValues();
        values.put(ItemResultadoSorteio.SORTEIO, this.sorteio);
        values.put(ItemResultadoSorteio.RESULTADO, this.resultado);
        return values;
    }
}
