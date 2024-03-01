package com.example.examenmov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import com.example.examen.models.ManejadorRecetaFirebase
import com.google.android.material.textfield.TextInputEditText

class RecetaRegistros : AppCompatActivity() {

    lateinit var inputNombre:TextInputEditText
    lateinit var inputApellido:TextInputEditText
    lateinit var inputCurso:TextInputEditText
    lateinit var inputPlatoTemporada:CheckBox
    lateinit var inputDiaCum:TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receta_registrar)
        obtenerDatos()

        val btnCrear = findViewById<Button>(R.id.btn_crear)
        btnCrear.setOnClickListener {
            if(verificarDatos()){
                if(ManejadorRecetaFirebase.isRegistered(inputNombre.text.toString()!!)){
                    Toast.makeText(this, "ERROR! Receta ya registrada", Toast.LENGTH_SHORT).show()
                }
                else{
                    ManejadorRecetaFirebase.agregarReceta(inputNombre.text.toString(),inputApellido.text.toString().toFloat())
                    Toast.makeText(this, "Receta Creada con éxito", Toast.LENGTH_SHORT).show()
                }
                //Toast.makeText(this, "Receta Creada", Toast.LENGTH_SHORT).show()
                val intend = Intent(this, MainActivity::class.java)
                startActivity(intend)
            }else{
                Toast.makeText(this, "Complete los datos", Toast.LENGTH_SHORT).show()
            }

        }

    }
    fun obtenerDatos(){
        inputNombre= findViewById(R.id.input_nombre)
        inputApellido = findViewById(R.id.input_precio)
        inputCurso = findViewById(R.id.input_porciones)
        inputPlatoTemporada = findViewById(R.id.input_platoTemporada)
        inputDiaCum = findViewById(R.id.input_agregacion)
    }
    fun verificarDatos(): Boolean {
        if (inputNombre.text?.isEmpty() == true || inputApellido.text?.isEmpty() == true)
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