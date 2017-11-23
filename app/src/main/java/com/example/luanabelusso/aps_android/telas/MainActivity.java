package com.example.luanabelusso.aps_android.telas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.luanabelusso.aps_android.R;
import com.example.luanabelusso.aps_android.banco.controllers.ControllerSorteio;
import com.example.luanabelusso.aps_android.entidades.ItemResultadoSorteio;
import com.example.luanabelusso.aps_android.entidades.Sorteio;
import com.example.luanabelusso.aps_android.entidades.enums.TipoCriterio;
import com.example.luanabelusso.aps_android.util.Util;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.historico) {
            Intent intent = new Intent(this, HistoricoActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onBtnSorteioRapidoClick(View view) {
        TextView txtResultado = findViewById(R.id.edtresultado);
        EditText edtMin = findViewById(R.id.edtminimo);
        EditText edtMax = findViewById(R.id.edtmaximo);

        int min = Util.parseIntDef(edtMin.getText().toString(), 0);
        int max = Util.parseIntDef(edtMax.getText().toString(), 0);

        int resultado = Util.sorteio(min, max);
        txtResultado.setText(String.valueOf(resultado));
        Sorteio sorteio = new Sorteio();
        sorteio.setQntResultados(1);
        sorteio.setVlMinimo(min);
        sorteio.setVlMaximo(max);
        sorteio.setTipoCriterio(TipoCriterio.NUMEROS_ALEATORIOS);
        sorteio.getItensResultado().add(new ItemResultadoSorteio(resultado));
        ControllerSorteio.getInstance().salvarSorteio(sorteio);
    }

    public void onBtnNovoSorteioPersonalizadoClick(View view) {
        Sorteio sorteio = new Sorteio();
        ControllerSorteio.getInstance().setCurrentSorteio(sorteio);
        Intent intent = new Intent(this, OpcaoSorteioActivity.class);
        startActivity(intent);
    }
}
