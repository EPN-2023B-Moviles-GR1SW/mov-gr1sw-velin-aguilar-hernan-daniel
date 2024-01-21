package com.example.examenmov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.examen.models.ManejadorReceta
import com.google.android.material.textfield.TextInputEditText

class CrearNotaActivity : AppCompatActivity() {

    lateinit var inputValor: TextInputEditText
    lateinit var inputMateria: TextInputEditText
    lateinit var inputCodMateria: TextInputEditText
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
        inputValor = findViewById(R.id.input_valor)
        inputMateria = findViewById(R.id.input_materia)
        inputCodMateria = findViewById(R.id.input_codMateria)
    }
    fun logicaCrear(){
        val id: Int = intent.extras?.getInt("id")!!

        val lista = ManejadorReceta.obtenerLista()

        val inputNota:String = inputValor.text.toString()
        var precio: Double = 0.0
        val nombre = inputMateria.text.toString()
        val cod = inputCodMateria.text.toString()
        var codMat: Int = 0


        try {
            precio= inputNota.toDoubleOrNull()!!
            codMat = cod.toInt()
            if (precio != null) {
                lista[id]?.agregarIngrediente(nombre, precio, codMat)
                Toast.makeText(this," Ingrediente Creado", Toast.LENGTH_SHORT).show()
                val intend = Intent(this, MainActivity::class.java)
                startActivity(intend)
            }
        } catch (e: NumberFormatException) {
            println(e)
        }

    }
}