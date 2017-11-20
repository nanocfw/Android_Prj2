package com.example.luanabelusso.aps_android.entidades;

import android.content.ContentValues;

import lombok.Data;

/**
 * Created by Marciano on 19/11/2017.
 */

@Data
public class ItemSorteio extends DefaultEntity {
    public static final String TABELA = "ITEM_SORTEIO";
    public static final String ID = "_id";
    public static final String SORTEIO = "sorteio_id";
    public static final String DESCRICAO = "descricao";

    private int id;
    private int sorteio;
    private String descricao;

    @Override
    public String getScriptCreate() {
        return "CREATE TABLE " + TABELA + "(" +
                ID + " integer primary key autoincrement, " +
                SORTEIO + " integer, " +
                DESCRICAO + " text " +
                ")";
    }

    @Override
    public String[] getAllFields() {
        return new String[]{
                ID,
                SORTEIO,
                DESCRICAO
        };
    }

    @Override
    public ContentValues getUpdateValues() {
        ContentValues values = new ContentValues();
        values.put(ItemSorteio.SORTEIO, this.sorteio);
        values.put(ItemSorteio.DESCRICAO, this.descricao);
        return values;
    }
}
