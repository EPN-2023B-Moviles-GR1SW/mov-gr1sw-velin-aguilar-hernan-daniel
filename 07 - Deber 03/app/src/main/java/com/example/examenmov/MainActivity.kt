package com.example.examenmov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val images = listOf(
            R.drawable.contagion2,
            R.drawable.xfiles,
            R.drawable.barbie
        )

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val adapter2 = ImageSliderAdapter(images)
        val viewPager: ViewPager2 = findViewById(R.id.viewPagerImages)
        viewPager.adapter = adapter2

        val navView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        navView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_dashboard -> {
                    val intend = Intent(this, DescargasActivity::class.java)
                    startActivity(intend)
                    true
                }
                R.id.navigation_notifications -> {
                    val intend = Intent(this, BusquedaActivity::class.java)
                    startActivity(intend)
                    true
                }
                else -> false
            }
        }

        val sectionsRecyclerView: RecyclerView = findViewById(R.id.sectionsRecyclerView)
        sectionsRecyclerView.layoutManager = LinearLayoutManager(this)

        val sections = listOf(
            Section(
                title = "Prime - Las series más vistas en Prime Video",
                items = listOf(
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
                    )
                )
            ),
            Section(
                title = "Prime - Series Basadas en Libros",
                items = listOf(
                    Program(
                        id = "1",
                        imageUrl = R.drawable.fall,
                        title = "Stranger Things",
                        description = "Cuando un niño desaparece, un pequeño pueblo descubre un misterio que involucra experimentos secretos, fuerzas sobrenaturales aterradoras y una niña extraña.",
                        rating = 8.7,
                        genre = "Terror",
                        anio = 2010
                    ),
                    Program(
                        id = "2",
                        imageUrl = R.drawable.naruto,
                        title = "Stranger Things",
                        description = "Cuando un niño desaparece, un pequeño pueblo descubre un misterio que involucra experimentos secretos, fuerzas sobrenaturales aterradoras y una niña extraña.",
                        rating = 8.7,
                        genre = "Terror",
                        anio = 2010
                    ),
                    Program(
                        id = "3",
                        imageUrl = R.drawable.invincible,
                        title = "Stranger Things",
                        description = "Cuando un niño desaparece, un pequeño pueblo descubre un misterio que involucra experimentos secretos, fuerzas sobrenaturales aterradoras y una niña extraña.",
                        rating = 8.7,
                        genre = "Terror",
                        anio = 2010
                    ),
                    Program(
                        id = "4",
                        imageUrl = R.drawable.dororo,
                        title = "Stranger Things",
                        description = "Cuando un niño desaparece, un pequeño pueblo descubre un misterio que involucra experimentos secretos, fuerzas sobrenaturales aterradoras y una niña extraña.",
                        rating = 8.7,
                        genre = "Terror",
                        anio = 2010
                    ),
                    Program(
                        id = "5",
                        imageUrl = R.drawable.dragonballz,
                        title = "Stranger Things",
                        description = "Cuando un niño desaparece, un pequeño pueblo descubre un misterio que involucra experimentos secretos, fuerzas sobrenaturales aterradoras y una niña extraña.",
                        rating = 8.7,
                        genre = "Terror",
                        anio = 2010
                    )
                )
            ),
            Section(
                title = "Prime - Series de Comedia",
                items = listOf(
                    Program(
                        id = "1",
                        imageUrl = R.drawable.proyectox,
                        title = "Stranger Things",
                        description = "Cuando un niño desaparece, un pequeño pueblo descubre un misterio que involucra experimentos secretos, fuerzas sobrenaturales aterradoras y una niña extraña.",
                        rating = 8.7,
                        genre = "Terror",
                        anio = 2010
                    ),
                    Program(
                        id = "2",
                        imageUrl = R.drawable.click,
                        title = "Stranger Things",
                        description = "Cuando un niño desaparece, un pequeño pueblo descubre un misterio que involucra experimentos secretos, fuerzas sobrenaturales aterradoras y una niña extraña.",
                        rating = 8.7,
                        genre = "Terror",
                        anio = 2010
                    ),
                    Program(
                        id = "3",
                        imageUrl = R.drawable.detectivepikachu,
                        title = "Stranger Things",
                        description = "Cuando un niño desaparece, un pequeño pueblo descubre un misterio que involucra experimentos secretos, fuerzas sobrenaturales aterradoras y una niña extraña.",
                        rating = 8.7,
                        genre = "Terror",
                        anio = 2010
                    ),
                    Program(
                        id = "4",
                        imageUrl = R.drawable.qpa2,
                        title = "Stranger Things",
                        description = "Cuando un niño desaparece, un pequeño pueblo descubre un misterio que involucra experimentos secretos, fuerzas sobrenaturales aterradoras y una niña extraña.",
                        rating = 8.7,
                        genre = "Terror",
                        anio = 2010
                    ),
                    Program(
                        id = "5",
                        imageUrl = R.drawable.mcklovin,
                        title = "Stranger Things",
                        description = "Cuando un niño desaparece, un pequeño pueblo descubre un misterio que involucra experimentos secretos, fuerzas sobrenaturales aterradoras y una niña extraña.",
                        rating = 8.7,
                        genre = "Terror",
                        anio = 2010
                    )
                )
            ),
            Section(
                title = "Prime - Series de Ciencia Ficción",
                items = listOf(
                    Program(
                        id = "1",
                        imageUrl = R.drawable.sinlimites,
                        title = "Stranger Things",
                        description = "Cuando un niño desaparece, un pequeño pueblo descubre un misterio que involucra experimentos secretos, fuerzas sobrenaturales aterradoras y una niña extraña.",
                        rating = 8.7,
                        genre = "Terror",
                        anio = 2010
                    ),
                    Program(
                        id = "2",
                        imageUrl = R.drawable.xfiles,
                        title = "Stranger Things",
                        description = "Cuando un niño desaparece, un pequeño pueblo descubre un misterio que involucra experimentos secretos, fuerzas sobrenaturales aterradoras y una niña extraña.",
                        rating = 8.7,
                        genre = "Terror",
                        anio = 2010
                    ),
                    Program(
                        id = "3",
                        imageUrl = R.drawable.tenet,
                        title = "Stranger Things",
                        description = "Cuando un niño desaparece, un pequeño pueblo descubre un misterio que involucra experimentos secretos, fuerzas sobrenaturales aterradoras y una niña extraña.",
                        rating = 8.7,
                        genre = "Terror",
                        anio = 2010
                    ),
                    Program(
                        id = "4",
                        imageUrl = R.drawable.contagion,
                        title = "Stranger Things",
                        description = "Cuando un niño desaparece, un pequeño pueblo descubre un misterio que involucra experimentos secretos, fuerzas sobrenaturales aterradoras y una niña extraña.",
                        rating = 8.7,
                        genre = "Terror",
                        anio = 2010
                    ),
                    Program(
                        id = "5",
                        imageUrl = R.drawable.her,
                        title = "Stranger Things",
                        description = "Cuando un niño desaparece, un pequeño pueblo descubre un misterio que involucra experimentos secretos, fuerzas sobrenaturales aterradoras y una niña extraña.",
                        rating = 8.7,
                        genre = "Terror",
                        anio = 2010
                    )
                )
            ),
            Section(
                title = "Prime - Series de Terror",
                items = listOf(
                    Program(
                        id = "1",
                        imageUrl = R.drawable.tremors,
                        title = "Stranger Things",
                        description = "Cuando un niño desaparece, un pequeño pueblo descubre un misterio que involucra experimentos secretos, fuerzas sobrenaturales aterradoras y una niña extraña.",
                        rating = 8.7,
                        genre = "Terror",
                        anio = 2010
                    ),
                    Program(
                        id = "2",
                        imageUrl = R.drawable.getout,
                        title = "Stranger Things",
                        description = "Cuando un niño desaparece, un pequeño pueblo descubre un misterio que involucra experimentos secretos, fuerzas sobrenaturales aterradoras y una niña extraña.",
                        rating = 8.7,
                        genre = "Terror",
                        anio = 2010
                    ),
                    Program(
                        id = "3",
                        imageUrl = R.drawable.gunter,
                        title = "Stranger Things",
                        description = "Cuando un niño desaparece, un pequeño pueblo descubre un misterio que involucra experimentos secretos, fuerzas sobrenaturales aterradoras y una niña extraña.",
                        rating = 8.7,
                        genre = "Terror",
                        anio = 2010
                    ),
                    Program(
                        id = "4",
                        imageUrl = R.drawable.laniebla,
                        title = "Stranger Things",
                        description = "Cuando un niño desaparece, un pequeño pueblo descubre un misterio que involucra experimentos secretos, fuerzas sobrenaturales aterradoras y una niña extraña.",
                        rating = 8.7,
                        genre = "Terror",
                        anio = 2010
                    ),
                    Program(
                        id = "5",
                        imageUrl = R.drawable.contagion,
                        title = "Stranger Things",
                        description = "Cuando un niño desaparece, un pequeño pueblo descubre un misterio que involucra experimentos secretos, fuerzas sobrenaturales aterradoras y una niña extraña.",
                        rating = 8.7,
                        genre = "Terror",
                        anio = 2010
                    )
                )
            )
        )

        val adapter = SectionAdapter(sections)
        sectionsRecyclerView.adapter = adapter

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_home, menu)
        return true
    }

}