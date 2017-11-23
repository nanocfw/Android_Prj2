package com.example.luanabelusso.aps_android.telas;


import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.luanabelusso.aps_android.R;
import com.example.luanabelusso.aps_android.banco.controllers.ControllerSorteio;
import com.example.luanabelusso.aps_android.entidades.Sorteio;

import java.util.List;

public class VisualizarSorteioActivity extends DefaultActivity {

    List<String> itensSorteio;
    List<String> itensSorteados;
    ArrayAdapter<String> adapterSorteio;
    ArrayAdapter<String> adapterResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_sorteio);
        Sorteio sorteio = ControllerSorteio.getInstance().getCurrentSorteio();

        itensSorteio = sorteio.getStringItens();
        adapterSorteio = new ArrayAdapter<>(getApplicationContext(), R.layout.activity_adapter_result_pers, R.id.tv, itensSorteio);
        ListView lv = findViewById(R.id.lvItensSorteio);
        lv.setAdapter(adapterSorteio);

        itensSorteados = sorteio.getStringItensResultado();
        adapterResultado = new ArrayAdapter<>(getApplicationContext(), R.layout.activity_adapter_result_pers, R.id.tv, itensSorteados);
        lv = findViewById(R.id.lvItensSorteados);
        lv.setAdapter(adapterResultado);

        TextView tv = findViewById(R.id.txtSorteio);
        tv.setText(getString(R.string.VISUALIZAR) + " " + sorteio.getId());
    }
}
