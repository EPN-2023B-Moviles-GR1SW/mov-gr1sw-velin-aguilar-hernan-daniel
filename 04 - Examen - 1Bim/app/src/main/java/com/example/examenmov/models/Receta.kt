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

    fun agregarIngrediente(nombre: String, precioPorKilo: Double, caloriasPorKilo : Int, importado : Boolean) {
        val nuevoIngrediente = Ingrediente(precioPorKilo, nombre ,caloriasPorKilo, importado)
        ingredientes.add(nuevoIngrediente)
        println("Ingrediente agregado: Ingrediente: $nombre, Precio: $precioPorKilo, Es importado: $importado")
    }

    fun editarIngrediente(nombre: String, nuevoPrecio: Double) {
        val ingrediente = ingredientes.find { it.nombre == nombre }
        if (ingrediente != null) {
            ingrediente.precioPorKilo = nuevoPrecio
            println("Ingrediente editado: Ingrediente: $nombre, Nuevo precio: $nuevoPrecio")
        } else {
            println("No se encontró el ingrediente $nombre para editarlo")
        }
    }

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