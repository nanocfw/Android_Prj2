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
import com.example.luanabelusso.aps_android.banco.controllers.ControllerSorteio;
import com.example.luanabelusso.aps_android.entidades.ItemResultadoSorteio;
import com.example.luanabelusso.aps_android.entidades.Sorteio;
import com.example.luanabelusso.aps_android.entidades.enums.TipoCriterio;
import com.example.luanabelusso.aps_android.util.Util;

import java.util.List;

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

        rgOpcoes = findViewById(R.id.rgGrupo);
        btnAcao = findViewById(R.id.btnsorteio);

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
        EditText edtQtdItens = findViewById(R.id.edtQtdItens);
        EditText edtLimMin = findViewById(R.id.edtminimo);
        EditText edtLimMax = findViewById(R.id.edtmaximo);
        RadioButton rbSelecionado = findViewById(rgOpcoes.getCheckedRadioButtonId());

        Sorteio sorteio = ControllerSorteio.getInstance().getCurrentSorteio();
        sorteio.setTipoCriterio(TipoCriterio.values()[Integer.parseInt((String) rbSelecionado.getTag())]);
        sorteio.setVlMinimo(Util.parseIntDef(edtLimMin.getText().toString(), 0));
        sorteio.setVlMaximo(Util.parseIntDef(edtLimMax.getText().toString(), 0));
        sorteio.setQntResultados(Util.parseIntDef(edtQtdItens.getText().toString(), 0));

        if (sorteio.getTipoCriterio() == TipoCriterio.NUMEROS_ALEATORIOS) {
            List<Integer> aux = Util.sorteio(sorteio.getVlMinimo(), sorteio.getVlMaximo(), sorteio.getQntResultados());

            for (Integer resultado : aux)
                sorteio.getItensResultado().add(new ItemResultadoSorteio(resultado));

            ControllerSorteio.getInstance().salvarSorteio(sorteio);

            Intent intent = new Intent(this, ResultSorteioPersActivity.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(this, SorteioPersonalizadoActivity.class);
            startActivityForResult(intent, 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && data != null && data.getIntExtra("resultado", 0) == 1)
            finish();
    }
}

