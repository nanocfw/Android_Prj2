package com.example.luanabelusso.aps_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.graphics.Color;

/**
 * Created by Luana Belusso on 08/10/2017.
 */

public class OpcaoSorteioActivity extends AppCompatActivity {

    private RadioGroup rgOpcoes;
    private Button btnAcao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opcaosorteio);

        rgOpcoes = (RadioGroup) findViewById(R.id.rgGrupo);
        btnAcao = (Button) findViewById(R.id.btnsorteio);

        // Alterar descrição do botão conforme item selecionado
        rgOpcoes.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                RadioButton rbOpAleatorio = (RadioButton) findViewById(R.id.rbAleatorio);

                if(rbOpAleatorio.isChecked()){
                    btnAcao.setHint("Sortear");
                }else{
                    btnAcao.setHint("Criar Lista");
                }
                btnAcao.setTextColor(Color.parseColor("#FFFFFF"));
            }
        });
    }

    public void sortear(View view){
        int tipoSorteio=0;

        RadioButton rbOpSortearCriterio = (RadioButton) findViewById(R.id.rbSortearCriterio);
        RadioButton rbOpCrescente = (RadioButton) findViewById(R.id.rbCrescente);
        RadioButton rbOpDecrescente = (RadioButton) findViewById(R.id.rbDecrescente);
        RadioButton rbOpPar = (RadioButton) findViewById(R.id.rbPar);
        RadioButton rbOpImpar = (RadioButton) findViewById(R.id.rbImpar);
        RadioButton rbOpAleatorio = (RadioButton) findViewById(R.id.rbAleatorio);
        RadioButton rbOpItemLista = (RadioButton) findViewById(R.id.rbSortearItemLista);

        EditText edtQtdItens = (EditText) findViewById(R.id.edtQtdItens);
        EditText edtLimMin = (EditText) findViewById(R.id.edtminimo);
        EditText edtLimMax = (EditText) findViewById(R.id.edtmaximo);

        if(rbOpSortearCriterio.isChecked()) {
            tipoSorteio = 1;
        }else if(rbOpCrescente.isChecked()) {
            tipoSorteio = 2;
        }else if(rbOpDecrescente.isChecked()) {
            tipoSorteio = 3;
        }else if(rbOpPar.isChecked()) {
            tipoSorteio = 4;
        }else if(rbOpImpar.isChecked()) {
            tipoSorteio = 5;
        }else if(rbOpAleatorio.isChecked()) {
            tipoSorteio = 6;
        }else if(rbOpItemLista.isChecked()) {
            tipoSorteio = 7;
        }

        Intent intent = new Intent(this, SorteioPersonalizadoActivity.class);
        Bundle params = new Bundle();
        params.putInt("tipoSorteio", tipoSorteio);

        if(edtQtdItens.getText().length() == 0) {
            params.putInt("qtdItens", 1);
        }else if (Integer.parseInt(edtQtdItens.getText().toString()) > 0) {
            params.putInt("qtdItens", Integer.parseInt(edtQtdItens.getText().toString()));
        }

        if(edtLimMin.getText().length() == 0 || edtLimMin.getText().length() == 0) {
            params.putInt("limMin", 0);
            params.putInt("limMax", 0);
        }else if (Integer.parseInt(edtLimMin.getText().toString()) > 0 && Integer.parseInt(edtLimMax.getText().toString()) > 0 ) {
            params.putInt("limMin", Integer.parseInt(edtLimMin.getText().toString()));
            params.putInt("limMax", Integer.parseInt(edtLimMax.getText().toString()));
        }

        intent.putExtras(params);
        startActivity(intent);
    }
}

