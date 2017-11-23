package com.example.luanabelusso.aps_android.entidades.enums;

/**
 * Created by Marciano on 19/11/2017.
 */

public enum TipoCriterio {
    NUMEROS_ALEATORIOS, ITEM_LISTA, CRITERIO_AUTOMATICO, ORDEM_CRESCENTE, ORDEM_DECRESCENTE, NUMEROS_PARES, NUMEROS_IMPARES;

    @Override
    public String toString() {
        return asString(this);
    }

    public static String asString(TipoCriterio c) {
        switch (c) {
            case NUMEROS_ALEATORIOS:
                return "Números Aleatórios";
            case ITEM_LISTA:
                return "Item da Lista";
            case CRITERIO_AUTOMATICO:
                return "Automático";
            case ORDEM_CRESCENTE:
                return "Ordem Crescente";
            case ORDEM_DECRESCENTE:
                return "Ordem Decrescente";
            case NUMEROS_PARES:
                return "Números Pares";
            case NUMEROS_IMPARES:
                return "Números Ímpares";
            default:
                return "Inválido";
        }
    }

    public static String[] getAll() {
        TipoCriterio[] c = values();
        String[] aux = new String[c.length];

        for (int i = 0; i < c.length; i++)
            aux[i] = asString(c[i]);

        return aux;
    }
}
