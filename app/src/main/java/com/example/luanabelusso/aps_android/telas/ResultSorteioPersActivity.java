package com.example.luanabelusso.aps_android.telas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.luanabelusso.aps_android.R;
import com.example.luanabelusso.aps_android.banco.controllers.ControllerSorteio;

import java.util.ArrayList;

public class ResultSorteioPersActivity extends DefaultActivity {

    private ArrayList<String> dados;
    private ListView list;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_sorteio_pers);

        dados = ControllerSorteio.getInstance().getCurrentSorteio().getStringItensResultado();

        list = findViewById(R.id.lvItensResultado);
        adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.activity_adapter_result_pers, R.id.tv, dados);
        list.setAdapter(adapter);

        TextView tvResult = findViewById(R.id.tvResultado);
        tvResult.setText(getString(R.string.RESULTADO) + " " +
                ControllerSorteio.getInstance().getCurrentSorteio().getTipoCriterio().toString());
    }

    public void novoSorteio(View v) {
        finish();
    }
}
