package com.example.examenmov

import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class BusquedaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_busqueda)

        val programsList = listOf(
                Program(
                    id = "1",
                    imageUrl = R.drawable.dragonballz,
                    title = "Interstellar",
                    description = "Un equipo de exploradores viaja a través de un agujero de gusano en el espacio en un intento de asegurar la supervivencia de la humanidad.",
                    rating = 8.6,
                    genre = "Ciencia ficción",
                    anio = 2010
                ),
                Program(
                    id = "2",
                    imageUrl = R.drawable.xfiles,
                    title = "El origen",
                    description = "Un ladrón que roba secretos corporativos a través del uso de la tecnología de compartir sueños, se le da la tarea inversa de plantar una idea en la mente de un director ejecutivo.",
                    rating = 8.8,
                    genre = "Acción",
                    anio = 2010
                ),
                Program(
                    id = "3",
                    imageUrl = R.drawable.naruto,
                    title = "El origen",
                    description = "Un ladrón que roba secretos corporativos a través del uso de la tecnología de compartir sueños, se le da la tarea inversa de plantar una idea en la mente de un director ejecutivo.",
                    rating = 8.8,
                    genre = "Acción",
                    anio = 2010
                ),
                Program(
                    id = "4",
                    imageUrl = R.drawable.invincible,
                    title = "El origen",
                    description = "Un ladrón que roba secretos corporativos a través del uso de la tecnología de compartir sueños, se le da la tarea inversa de plantar una idea en la mente de un director ejecutivo.",
                    rating = 8.8,
                    genre = "Acción",
                    anio = 2010
                ),
                Program(
                    id = "5",
                    imageUrl = R.drawable.detectivepikachu,
                    title = "El origen",
                    description = "Un ladrón que roba secretos corporativos a través del uso de la tecnología de compartir sueños, se le da la tarea inversa de plantar una idea en la mente de un director ejecutivo.",
                    rating = 8.8,
                    genre = "Acción",
                    anio = 2010
                ),
            Program(
                id = "5",
                imageUrl = R.drawable.her,
                title = "El origen",
                description = "Un ladrón que roba secretos corporativos a través del uso de la tecnología de compartir sueños, se le da la tarea inversa de plantar una idea en la mente de un director ejecutivo.",
                rating = 8.8,
                genre = "Acción",
                anio = 2010
            ),
            Program(
                id = "5",
                imageUrl = R.drawable.laniebla,
                title = "El origen",
                description = "Un ladrón que roba secretos corporativos a través del uso de la tecnología de compartir sueños, se le da la tarea inversa de plantar una idea en la mente de un director ejecutivo.",
                rating = 8.8,
                genre = "Acción",
                anio = 2010
            ),
            Program(
                id = "5",
                imageUrl = R.drawable.sinlimites,
                title = "El origen",
                description = "Un ladrón que roba secretos corporativos a través del uso de la tecnología de compartir sueños, se le da la tarea inversa de plantar una idea en la mente de un director ejecutivo.",
                rating = 8.8,
                genre = "Acción",
                anio = 2010
            )
            )

        val recyclerView: RecyclerView = findViewById(R.id.programsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = BusquedaProgramAdapter(programsList)

        val navView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        navView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    val intend = Intent(this, MainActivity::class.java)
                    startActivity(intend)
                    true
                }
                R.id.navigation_dashboard -> {
                    val intend = Intent(this, DescargasActivity::class.java)
                    startActivity(intend)
                    true
                }
                else -> false
            }
        }
    }
}