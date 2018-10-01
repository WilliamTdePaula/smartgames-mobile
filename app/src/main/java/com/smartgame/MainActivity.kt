package com.smartgame

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import kotlinx.android.synthetic.main.activity_main.*
/*AUTOR:William Tristão de Paula
* DATA DE MODIFICAÇÃO: 29/09
* */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //ouvinte do botão
        btnContinuarInicio.setOnClickListener {
            startActivity(Intent(applicationContext, ProdutoActivity::class.java));
        }


    }
}
