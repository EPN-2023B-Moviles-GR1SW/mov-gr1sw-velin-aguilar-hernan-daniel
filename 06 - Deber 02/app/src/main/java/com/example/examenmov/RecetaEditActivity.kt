package com.example.examenmov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import com.example.examen.models.ManejadorReceta
import com.google.android.material.textfield.TextInputEditText
import java.lang.Exception
import java.util.Date

class RecetaEditActivity : AppCompatActivity() {

    lateinit var inputNombre: TextInputEditText
    lateinit var inputPrecio: TextInputEditText
    lateinit var inputPlatoTemporada: CheckBox
    lateinit var inputPorciones: TextInputEditText
    lateinit var inputDiaCum: TextInputEditText

    var id: Int = 0
    var key : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_receta)
        id = intent.extras?.getInt("id")!!
        key = intent.extras?.getInt("key")!!
        ponerDatos()
        var platoTemporada = 0
        if(inputPlatoTemporada.isChecked) platoTemporada = 1
        val btnEditar = findViewById<Button>(R.id.btn_editar)
        btnEditar.setOnClickListener {
            if(verificarDatos()){
                try {
                    EBaseDatos.tablaEntrenador!!.actualizarReceta(inputNombre.text.toString(),
                       platoTemporada, inputPrecio.text.toString().toFloat(), Date().toString()
                        , inputPorciones.text.toString().toInt(), key)
                }catch (e: Exception){
                    println(e)
                }
                Toast.makeText(this, "Receta Editada Correctamente", Toast.LENGTH_SHORT).show()
                val intend = Intent(this, MainActivity::class.java)
                startActivity(intend)
            }else{
                Toast.makeText(this, "Complete los datos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun ponerDatos(){
        inputNombre= findViewById(R.id.input_nombre)
        inputPrecio = findViewById(R.id.input_precio)
        inputPlatoTemporada = findViewById(R.id.input_platoTemporada)
        inputPorciones = findViewById(R.id.input_porciones)
        inputDiaCum = findViewById(R.id.input_agregacion)

        val lista = EBaseDatos.tablaEntrenador!!.consultarRecetas()
        inputNombre.setText(lista.values.elementAt(id)!!.nombre)
        inputPrecio.setText(lista.values.elementAt(id)!!.precio.toString())
        lista.values.elementAt(id)!!.esPlatoTemporada?.let { inputPlatoTemporada.isChecked }
        inputPorciones.setText(lista.values.elementAt(id)!!.porcionesPlato.toString())
        lista.values.elementAt(id)!!.fechaAgregacionMenu?.let { inputDiaCum.setText(it.toString()) }

    }
    fun verificarDatos(): Boolean {
        if (inputNombre.text?.isEmpty() == true || inputPrecio.text?.isEmpty() == true)
        {
            val inputLayout = findViewById<com.google.android.material.textfield.TextInputLayout>(R.id.layout_nombre)
            inputLayout.error = "Este campo es obligatorio"
            val inputLayout2 = findViewById<com.google.android.material.textfield.TextInputLayout>(R.id.layout_apellido)
            inputLayout2.error = "Este campo es obligatorio"
            return false
        }else{
            return true
        }
    }

}