package com.example.examen.models

import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

public class ManejadorReceta {
    companion object {
        val listaIngredientes: MutableMap<Int, Receta> = mutableMapOf()

        init {
            //cargarDatos()
            agregarReceta("Salmon",10.2F, 145)
            agregarReceta("Queso",3.4F, 145)
            listaIngredientes[1]?.agregarIngrediente("Pan",5.2, 145)

        }
        fun obtenerLista(): MutableMap<Int, Receta> {
            return listaIngredientes
        }
        fun agregarReceta(
            nombre:String, precio: Float, porcionesPorPlato: Int =1,
            paralelo:String="Por editar", diaCum: Int? =null) {
            val receta = Receta(nombre,precio,porcionesPorPlato)
            if (!listaIngredientes.containsValue(receta)) {
                val newKey = if (listaIngredientes.isEmpty()) 1 else listaIngredientes.keys.maxOrNull()!! + 1
                listaIngredientes[newKey] = receta
                println("Se agrego la receta")
            } else {
                println("ERROR! Estudiante ya existente")
            }

        }

        fun obtenerIdReceta(nombre: String, precio: Float): List<Int> {
            val idsEncontrados = listaIngredientes.entries
                .filter { it.value.nombre == nombre || it.value.precio == precio  }
                .map { it.key }

            return if (idsEncontrados.isNotEmpty()) {
                println("Se encontraron recetas con el nombre $nombre. IDs: $idsEncontrados")
                idsEncontrados
            } else {
                println("No se encontraron recetas con el nombre $nombre")
                emptyList()
            }
        }




        fun editarReceta(idReceta: Int,
                         nuevoNombre: String? = null,
                         nuevoApellido: String? = null,
                         nuevoCurso: Int? = null) {
            val receta = listaIngredientes[idReceta]

            if (receta != null) {
                // Actualizar solo los campos proporcionados
                nuevoNombre?.let { receta.nombre = it }
                nuevoApellido?.let { receta.precio = it.toFloat() }
                nuevoCurso?.let { receta.porcionesPlato = it}

                println("Receta con ID $idReceta editado exitosamente: $receta")
            } else {
                println("Receta no encontrado con el ID $idReceta")
            }
        }

        fun eliminarEstudiante(idEstudiante: Int) {
            if (listaIngredientes.containsKey(idEstudiante)) {
                listaIngredientes.remove(idEstudiante)
                //guardarDatos()
            } else {
                println("Error: El estudiante a eliminar no existe.")
            }
        }

        fun verListaEstudiantes() {
            println("Lista de Recetas y sus ingredientes:")
            for ((key, receta) in listaIngredientes) {
                println("Receta-ID:${key}: ${receta.nombre} ${receta.precio}")
                if (receta.ingredientes.isNotEmpty()){
                    receta.verIngredientes()
                }
                else{
                    println("Sin ingredientes")
                }

            }
        }


        private fun guardarDatos() {
            ObjectOutputStream(FileOutputStream("datos.txt")).use {
                it.writeObject(listaIngredientes)
                it.writeObject(listaIngredientes)
            }
        }

        private fun cargarDatos() {
            try {
                ObjectInputStream(FileInputStream("datos.txt")).use {
                    val estudiantesGuardados = it.readObject() as MutableMap<Int, Receta>

                    listaIngredientes.putAll(estudiantesGuardados)

                }
            } catch (e: FileNotFoundException) {
                // Manejar la excepción si el archivo no existe (primera ejecución)
            }
        }
    }

}