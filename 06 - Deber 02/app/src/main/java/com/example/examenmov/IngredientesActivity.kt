package com.example.examenmov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import com.example.examen.models.Ingrediente
import com.example.examen.models.Receta
import com.example.examenmov.models.BaseAdapterIngrediente

class IngredientesActivity : AppCompatActivity() {
    var ingredientes:MutableList<Ingrediente>? = null
    var receta : Receta? = null
    var ingredienteEliminar : Ingrediente? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredientes)

        initProgram()
    }

    fun initProgram(){
        val tvResult = findViewById<TextView>(R.id.tv_est)

        val position : Int = intent.extras?.getInt("id")!!
        val key : Int = intent.extras?.getInt("key")!!
        try {
            val lista = EBaseDatos.tablaReceta!!.consultarRecetas()
            receta = lista.values.elementAt(position)

            val nombre = receta?.nombre
            val precio = receta?.precio

            //val id: String = intent.extras?.getString("id").orEmpty()
            tvResult.text = "$nombre $precio"

            ingredientes = EBaseDatos.tablaIngrediente?.consultarIngredientes(key);
            Log.i("receta", ingredientes.toString())

            if (ingredientes?.isNotEmpty() == true) {
                val adaptadorCal = ingredientes?.let { BaseAdapterIngrediente(this, it) }
                val lv2 = findViewById<ListView>(R.id.lv_cal)
                lv2.adapter = adaptadorCal

                lv2.setOnItemClickListener { _, view, position, _ ->

                    val menuIcon =
                        view.findViewById<androidx.cardview.widget.CardView>(R.id.cv_nota)
                    showPopupMenu(menuIcon, position, ingredientes?.elementAt(position)?.nombre!!)
                }
            } else {
                val tv_exep = findViewById<TextView>(R.id.tv_exep)
                tv_exep.text = "No tiene ingredientes"
            }

            val btnNuevaNota = findViewById<Button>(R.id.btn_nueva_cal)
            btnNuevaNota.setOnClickListener {
                val intend = Intent(this, CrearIngredienteActivity::class.java)
                intend.putExtra("id", position)
                intend.putExtra("key", key)
                startActivity(intend)
            }
        }
        catch (e : Exception){

        }
    }
    fun showPopupMenu(view: View, position: Int, nombre : String) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.inflate(R.menu.menu_ingredientes)

        popupMenu.setOnMenuItemClickListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.opc1-> {
                    Toast.makeText(this, "Funcionalidad en proceso", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.opc2 -> {
                    val key = EBaseDatos.tablaIngrediente?.obtenerIdIngrediente(nombre)
                    EBaseDatos.tablaIngrediente?.eliminarIngrediente(key)
                    val intend = Intent(this, MainActivity::class.java)
                    startActivity(intend)
                    Toast.makeText(this, "Ingrediente Eliminado", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }
}