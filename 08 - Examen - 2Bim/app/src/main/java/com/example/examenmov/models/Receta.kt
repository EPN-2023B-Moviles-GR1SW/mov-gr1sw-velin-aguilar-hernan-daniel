package com.example.examen.models

import java.util.Date

class Receta(
    var nombre: String = "",
    var esPlatoTemporada: Boolean? = null,
    var precio: Float = 0f,
    var fechaAgregacionMenu: Date = Date(),
    var porcionesPlato: Int = 0,
    var ingredientes: MutableList<Ingrediente> = mutableListOf()
) {
    constructor(nombre: String, esPlatoTemporada: Boolean?, precio: Float, porcionesPlato: Int) :
            this(nombre, esPlatoTemporada, precio, Date(), porcionesPlato, mutableListOf())

    constructor(nombre: String, precio: Float, porcionesPlato: Int) :
            this(nombre, false, precio, Date(), porcionesPlato, mutableListOf())

}