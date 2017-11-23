package com.example.luanabelusso.aps_android.telas;


import android.os.Bundle;
import android.widget.ListView;

import com.example.luanabelusso.aps_android.R;
import com.example.luanabelusso.aps_android.banco.controllers.ControllerSorteio;
import com.example.luanabelusso.aps_android.telas.adapters.AdapterItensHistorico;

public class HistoricoActivity extends DefaultActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);
        ListView lv = findViewById(R.id.lvHistoricoSorteio);
        AdapterItensHistorico adapter = new AdapterItensHistorico(this,
                ControllerSorteio.getInstance().listarSorteios());
        lv.setAdapter(adapter);
    }
}
