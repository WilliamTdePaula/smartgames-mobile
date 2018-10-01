package com.smartgame

import adapters.DetalheProdutoAdapter
import adapters.ProdutoAdapter
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.ListView
import android.widget.Toast
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.content_detalhes_produto.*
import models.Loja
import models.Produto
import models.ProdutoLoja
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONArray
import org.json.JSONObject
import utils.HttpConnection
import utils.converterDinheiro
import utils.ipServidor
/*AUTOR:William Tristão de Paula
* DATA DE MODIFICAÇÃO: 30/09
* */
class DetalhesProdutoActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val idProduto:String = intent.getStringExtra("id");

        var produto = Produto()

        val adapter = DetalheProdutoAdapter(applicationContext, ArrayList<Loja>())

        //BUSCA DE DETALHES DO PRODUTO SELECIONADO
        doAsync {

            val url = ipServidor()+"/smartgames/api/buscarProdutoId.php"

            val map = HashMap<String, String>()
            map.put("idProduto", idProduto)

            val resultado = HttpConnection.post(url, map)

            Log.d("API", resultado)

            uiThread {

                val jsonarray = JSONArray(resultado)

                for (i in 0 until jsonarray.length()) {

                    val jsonobject = jsonarray.getJSONObject(i)

                    //pegando itens da API
                    produto = Produto(jsonobject.getInt("idProduto"), jsonobject.getString("nome"), jsonobject.getString("imagem"), jsonobject.getString("valor"),
                            jsonobject.getString("detalhe"), jsonobject.getString("destaque"));

                    preencherCampos(produto)//CHAMANDO FUNÇÃO PARA PREENCHER CAMPOS
                }
            }
        }

        //
        ondeEstamos.setOnClickListener {

            val intent = Intent(applicationContext, MapsActivity::class.java)

            startActivity(intent)
        }
    }

    //FUNCÃO PARA PREENCHER CAMPOS
    fun preencherCampos(produto: Produto){
        nomeDetalheProduto.text = produto.getNome()
        Picasso.with(applicationContext).load(ipServidor() + "/smartgames/" + produto.getImagem()).into(imgDetalheProduto)
        valorDetalhe.text = converterDinheiro(produto.getValor())
        txtDetalheProd.text = produto.getDetalhe()
    }

}
