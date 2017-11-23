package com.example.luanabelusso.aps_android.telas;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.luanabelusso.aps_android.R;
import com.example.luanabelusso.aps_android.banco.controllers.ControllerSorteio;
import com.example.luanabelusso.aps_android.entidades.ItemResultadoSorteio;
import com.example.luanabelusso.aps_android.entidades.ItemSorteio;
import com.example.luanabelusso.aps_android.entidades.Sorteio;
import com.example.luanabelusso.aps_android.entidades.enums.TipoCriterio;
import com.example.luanabelusso.aps_android.telas.adapters.AdapterItensSorteioPersonalizado;
import com.example.luanabelusso.aps_android.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Luana Belusso on 08/10/2017.
 */

public class SorteioPersonalizadoActivity extends DefaultActivity {

    private AdapterItensSorteioPersonalizado adapter;
    private Sorteio sorteio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorteio_personalizado);
        sorteio = ControllerSorteio.getInstance().getCurrentSorteio();

        ListView list = findViewById(R.id.lvItens);
        adapter = new AdapterItensSorteioPersonalizado(this, sorteio.getItensSorteio());

        list.setAdapter(adapter);

        TextView tipoSorteio = findViewById(R.id.tvTipoSorteio);
        tipoSorteio.setText(sorteio.getTipoCriterio().toString());
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

    public void onBtnSorteioPersonalizadoClick(View v) {
        if (sorteio.getItensSorteio().size() < 2) {
            alert("Lista deve possuir mais do que um item.");
            return;
        }

        if (sorteio.getTipoCriterio() == TipoCriterio.ITEM_LISTA && sorteio.getItensSorteio().size() < sorteio.getQntResultados()) {
            alert("Não há itens suficientes para sortear.");
            return;
        }

        switch (sorteio.getTipoCriterio()) {
            case ITEM_LISTA:
                sortearItemLista();
                break;
            case CRITERIO_AUTOMATICO:
                sortearCriterio();
                break;
            case ORDEM_CRESCENTE:
                crescente();
                break;
            case ORDEM_DECRESCENTE:
                decrescente();
                break;
            case NUMEROS_PARES:
                par();
                break;
            case NUMEROS_IMPARES:
                impar();
                break;
        }

        ControllerSorteio.getInstance().salvarSorteio(sorteio);
        Intent intent = new Intent(this, ResultSorteioPersActivity.class);
        startActivity(intent);

        Intent returnIntent = new Intent();
        returnIntent.putExtra("retorno", 1);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    public boolean onlyNumbersInItems() {
        for (ItemSorteio item : sorteio.getItensSorteio()) {
            if (Util.isInteger(item.getDescricao()))
                return false;
        }
        return true;
    }

    public void sortearCriterio() {
        sorteio.setCriterioAleatorio(true);
        TipoCriterio resultado;

        if (onlyNumbersInItems()) {
            resultado = TipoCriterio.values()[(int) (1 + (Math.random() * (4)))];
        } else {
            resultado = TipoCriterio.values()[(int) (3 + (Math.random() * (4)))];
        }

        switch (resultado) {
            case ORDEM_CRESCENTE:
                crescente();
                break;
            case ORDEM_DECRESCENTE:
                decrescente();
                break;
            case NUMEROS_PARES:
                par();
                break;
            case NUMEROS_IMPARES:
                impar();
                break;
            default:
                alert("Erro");
        }
    }

    public void crescente() {
        List<ItemSorteio> aux = new ArrayList<>();
        aux.addAll(sorteio.getItensSorteio());
        Collections.sort(aux, new Comparator<ItemSorteio>() {
            @Override
            public int compare(ItemSorteio o1, ItemSorteio o2) {
                return o1.getDescricao().compareTo(o2.getDescricao());
            }
        });

        for (int i = 0; i < aux.size(); i++)
            sorteio.getItensResultado().add(new ItemResultadoSorteio(sorteio.getItensSorteio().indexOf(aux.get(i))));
    }

    public void decrescente() {
        List<ItemSorteio> aux = new ArrayList<>();
        aux.addAll(sorteio.getItensSorteio());
        Collections.sort(aux, new Comparator<ItemSorteio>() {
            @Override
            public int compare(ItemSorteio o1, ItemSorteio o2) {
                return o2.getDescricao().compareTo(o1.getDescricao());
            }
        });

        for (int i = 0; i < aux.size(); i++)
            sorteio.getItensResultado().add(new ItemResultadoSorteio(sorteio.getItensSorteio().indexOf(aux.get(i))));
    }

    public void par() {
        for (int i = 0; i < sorteio.getItensSorteio().size(); i++)
            if (Util.parseIntDef(sorteio.getItensSorteio().get(i).getDescricao(), 0) % 2 == 0)
                sorteio.getItensResultado().add(new ItemResultadoSorteio(i));
    }

    public void impar() {
        for (int i = 0; i < sorteio.getItensSorteio().size(); i++)
            if (Util.parseIntDef(sorteio.getItensSorteio().get(i).getDescricao(), 0) % 2 != 0)
                sorteio.getItensResultado().add(new ItemResultadoSorteio(i));
    }

    public void sortearItemLista() {
        List<Integer> aux = Util.sorteio(0, sorteio.getItensSorteio().size() - 1, sorteio.getQntResultados());
        for (Integer resultado : aux)
            sorteio.getItensResultado().add(new ItemResultadoSorteio(resultado));
    }
}
