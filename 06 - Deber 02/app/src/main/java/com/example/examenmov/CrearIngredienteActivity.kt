package com.example.examenmov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class CrearIngredienteActivity : AppCompatActivity() {

    lateinit var inputValor: TextInputEditText
    lateinit var inputMateria: TextInputEditText
    lateinit var inputCodMateria: TextInputEditText
    lateinit var inputIngredienteImportado: CheckBox
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_ingrediente)

        val btnCrear = findViewById<Button>(R.id.btn_crear_nota)
        obtenerDatos()
        btnCrear.setOnClickListener {
            logicaCrear()
        }
    }

    fun obtenerDatos(){
        inputValor = findViewById(R.id.input_precio_ingrediente)
        inputMateria = findViewById(R.id.input_Ingrediente)
        inputCodMateria = findViewById(R.id.input_caloriasIngrediente)
        inputIngredienteImportado = findViewById(R.id.cb_ingrediente_importado)
    }
    fun logicaCrear(){
        val id : Int = intent.extras?.getInt("id")!!
        val key : Int = intent.extras?.getInt("key")!!

        val lista = EBaseDatos.tablaReceta!!.consultarRecetas()

        val inputNota:String = inputValor.text.toString()
        var precio: Double = 0.0
        val nombre = inputMateria.text.toString()
        val cod = inputCodMateria.text.toString()
        val importado = inputIngredienteImportado.isChecked
        var codMat: Int = 0


        try {
            precio= inputNota.toDoubleOrNull()!!
            codMat = cod.toInt()
            if (precio != null) {
                lista.values.elementAt(id).agregarIngrediente(nombre, precio, codMat, importado)
                var valImportado = 0
                if(importado) valImportado = 1
                EBaseDatos.tablaIngrediente?.crearIngrediente(nombre, valImportado, precio.toFloat(),
                    "", codMat, key)!!

                Toast.makeText(this," Ingrediente Creado", Toast.LENGTH_SHORT).show()
                val intend = Intent(this, MainActivity::class.java)
                startActivity(intend)
            }
        } catch (e: NumberFormatException) {
            println(e)
        }
    }
}