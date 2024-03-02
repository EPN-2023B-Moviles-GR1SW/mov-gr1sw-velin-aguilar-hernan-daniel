package com.example.examen.models

import java.util.Date

class Ingrediente(
    var precioPorKilo: Double = 0.0,
    val esImportado: Boolean? = null,
    val nombre: String = "",
    val caloriasPorKilo: Int? = 0,
    var fechaCaducidad: Date? = null
) {
    constructor(precioPorKilo: Double, nombre: String, caloriasPorKilo: Int?, esImportado: Boolean?) : this(
        precioPorKilo,
        esImportado,
        nombre,
        caloriasPorKilo,
        Date()
    )
    constructor() : this(0.0,"pendiente",0, false)
}