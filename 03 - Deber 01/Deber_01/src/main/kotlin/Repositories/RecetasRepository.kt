package Repositories

import Entities.Ingrediente
import Entities.Receta
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.util.*

class RecetasRepository() : Repository() {
    private val recetas: MutableMap<Int, Receta> = mutableMapOf()
    private val ingredientesRepository : IngredientesRepository = IngredientesRepository()
    override var rutaArchivo : String = "src/main/kotlin/Resources/recetas.txt"

    init{
        cargarDatos()
    }

    override fun cargarDatos() {
        val file = File("src/main/kotlin/Resources/recetas.txt")
        var counter: Int = 0
        if (file.exists()) {
            file.forEachLine {
                val partes = it.split(",")
                if (partes.size >= 5) {
                    val nombre = partes[0]
                    val esPlatoTemporada = partes[1].toBoolean()
                    val precio = partes[2].toFloat()
                    val fechaAgregacionMenu = Date(partes[3].toLong())
                    val porcionesPlato = partes[4].toInt()
                    val receta = Receta(nombre, esPlatoTemporada, precio, fechaAgregacionMenu, porcionesPlato)

                    recetas[counter++] = receta

                    if (partes.size > 5) {
                        for (i in 5 until partes.size) {

                            val nombreIngrediente = partes[i]

                            val ingrediente = ingredientesRepository.obtenerIngredientePorNombre(nombreIngrediente)
                            if (ingrediente != null) {
                                receta.ingredientes += ingrediente
                            }
                        }
                    }
                }
            }
        }
    }

    override fun guardarDatos() {
        val file = File(rutaArchivo)
        FileWriter(file, false).use { fileWriter ->
            BufferedWriter(fileWriter).use { out ->
                recetas.values.forEach { receta ->
                    val listaNombresIngredientes = receta.ingredientes.joinToString(",") { it.nombre }
                    out.write("${receta.nombre},${receta.esPlatoTemporada},${receta.precio},${receta.fechaAgregacionMenu.time},${receta.porcionesPlato},$listaNombresIngredientes\n")
                }
            }
        }
    }



    fun agregarReceta(receta: Receta) {
        recetas[recetas.size] = receta
        guardarDatos()
    }

    fun obtenerRecetaPorNombre(nombre: String): Receta? {
        return recetas.values.find { it.nombre == nombre }
    }

    fun eliminarRecetaPorNombre(nombre: String) {
        val idAEliminar = recetas.entries.find { it.value.nombre == nombre }?.key
        idAEliminar?.let { recetas.remove(it) }
        guardarDatos()
    }

    fun obtenerRecetas(): List<Receta> {
        return recetas.values.toList()
    }

    fun actualizarReceta(nombre: String, nuevaReceta: Receta) {
        val recetaAActualizar  = recetas.entries.find { it.value.nombre == nombre }

        if (recetaAActualizar != null) {
            recetas[recetaAActualizar.key] = nuevaReceta
            guardarDatos()
        }
    }

    fun agregarIngrediente(receta : String, ingrediente: Ingrediente){
        val recetaAActualizar  = recetas.entries.find { it.value.nombre == receta }

        if(recetaAActualizar != null){
            recetas[recetaAActualizar.key]?.ingredientes?.add(ingrediente)
            guardarDatos()
        }
    }

    fun eliminarIngrediente(receta : String, ingrediente: Ingrediente){
        val recetaAActualizar  = recetas.entries.find { it.value.nombre == receta }

        if(recetaAActualizar != null){
            recetas[recetaAActualizar.key]?.ingredientes?.removeIf { it.nombre == ingrediente.nombre }
            guardarDatos()
        }
    }
}