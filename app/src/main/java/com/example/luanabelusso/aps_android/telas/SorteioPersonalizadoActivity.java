package com.example.luanabelusso.aps_android.telas;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.luanabelusso.aps_android.R;
import com.example.luanabelusso.aps_android.banco.controllers.ControllerItemSorteio;
import com.example.luanabelusso.aps_android.banco.controllers.ControllerSorteio;
import com.example.luanabelusso.aps_android.entidades.ItemSorteio;
import com.example.luanabelusso.aps_android.telas.adapters.AdapterItensSorteioPersonalizado;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Luana Belusso on 08/10/2017.
 */

public class SorteioPersonalizadoActivity extends DefaultActivity {

    private AdapterItensSorteioPersonalizado adapter;
    private ListView list;
    private int tipoSorteio;
    private String descricaoSorteio;
    private int qtdItens;
    private int limMin;
    private int limMax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorteio_personalizado);

        final Bundle args = getIntent().getExtras();
        tipoSorteio = args.getInt("tipoSorteio");
        qtdItens = args.getInt("qtdItens");
        limMin = args.getInt("limMin");
        limMax = args.getInt("limMax");

        list = (ListView) findViewById(R.id.lvItens);
        adapter = new AdapterItensSorteioPersonalizado(this,
                ControllerSorteio.getInstance().getCurrentSorteio().getItensSorteio());

        list.setAdapter(adapter);

        setTipoSorteio();

        if (tipoSorteio == 6) {
            aleatorios();
        }
    }

    public void onBtnAdicionarClick(View v) {
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);

        new AlertDialog.Builder(this)
                .setTitle("Informe a descrição do novo item")
                .setView(input)
                .setPositiveButton(R.string.STR_OK, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String descricao = input.getText().toString();
                        ItemSorteio itemSorteio = new ItemSorteio();
                        ControllerSorteio.getInstance().getCurrentSorteio().getItensSorteio().add(itemSorteio);
                        itemSorteio.setSorteio(ControllerSorteio.getInstance().getCurrentSorteio().getId());
                        itemSorteio.setDescricao(descricao);
                        ControllerItemSorteio.getInstance().salvarItem(itemSorteio);
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton(R.string.STR_CANCEL, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).show();
    }

    public void setTipoSorteio() {
        TextView tv = (TextView) findViewById(R.id.tvTipoSorteio);

        switch (tipoSorteio) {
            case 1:
                descricaoSorteio = "Sortear Critério Automaticamente";
                break;
            case 2:
                descricaoSorteio = "Ordenar forma Crescente";
                break;
            case 3:
                descricaoSorteio = "Ordenar forma Decrescente";
                break;
            case 4:
                descricaoSorteio = "Sortear Números Pares";
                break;
            case 5:
                descricaoSorteio = "Sortear Números Ímpares";
                break;
            case 7:
                descricaoSorteio = "Sortear Item de uma Lista";
                break;
        }
        tv.setText(descricaoSorteio);
    }

    public void sortear(View v) {
//        ArrayList<String> list, listOrdenada;
//        list = arraylist;
//        listOrdenada = list;
//
//        switch (tipoSorteio) {
//            case 1:
//                listOrdenada = sortearCriterio(v, list);
//                break;
//            case 2:
//                listOrdenada = crescente(v, list);
//                break;
//            case 3:
//                listOrdenada = decrescente(v, list);
//                break;
//            case 4:
//                listOrdenada = par(v, list);
//                break;
//            case 5:
//                listOrdenada = impar(v, list);
//                break;
//            case 7:
//                listOrdenada = sortearItemLista(v, list);
//                break;
//    }

//        Intent intent = new Intent(this, ResultSorteioPersActivity.class);
//        Bundle params = new Bundle();
//        params.putStringArrayList("dados", listOrdenada);
//        params.putString("tipoSorteio", descricaoSorteio);
//        intent.putExtras(params);
//
//        startActivity(intent);

    }

    public ArrayList<String> sortearCriterio(View v, ArrayList<String> list) {
        int resultado;

        if (validaIsNumber(v, list)) {
            resultado = (int) (1 + (Math.random() * (4)));
        } else {
            resultado = (int) (3 + (Math.random() * (4)));
        }

        switch (resultado) {
            case 1:
                descricaoSorteio = "Sortear Números Pares";
                list = par(v, list);
                break;
            case 2:
                descricaoSorteio = "Sortear Númeors Ímpares";
                list = impar(v, list);
                break;
            case 3:
                descricaoSorteio = "Ordenar forma Crescente";
                list = crescente(v, list);
                break;
            default:
                descricaoSorteio = "Ordenar forma Decrescente";
                list = decrescente(v, list);
                break;
        }
        return list;
    }

    public ArrayList<String> crescente(View v, ArrayList<String> list) {

        ArrayList<Integer> result = new ArrayList<Integer>();
        ArrayList<String> listaFinal = new ArrayList<String>();

        String[] stockArr = new String[list.size()];
        stockArr = list.toArray(stockArr);

        Arrays.sort(stockArr);

        for (int i = 0; i < stockArr.length; i++) {
            listaFinal.add(stockArr[i]);
        }

        return listaFinal;
    }

    public ArrayList<String> decrescente(View v, ArrayList<String> list) {
        Collections.sort(list, Collections.reverseOrder());

        return list;
    }

    public ArrayList<String> par(View v, ArrayList<String> list) {

        ArrayList<Integer> result = new ArrayList<Integer>();
        ArrayList<Integer> resultPar = new ArrayList<Integer>();
        ArrayList<String> listaFinal = new ArrayList<String>();

        for (int i = 0; i < list.size(); i++) {
            result.add(Integer.parseInt(list.get(i)));
        }

        for (int n = 0; n < result.size(); n++) {

            if (result.get(n) % 2 == 0) {
                resultPar.add(result.get(n));
            }
        }

        for (int i = 0; i < resultPar.size(); i++) {
            listaFinal.add(resultPar.get(i).toString());
        }

        return listaFinal;

    }

    public ArrayList<String> impar(View v, ArrayList<String> list) {

        ArrayList<Integer> result = new ArrayList<Integer>();
        ArrayList<Integer> resultImpar = new ArrayList<Integer>();
        ArrayList<String> listaFinal = new ArrayList<String>();

        for (int i = 0; i < list.size(); i++) {
            result.add(Integer.parseInt(list.get(i)));
        }

        for (int n = 0; n < result.size(); n++) {

            if (result.get(n) % 2 != 0) {
                resultImpar.add(result.get(n));
            }
        }

        for (int i = 0; i < resultImpar.size(); i++) {
            listaFinal.add(resultImpar.get(i).toString());
        }

        return listaFinal;

    }

    public void aleatorios() {
        ArrayList<Integer> result = new ArrayList<Integer>();
        ArrayList<String> listaFinal = new ArrayList<String>();

        if (limMax > 0 && limMin > 0) {
            for (int n = 0; n < qtdItens; n++) {
                result.add((int) (limMin + (Math.random() * (limMax + 1 - limMin))));
            }
        } else {
            int i;
            double d;

            for (int n = 0; n < qtdItens; n++) {
                d = (Math.random() * 100);
                d = Math.round(d);

                i = (int) d;
                result.add(i);
            }
        }

        for (int i = 0; i < result.size(); i++) {
            listaFinal.add(result.get(i).toString());
        }

        Intent intent = new Intent(this, ResultSorteioPersActivity.class);
        Bundle params = new Bundle();
        params.putStringArrayList("dados", listaFinal);
        params.putString("tipoSorteio", descricaoSorteio);
        intent.putExtras(params);
        startActivity(intent);
    }

    public ArrayList<String> sortearItemLista(View v, ArrayList<String> list) {
        ArrayList<String> listaFinal = new ArrayList<String>();
        int tam = list.size();

        for (int n = 0; n < qtdItens; n++) {
            int i = (int) (0 + (Math.random() * (tam)));

            while (listaFinal.contains(list.get(i).toString())) {
                i = (int) (0 + (Math.random() * (tam)));
            }

            listaFinal.add(list.get(i).toString());
        }

        return listaFinal;
    }

    public boolean validaIsNumber(View v, ArrayList<String> list) {
        int number;

        try {
            for (int i = 0; i < list.size(); i++) {
                number = Integer.parseInt(list.get(i));
            }
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
