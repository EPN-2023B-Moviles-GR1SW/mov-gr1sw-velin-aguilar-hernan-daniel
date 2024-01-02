package com.example.application

class BEntrenador(var id: Int,
                  var nombre: String?,
                  var descripcion: String?) {

    override fun toString(): String {
        return "${nombre} - ${descripcion}"
    }
}