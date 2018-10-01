package com.smartgame

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.activity_sobre_nos.*
import kotlinx.android.synthetic.main.content_detalhes_produto.*
import kotlinx.android.synthetic.main.content_sobre_nos.*
import models.Produto
import models.SobreNos
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONArray
import utils.HttpConnection
import utils.converterDinheiro
import utils.ipServidor
/*AUTOR:William Tristão de Paula
* DATA DE MODIFICAÇÃO: 30/09
* */
class SobreNosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sobre_nos)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //fazendo conexão com a API
        doAsync {

            val resultado = HttpConnection.get(ipServidor() +"/smartgames/api/buscarSobreNos.php");//link API

            Log.d("API", resultado)

            uiThread {

                val jsonarray = JSONArray(resultado)

                for (i in 0 until jsonarray.length()) {

                    val jsonobject = jsonarray.getJSONObject(i)

                    //pegando itens da API
                    val sobreNos = SobreNos(jsonobject.getInt("id_sobrenos"), jsonobject.getString("txtSobreNos"),
                            jsonobject.getString("txtNossoProposito"), jsonobject.getString("telefone"), jsonobject.getString("email"));

                    preencherCampos(sobreNos)
                }
            }
        }
    }

    fun preencherCampos(sobreNos: SobreNos){
        sobrenos.text = sobreNos.txtSobreNos
        nossoproposito.text = sobreNos.txtNossoProposito
        telEmpr.text = sobreNos.telefone
        emailEmpr.text = sobreNos.email
    }

}
