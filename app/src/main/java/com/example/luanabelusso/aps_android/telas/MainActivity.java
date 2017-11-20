package com.example.luanabelusso.aps_android.telas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.luanabelusso.aps_android.R;
import com.example.luanabelusso.aps_android.banco.DataBase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sorteioSimples(View view) {
        TextView txtresultado = (TextView) findViewById(R.id.edtresultado);
        EditText edtmin = (EditText) findViewById(R.id.edtminimo);
        EditText edtmax = (EditText) findViewById(R.id.edtmaximo);

        int resultado=0;

        if(edtmin.getText().length() == 0 || edtmax.getText().length() == 0) {
            resultado = sortAleatorio();
        }else{
            int minimo = Integer.parseInt(edtmin.getText().toString());
            int maximo = Integer.parseInt(edtmax.getText().toString());

            if (minimo > 0 && maximo > 0) {
                resultado = (int) (minimo + (Math.random() * (maximo+1-minimo)));
            }else{
                resultado = sortAleatorio();
            }
        }
        txtresultado.setText(""+ resultado);
    }

    public void sorteioPersonalizado(View view){
        Intent intent = new Intent(this, OpcaoSorteioActivity.class);
        Bundle params = new Bundle();
        startActivity(intent);
    }

    public int sortAleatorio(){
        int i;
        double d;

        d = (Math.random()*100);
        d = Math.round(d);

        i = (int) d;
        return i;
    }
}
