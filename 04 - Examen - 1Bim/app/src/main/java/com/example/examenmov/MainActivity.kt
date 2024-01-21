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
import android.widget.Toast
import com.example.examen.models.CustomBaseAdapter
import com.example.examen.models.ManejadorReceta

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initProgram()

    }

    override fun onResume() {
        super.onResume()
        initProgram()
    }

    fun initProgram() {

        val lista = ManejadorReceta.obtenerLista()
        val miadapter = CustomBaseAdapter(this,lista)
        val lv1 = findViewById<ListView>(R.id.lv_est)
        lv1.adapter = miadapter

        lv1.setOnItemClickListener { _, view, position, _ ->
            // Obtén la vista del ícono del menú
            val menuIcon = view.findViewById<androidx.cardview.widget.CardView>(R.id.cv_item)
            // Muestra el menú contextual
            showPopupMenu(menuIcon, position + 1, miadapter )
        }
        val btnCrearReceta = findViewById<Button>(R.id.btn_crear_nuevo)

        btnCrearReceta.setOnClickListener {
            val intend = Intent(this, RecetaRegistros::class.java)
            startActivity(intend)
        }

        val closeButton: Button = findViewById(R.id.btn_cerrar)
        closeButton.setOnClickListener {
            finish()
        }

    }
    fun showPopupMenu(view: View, position: Int, miadapter: CustomBaseAdapter) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.inflate(R.menu.menu_receta)

        popupMenu.setOnMenuItemClickListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.opcion1 -> {
                    println("confirmado")
                    val intend = Intent(this, RecetaEditActivity::class.java)
                    Log.i("iddd", position.toString())
                    intend.putExtra("id", position)
                    startActivity(intend)
                    true
                }
                R.id.opcion2 -> {
                    val intend = Intent(this, IngredientesActivity::class.java)
                    Log.i("iddd", position.toString())
                    intend.putExtra("id", position)
                    startActivity(intend)
                    true
                }
                R.id.opcion3 -> {
                    ManejadorReceta.eliminarReceta(position)
                    Toast.makeText(this, "Ingrediente Eliminado", Toast.LENGTH_SHORT).show()
                    miadapter.notifyDataSetChanged()
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }

}