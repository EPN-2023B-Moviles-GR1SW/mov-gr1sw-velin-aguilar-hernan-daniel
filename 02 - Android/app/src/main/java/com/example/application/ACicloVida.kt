package com.example.application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar

class ACicloVida : AppCompatActivity() {

    var textoGlobal = ""

    fun mostrarSnackBar(texto:String){
        textoGlobal = textoGlobal + " " + texto
        Snackbar.make(findViewById(R.id.cl_ciclo_vida),
            textoGlobal, Snackbar.LENGTH_INDEFINITE).show()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aciclo_vida)
    }

    override fun onStart(){
        super.onStart()
        mostrarSnackBar("onStart")
    }

    override fun onResume(){
        super.onResume()
        mostrarSnackBar("onResume")
    }

    override fun onRestart(){
        super.onRestart()
        mostrarSnackBar("onRestart")
    }

    override fun onPause(){
        super.onPause()
        mostrarSnackBar("onPause")
    }

    override fun onStop(){
        super.onStop()
        mostrarSnackBar("onStop")
    }

    override fun onDestroy(){
        super.onDestroy()
        mostrarSnackBar("onDestroy")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        //Recuperar las variables
        //Primitivos
        val textoRecuperado:String? = savedInstanceState.getString("textoGuardado")
        if(textoRecuperado != null){
            mostrarSnackBar(textoRecuperado)
            textoGlobal = textoRecuperado
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            //Guardar las variables
            //Primitivos
            putString("textoGuardado", textoGlobal)
        }
        super.onSaveInstanceState(outState)
    }

}