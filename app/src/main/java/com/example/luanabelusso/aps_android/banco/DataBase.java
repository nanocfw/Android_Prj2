package com.example.luanabelusso.aps_android.banco;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.luanabelusso.aps_android.FastSort;

import java.util.ArrayList;
import java.util.List;

import com.example.luanabelusso.aps_android.entidades.DefaultEntity;
import com.example.luanabelusso.aps_android.entidades.ItemResultadoSorteio;
import com.example.luanabelusso.aps_android.entidades.ItemSorteio;
import com.example.luanabelusso.aps_android.entidades.Sorteio;

/**
 * Created by Marciano on 16/11/2017.
 */

public class DataBase extends SQLiteOpenHelper {
    private static final String NOME_BANCO = "sorteio.db";
    private static final int VERSAO = 1;
    private static List<DefaultEntity> listaTabelas;

    public DataBase() {
        super(FastSort.getContext(), NOME_BANCO, null, VERSAO);
        listaTabelas = new ArrayList<>();
        listaTabelas.add(new Sorteio());
        listaTabelas.add(new ItemSorteio());
        listaTabelas.add(new ItemResultadoSorteio());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for (DefaultEntity tabela : listaTabelas)
            db.execSQL(tabela.getScriptCreate());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    protected int insereDados(String tabela, ContentValues valores) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            return (int) db.insert(tabela, null, valores);
        } catch (Exception e) {
            return 0;
        } finally {
            db.close();
        }
    }

    protected int atualizaDados(String tabela, String where, ContentValues valores) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            return db.update(tabela, valores, where, null);
        } finally {
            db.close();
        }
    }

    protected Cursor selecionar(String tabela, String where, String[] campos) {
        Cursor cursor;
        SQLiteDatabase db = getReadableDatabase();
        cursor = db.query(tabela, campos, where, null, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        db.close();
        return cursor;
    }

    protected int delete(String tabela, String where) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            return db.delete(tabela, where, null);
        } finally {
            db.close();
        }
    }
}
