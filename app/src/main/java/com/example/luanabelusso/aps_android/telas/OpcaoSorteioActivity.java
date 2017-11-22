package com.example.luanabelusso.aps_android.telas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.graphics.Color;

import com.example.luanabelusso.aps_android.R;
import com.example.luanabelusso.aps_android.entidades.enums.TipoCriterio;

/**
 * Created by Luana Belusso on 08/10/2017.
 */

public class OpcaoSorteioActivity extends DefaultActivity {

    private RadioGroup rgOpcoes;
    private Button btnAcao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opcao_sorteio);

        rgOpcoes = (RadioGroup) findViewById(R.id.rgGrupo);
        btnAcao = (Button) findViewById(R.id.btnsorteio);

        // Alterar descrição do botão conforme item selecionado
        rgOpcoes.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rbSelecionado = findViewById(rgOpcoes.getCheckedRadioButtonId());

                if (Integer.parseInt((String) rbSelecionado.getTag()) == 0)
                    btnAcao.setHint("Sortear");
                else
                    btnAcao.setHint("Criar Lista");

                btnAcao.setTextColor(Color.parseColor("#FFFFFF"));
            }
        });
    }

    public void sortear(View view) {
        EditText edtQtdItens = (EditText) findViewById(R.id.edtQtdItens);
        EditText edtLimMin = (EditText) findViewById(R.id.edtminimo);
        EditText edtLimMax = (EditText) findViewById(R.id.edtmaximo);
        RadioButton rbSelecionado = findViewById(rgOpcoes.getCheckedRadioButtonId());

        Intent intent = new Intent(this, SorteioPersonalizadoActivity.class);
        Bundle params = new Bundle();
        params.putInt("tipoSorteio", Integer.parseInt((String) rbSelecionado.getTag()));

        if (edtQtdItens.getText().length() == 0) {
            params.putInt("qtdItens", 1);
        } else if (Integer.parseInt(edtQtdItens.getText().toString()) > 0) {
            params.putInt("qtdItens", Integer.parseInt(edtQtdItens.getText().toString()));
        }

        if (edtLimMin.getText().length() == 0 || edtLimMin.getText().length() == 0) {
            params.putInt("limMin", 0);
            params.putInt("limMax", 0);
        } else if (Integer.parseInt(edtLimMin.getText().toString()) > 0 && Integer.parseInt(edtLimMax.getText().toString()) > 0) {
            params.putInt("limMin", Integer.parseInt(edtLimMin.getText().toString()));
            params.putInt("limMax", Integer.parseInt(edtLimMax.getText().toString()));
        }

        intent.putExtras(params);
        startActivity(intent);
    }
}

