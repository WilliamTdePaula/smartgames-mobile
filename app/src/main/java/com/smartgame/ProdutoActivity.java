package com.smartgame;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import adapters.ProdutoAdapter;
import models.Produto;
import utils.HttpConnection;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ImageView;
import android.util.Log;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import static utils.ConverterDinheiroKt.converterDinheiro;
import static utils.IpServidorKt.ipServidor;
/*AUTOR:William Tristão de Paula
* DATA DE MODIFICAÇÃO: 30/09
* */
public class ProdutoActivity extends AppCompatActivity {

    ListView list_view;

    ProdutoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        list_view = (ListView) findViewById(R.id.list_view);

        List<Produto> lstProduto = new ArrayList<>();

        adapter = new ProdutoAdapter(this, lstProduto);

        list_view.setAdapter(adapter);

        new AsyncTask<Void, Void, Void>(){//fazendo conexão com a API

            ArrayList<Produto> lstViagem = new ArrayList<Produto>();

            @Override
            protected Void doInBackground(Void... voids) {

                String retornoJson = HttpConnection.get(ipServidor()+"/smartgames/api/buscarProdutos.php");//link API

                Log.d("TAG", retornoJson);

                try {
                    JSONArray jsonArray = new JSONArray(retornoJson);

                    for(int i =0; i < jsonArray.length(); i++){

                        JSONObject item = jsonArray.getJSONObject(i);

                        //pegando itens da API
                        Produto c = new  Produto(
                                jsonArray.getJSONObject(i).getInt("idProduto"),
                                jsonArray.getJSONObject(i).getString("nome"),
                                jsonArray.getJSONObject(i).getString("imagem"),
                                jsonArray.getJSONObject(i).getString("valor"),
                                jsonArray.getJSONObject(i).getString("detalhe"),
                                jsonArray.getJSONObject(i).getString("destaque"));
                        lstViagem.add(c);
                    }

                    Log.d("TAG", lstViagem.size()+"");
                }catch (Exception ex){
                    Log.e("Erro: ", ex.getMessage());
                }

                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                adapter.addAll(lstViagem);
            }
        }.execute();

        //OUVINTE DA LISTA
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Produto item = adapter.getItem(i);

                Intent intent = new Intent(getApplicationContext(), DetalhesProdutoActivity.class);//ABRIR UM ITEM DA LISTA

                intent.putExtra("id", item.getIdProduto()+"");

                startActivity(intent);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.sobrenos_settings){
            startActivity(new Intent(getApplicationContext(), SobreNosActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }



}
