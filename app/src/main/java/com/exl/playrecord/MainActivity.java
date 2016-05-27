package com.exl.playrecord;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    Boolean snackFlag=false;
    Snackbar snackbar;
    Snackbar.Callback call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);


        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (snackbar==null) {
                        //Create Snackbar
                        snackbar = Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_INDEFINITE);

                        //Get Snackbar Layout
                        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
                        layout.setBackgroundColor(ContextCompat.getColor(getBaseContext(),R.color.colorsnackEdit));
                        //Hide textView
                        TextView textView = (TextView) layout.findViewById(android.support.design.R.id.snackbar_text);
                        textView.setVisibility(View.INVISIBLE);
                        //Setup custom View
                        LayoutInflater myInflater = getLayoutInflater();
                        View snackView = myInflater.inflate(R.layout.my_snackbar, null);
                        snackView.setBackgroundColor(ContextCompat.getColor(getBaseContext(),R.color.colorsnackEdit));
                        EditText editText = (EditText) snackView.findViewById(R.id.snackEditText);
                        Button btn_add = (Button) snackView.findViewById(R.id.snackButtonAdd);
                        View.OnClickListener clk_add = new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //TODO
                            }
                        };
                        btn_add.setOnClickListener(clk_add);

                        layout.addView(snackView, 0);
                    }

                    if (snackFlag==false) {
                        snackFlag=true;
                        snackbar.show();
                    }else {
                        snackFlag = false;
                        snackbar.dismiss();
                    }
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
