package com.smartgame;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import adapters.DetalheProdutoAdapter;
import adapters.ProdutoAdapter;
import models.Loja;
import models.Produto;
import utils.HttpConnection;

import static utils.IpServidorKt.ipServidor;
/*AUTOR:William Tristão de Paula
 * DATA DE MODIFICAÇÃO: 30/09
 * */
public class LojaProdutoActivity extends AppCompatActivity {

    ListView list_view;

    DetalheProdutoAdapter adapter;

    String idProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = new Intent();

        idProduto = intent.getStringExtra("id");

        list_view = (ListView) findViewById(R.id.list_view_detalhe);

        List<Loja> lstLoja = new ArrayList<>();

        adapter = new DetalheProdutoAdapter(this, lstLoja);

        list_view.setAdapter(adapter);

        new AsyncTask<Void, Void, Void>(){

            ArrayList<Loja> lstLoja = new ArrayList<Loja>();

            @Override
            protected Void doInBackground(Void... voids) {

                String retornoJson = HttpConnection.get(ipServidor()+"/smartgames/api/buscarLojasId.php?idProduto="+idProduto);

                Log.d("TAG", retornoJson);

                try {
                    JSONArray jsonArray = new JSONArray(retornoJson);

                    for(int i =0; i < jsonArray.length(); i++){

                        JSONObject item = jsonArray.getJSONObject(i);

                        Loja l = new  Loja(jsonArray.getJSONObject(i).getInt("idLoja"), jsonArray.getJSONObject(i).getString("cidade"),
                                jsonArray.getJSONObject(i).getString("estado"), jsonArray.getJSONObject(i).getString("logradouro"),
                                jsonArray.getJSONObject(i).getString("bairro"), jsonArray.getJSONObject(i).getString("lat"),
                                jsonArray.getJSONObject(i).getString("long"), jsonArray.getJSONObject(i).getString("estoque"));
                        lstLoja.add(l);
                    }

                    Log.d("APIV", lstLoja.size()+"");
                }catch (Exception ex){
                    Log.e("Erro: ", ex.getMessage());
                }

                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                adapter.addAll(lstLoja);
            }
        }.execute();


        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                /*Loja item = adapter.getItem(i);

                Intent intent = new Intent(getApplicationContext(), DetalhesProdutoActivity.class);

                intent.putExtra("id", item.getIdProduto()+"");

                startActivity(intent);*/

            }
        });
    }

}
