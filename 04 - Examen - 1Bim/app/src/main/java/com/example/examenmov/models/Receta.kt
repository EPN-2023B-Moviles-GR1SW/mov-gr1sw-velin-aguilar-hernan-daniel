package com.example.examen.models

import java.util.Date

class Receta(
    var nombre: String,
    var esPlatoTemporada: Boolean?,
    var precio: Float,
    val fechaAgregacionMenu: Date,
    var porcionesPlato: Int,
    val ingredientes: MutableList<Ingrediente>
) {
    constructor(nombre: String, esPlatoTemporada: Boolean?, precio: Float, porcionesPlato: Int) :
            this(nombre, esPlatoTemporada, precio, Date(), porcionesPlato, mutableListOf())

    constructor(nombre: String, precio: Float, porcionesPlato: Int) :
            this(nombre, false, precio, Date(), porcionesPlato, mutableListOf())

    fun obtenerIngredientes(): MutableList<Ingrediente> {
        return ingredientes
    }
    fun verIngredientes() {
        if(ingredientes.isNotEmpty()){
            ingredientes.forEach{
                println(it.nombre)
            }
        }else{
            println("No se tienen ingredientes asociados")
        }



    }

    // Método para agregar una calificación
    fun agregarIngrediente(nombre: String, precioPorKilo: Double, caloriasPorKilo : Int) {
        val nuevoIngrediente = Ingrediente(precioPorKilo, null, nombre ,caloriasPorKilo)
        ingredientes.add(nuevoIngrediente)
        println("Ingrediente agregado: Ingrediente: $nombre, Precio: $precioPorKilo")
    }

    // Método para editar una calificación por materia
    fun editarIngrediente(nombre: String, nuevoPrecio: Double) {
        val ingrediente = ingredientes.find { it.nombre == nombre }
        if (ingrediente != null) {
            ingrediente.precioPorKilo = nuevoPrecio
            println("Ingrediente editado: Ingrediente: $nombre, Nuevo precio: $nuevoPrecio")
        } else {
            println("No se encontró el ingrediente $nombre para editarlo")
        }
    }

    // Método para eliminar una calificación por materia
    fun eliminarIngrediente(nombre: String) {
        val ingrediente = ingredientes.find { it.nombre == nombre }
        if (ingrediente != null) {
            ingredientes.remove(ingrediente)
            println("Ingrediente eliminado: Ingrediente: $nombre")
        } else {
            println("No se encontró el ingrediente $nombre para eliminarlo")
        }
    }

}