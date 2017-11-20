package com.example.luanabelusso.aps_android.telas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by Marciano on 16/11/2017.
 */

public abstract class DefaultActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                this.finish();
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void alert(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
