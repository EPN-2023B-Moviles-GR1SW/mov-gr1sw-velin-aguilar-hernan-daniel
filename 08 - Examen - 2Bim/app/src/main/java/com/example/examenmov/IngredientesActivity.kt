package com.example.examenmov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.examen.models.Ingrediente
import com.example.examen.models.ManejadorRecetaFirebase
import com.example.examen.models.Receta
import com.example.examenmov.models.BaseAdapterIngrediente
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class IngredientesActivity : AppCompatActivity() {
    var ingredientes:MutableList<Ingrediente>? = null
    var receta : Receta? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredientes)
        initProgram()
    }

    fun initProgram(){
        val tvResult = findViewById<TextView>(R.id.tv_est)

        val id: String? = intent.extras?.getString("id")

        val lista = ManejadorRecetaFirebase.obtenerListaRecetas()
        receta = lista.values.firstOrNull { receta -> receta.nombre == id}
        var idReceta : String? = lista.keys.firstOrNull { receta -> lista.get(receta)?.nombre == id}

        val nombre = receta?.nombre
        val precio = receta?.precio

        tvResult.text = "$nombre $precio"

        lifecycleScope.launch {
            ingredientes = ManejadorRecetaFirebase.obtenerListaIngredientes(idReceta!!)
            withContext(Dispatchers.Main) {
                if (ingredientes?.isNotEmpty() == true) {
                    val adaptadorCal = ingredientes?.let { BaseAdapterIngrediente(this@IngredientesActivity, it) }
                    val lv2 = findViewById<ListView>(R.id.lv_cal)
                    lv2.adapter = adaptadorCal

                    lv2.setOnItemClickListener { _, view, position, _ ->
                        val menuIcon = view.findViewById<androidx.cardview.widget.CardView>(R.id.cv_nota)
                        showPopupMenu(menuIcon, idReceta, ingredientes!![position].nombre)
                    }
                } else {
                    val tv_exep = findViewById<TextView>(R.id.tv_exep)
                    tv_exep.text = "No tiene ingredientes"
                }
            }
        }

        val btnNuevaNota = findViewById<Button>(R.id.btn_nueva_cal)
        btnNuevaNota.setOnClickListener {
            val intend = Intent(this, CrearIngredienteActivity::class.java)
            intend.putExtra("id", idReceta)
            startActivity(intend)
        }

    }
    fun showPopupMenu(view: View, idReceta: String, nombreIngrediente: String) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.inflate(R.menu.menu_ingredientes)

        popupMenu.setOnMenuItemClickListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.opc1-> {
                    Toast.makeText(this, "Ingrediente agregado", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.opc2 -> {
                    ManejadorRecetaFirebase.eliminarIngrediente(idReceta, nombreIngrediente)
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