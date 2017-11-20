package com.example.luanabelusso.aps_android.telas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.luanabelusso.aps_android.R;

import java.util.ArrayList;

public class ResultSorteioPersActivity  extends DefaultActivity {

    private ArrayList<String> dados;
    private String descricaoSorteio;
    private ListView list;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_sorteio_pers);

        Bundle args = getIntent().getExtras();
        dados = args.getStringArrayList("dados");
        descricaoSorteio = args.getString("tipoSorteio");

        list = (ListView) findViewById(R.id.listView2);

        adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.activity_adapter_result_pers,R.id.tv, dados);
        list.setAdapter(adapter);

        TextView tvResult = (TextView) findViewById(R.id.tvResultado);
        tvResult.setText(descricaoSorteio);
    }

    public void novoSorteio(View v){
        adapter.clear();

        Intent intent = new Intent(this, OpcaoSorteioActivity.class);
        startActivity(intent);
    }
}
