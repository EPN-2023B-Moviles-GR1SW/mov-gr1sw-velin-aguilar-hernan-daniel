package com.example.examen.models

import java.util.Date

class Ingrediente(
    var precioPorKilo: Double,
    val esImportado: Boolean?,
    val nombre: String,
    val caloriasPorKilo: Int?,
    var fechaCaducidad: Date?
) {
    constructor(precioPorKilo: Double, esImportado: Boolean?, nombre: String, caloriasPorKilo: Int?) : this(
        precioPorKilo,
        esImportado,
        nombre,
        caloriasPorKilo,
        Date()
    )
    constructor() : this(0.0, false,"pendiente",0,)
}