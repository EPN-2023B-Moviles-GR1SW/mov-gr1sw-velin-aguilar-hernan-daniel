package Repositories

import Entities.Ingrediente
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.util.*

class IngredientesRepository : Repository() {
    private val ingredientes: MutableMap<Int, Ingrediente> = mutableMapOf()
    override var rutaArchivo : String = "src/main/kotlin/Resources/ingredientes.txt"

    init{
        cargarDatos()
    }
    override fun cargarDatos(){
        val file = File("src/main/kotlin/Resources/ingredientes.txt")
        var counter : Int = 0
        if (file.exists()) {
            file.forEachLine {
                val partes = it.split(",")
                if (partes.size >= 5) {
                    val nombre = partes[0]
                    val esImportado = partes[1].toBoolean()
                    val precioPorKilo = partes[2].toFloat()
                    val fechaCaducidad = Date(partes[3].toLong())
                    val caloriasPorKilo = partes[4].toInt()
                    ingredientes[counter++] = Ingrediente(nombre, esImportado, precioPorKilo, fechaCaducidad, caloriasPorKilo)
                }
            }
        }
    }
    override fun guardarDatos() {
        val file = File(rutaArchivo)
        FileWriter(file, false).use { fileWriter ->
            BufferedWriter(fileWriter).use { out ->
                ingredientes.values.forEach {
                    out.write("${it.nombre},${it.esImportado},${it.precioPorKilo},${it.fechaCaducidad.time},${it.caloriasPorKilo}\n")
                }
            }
        }
    }

    fun agregarIngrediente(ingrediente: Ingrediente) {
        ingredientes[ingredientes.size] = ingrediente
        guardarDatos()
    }

    fun obtenerIngredientePorNombre(nombre: String): Ingrediente? {
        return ingredientes.values.find { it.nombre == nombre }
    }

    fun eliminarIngredientePorNombre(nombre: String) {
        val idAEliminar = ingredientes.entries.find { it.value.nombre == nombre }?.key
        idAEliminar?.let { ingredientes.remove(it) }
        guardarDatos()
    }

    fun actualizarIngrediente(nombre: String, nuevoIngrediente: Ingrediente) {
        val ingredienteAActualizar  = ingredientes.entries.find { it.value.nombre == nombre }

        if (ingredienteAActualizar != null) {
            ingredientes[ingredienteAActualizar.key] = nuevoIngrediente
            guardarDatos()
        }
    }

    fun obtenerIngredientes(): List<Ingrediente> {
        return ingredientes.values.toList()
    }
}