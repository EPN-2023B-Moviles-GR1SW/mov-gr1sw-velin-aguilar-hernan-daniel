package com.example.examen.models

import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

public class ManejadorReceta {
    companion object {
        val listaRecetas: MutableMap<Int, Receta> = mutableMapOf()

        init {
            cargarDatos()
            //agregarReceta("Salmon",10.2F, 145)
            //agregarReceta("Queso",3.4F, 145)
            //listaRecetas[1]?.agregarIngrediente("Pan",5.2, 145, false)

        }
        fun obtenerLista(): MutableMap<Int, Receta> {
            return listaRecetas
        }
        fun agregarReceta(
            nombre:String, precio: Float, porcionesPorPlato: Int =1,
            paralelo:String="Por editar", diaCum: Int? =null) {
            val receta = Receta(nombre,precio,porcionesPorPlato)
            if (!listaRecetas.containsValue(receta)) {
                val newKey = if (listaRecetas.isEmpty()) 1 else listaRecetas.keys.maxOrNull()!! + 1
                listaRecetas[newKey] = receta
                println("Se agrego la receta")
            } else {
                println("ERROR! Estudiante ya existente")
            }

        }

        fun obtenerIdReceta(nombre: String, precio: Float): List<Int> {
            val idsEncontrados = listaRecetas.entries
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
            val receta = listaRecetas[idReceta]

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

        fun eliminarReceta(idEstudiante: Int) {
            if (listaRecetas.containsKey(idEstudiante)) {
                listaRecetas.remove(idEstudiante)
                guardarDatos()
            } else {
                println("Error: El estudiante a eliminar no existe.")
            }
        }


        private fun guardarDatos() {
            ObjectOutputStream(FileOutputStream("datos.txt")).use {
                it.writeObject(listaRecetas)
                it.writeObject(listaRecetas)
            }
        }

        private fun cargarDatos() {
            println("CARGA DATOS")
            try {
                ObjectInputStream(FileInputStream("datos.txt")).use {
                    val estudiantesGuardados = it.readObject() as MutableMap<Int, Receta>
                    println("TODO OK")
                    listaRecetas.putAll(estudiantesGuardados)

                }
            } catch (e: FileNotFoundException) {
                println("YA NO VALE" + e.toString())
            }
        }
    }

}