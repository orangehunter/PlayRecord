package com.exl.playrecord;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.exl.playrecord.Adapter.RecyclerAdapter;
import com.exl.playrecord.Struct.Item_datas;
import com.exl.playrecord.script.SQLController;

public class MainActivity extends AppCompatActivity {

    Boolean snackFlag = false;
    Snackbar snackbar;
    EditText snack_editText;
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    Variable variable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        variable.db=new SQLController(getApplicationContext());
        variable.db.open();

        variable.datas=new SparseArray<Item_datas>();

        recyclerView=(RecyclerView)findViewById(R.id.ReView);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerAdapter=new RecyclerAdapter(variable.datas,this);
        recyclerView.setAdapter(recyclerAdapter);
        //FAB按鈕<=====
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (snackbar == null) {
                        //Create Snackbar
                        snackbar = Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_INDEFINITE);

                        //Get Snackbar Layout
                        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
                        layout.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.colorsnackEdit));
                        //Hide textView
                        TextView textView = (TextView) layout.findViewById(android.support.design.R.id.snackbar_text);
                        textView.setVisibility(View.INVISIBLE);
                        //Setup custom View
                        LayoutInflater myInflater = getLayoutInflater();
                        View snackView = myInflater.inflate(R.layout.my_snackbar, null);
                        snackView.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.colorsnackEdit));
                        snack_editText = (EditText) snackView.findViewById(R.id.snackEditText);
                        Button btn_add = (Button) snackView.findViewById(R.id.snackButtonAdd);
                        View.OnClickListener clk_add = new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(!snack_editText.getText().toString().equals("")){
                                    Item_datas tmp=new Item_datas();
                                    tmp.title=snack_editText.getText().toString();
                                    tmp.type="";
                                    tmp.season=0;
                                    tmp.seasonMax=0;
                                    tmp.message="";
                                    tmp.create_date= SystemClock.currentThreadTimeMillis();
                                    tmp.update_date=new Long(0);
                                    tmp.remind=new Long(0);
                                    tmp.isCustomSeason=false;
                                    if(variable.db.insertsql(tmp)==-1) {
                                        Log.e("MainActivSnackClick","Unable to insertsql!!");
                                    }
                                    reNewRecyclerAdapter();
                                    snackbar.dismiss();
                                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                                    imm.hideSoftInputFromWindow(snack_editText.getWindowToken(), 0);
                                }
                            }
                        };
                        btn_add.setOnClickListener(clk_add);

                        layout.addView(snackView, 0);
                    }

                    if (snackFlag == false) {
                        snackFlag = true;
                        snackbar.show();
                        snack_editText.requestFocus();
                        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
                    } else {
                        snackFlag = false;
                        snackbar.dismiss();
                        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(snack_editText.getWindowToken(), 0);
                    }
                }
            });
        }
        //FAB按鈕=====>
        reNewRecyclerAdapter();
    }
    public void reNewRecyclerAdapter(){
        if(variable.db.getCount()!=0) {
            variable.datas = variable.db.renewDatasFromSQL();
            recyclerAdapter.setData(variable.datas);
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

    @Override
    protected void onDestroy() {
        variable.db.close();
        super.onDestroy();
    }
}
