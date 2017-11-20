package com.example.luanabelusso.aps_android.entidades;

import android.content.ContentValues;

import java.io.Serializable;

/**
 * Created by Marciano on 19/11/2017.
 */

public abstract class DefaultEntity implements Serializable {
    public abstract String getScriptCreate();

    public abstract String[] getAllFields();

    public abstract ContentValues getUpdateValues();
}
