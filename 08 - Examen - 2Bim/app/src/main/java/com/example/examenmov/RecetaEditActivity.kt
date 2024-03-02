package com.example.examenmov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import com.example.examen.models.ManejadorRecetaFirebase
import com.example.examen.models.Receta
import com.google.android.material.textfield.TextInputEditText
import java.lang.Exception

class RecetaEditActivity : AppCompatActivity() {

    lateinit var inputNombre: TextInputEditText
    lateinit var inputPrecio: TextInputEditText
    lateinit var inputPlatoTemporada: CheckBox
    lateinit var inputPorciones: TextInputEditText
    lateinit var inputDiaCum: TextInputEditText

    var id: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_receta)
        id = intent.extras?.getString("id")!!
        var idDocumento : String = ponerDatos()

        val btnEditar = findViewById<Button>(R.id.btn_editar)
        btnEditar.setOnClickListener {
            if(verificarDatos()){
                try {
                    ManejadorRecetaFirebase.editarReceta(idDocumento,
                        inputNombre.text.toString(),
                        inputPrecio.text.toString().toFloat())

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

    fun ponerDatos() : String {
        inputNombre= findViewById(R.id.input_nombre)
        inputPrecio = findViewById(R.id.input_precio)
        inputPlatoTemporada = findViewById(R.id.input_platoTemporada)
        inputPorciones = findViewById(R.id.input_porciones)
        inputDiaCum = findViewById(R.id.input_agregacion)

        val lista = ManejadorRecetaFirebase.obtenerListaRecetas()

        var objetoEditar : Receta? = lista.values.firstOrNull { receta -> receta.nombre == id}
        var idRetorno : String? = lista.keys.firstOrNull { receta -> lista.get(receta)?.nombre == id}

        inputNombre.setText(objetoEditar!!.nombre)
        inputPrecio.setText(objetoEditar.precio.toString())
        objetoEditar!!.esPlatoTemporada?.let { inputPlatoTemporada.isChecked }
        inputPorciones.setText(objetoEditar.porcionesPlato.toString())
        objetoEditar!!.fechaAgregacionMenu?.let { inputDiaCum.setText(it.toString()) }

        return idRetorno!!
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