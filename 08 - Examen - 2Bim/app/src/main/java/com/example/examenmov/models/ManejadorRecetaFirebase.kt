package com.example.examen.models

import com.google.firebase.firestore.FirebaseFirestore
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.util.Date

public class ManejadorRecetaFirebase {
    companion object {
        val listaRecetas: MutableMap<String, Receta> = mutableMapOf()
        private val db = FirebaseFirestore.getInstance()

        fun isRegistered(nombreReceta: String) : Boolean {
            var returnValue : Boolean = false
            db.collection("recetas")
                .whereEqualTo("nombre", nombreReceta)
                .get()
                .addOnSuccessListener { documentos ->
                    if (!documentos.isEmpty) {
                        returnValue = true
                    }
                }
                .addOnFailureListener { e ->
                    println("Error al verificar la existencia de la receta: $e")
                }

            return returnValue
        }

        fun agregarReceta(
            nombre: String, precio: Float, porcionesPorPlato: Int = 1,
            paralelo: String = "Por editar", diaCum: Int? = null
        ) {
            val receta = Receta(nombre, precio, porcionesPorPlato)
            db.collection("recetas").add(receta)
                .addOnSuccessListener { documentReference ->
                    listaRecetas.put(documentReference.id, receta)
                    println("Receta agregada con ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    println("Error agregando receta: $e")
                }
        }

        fun eliminarReceta(nombreReceta: String) {
            db.collection("recetas")
                .whereEqualTo("nombre", nombreReceta)
                .get()
                .addOnSuccessListener { documentos ->
                    for (documento in documentos) {
                        db.collection("recetas").document(documento.id)
                            .delete()
                            .addOnSuccessListener {
                                listaRecetas.remove(documento.id)
                                println("Receta eliminada correctamente")
                            }
                            .addOnFailureListener { e ->
                                println("Error eliminando receta: $e")
                            }
                    }
                    if (documentos.isEmpty) {
                        println("No se encontraron recetas con el nombre: $nombreReceta")
                    }
                }
                .addOnFailureListener { e ->
                    println("Error al buscar recetas para eliminar: $e")
                }
        }

        fun obtenerLista(): MutableMap<String, Receta>{
            println("Iniciando lectura de documentos...")
            db.collection("recetas")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        try {
                            val receta = document.toObject(Receta::class.java)
                            listaRecetas[document.id] = receta
                        } catch (e: Exception) {
                            println("Error al convertir documento: ${document.id} a Receta")
                        }
                    }
                }
                .addOnFailureListener { exception ->
                    println("Error al obtener documentos: $exception")
                }
            return listaRecetas
        }

        fun editarReceta(
            idReceta: String,
            nuevoNombre: String? = null,
            nuevoPrecio: Float? = null,
        ) {

            db.collection("recetas").document(idReceta)
                .update(
                    mapOf(
                        "nombre" to nuevoNombre,
                        "precio" to nuevoPrecio,
                    )
                )
                .addOnSuccessListener {
                    println("Receta actualizada correctamente en Firestore.")
                    // Opcionalmente, actualiza tu caché local si lo necesitas
                    listaRecetas[idReceta]?.apply {
                        nuevoNombre?.let { nombre = it }
                        nuevoPrecio?.let { precio = it }
                    }
                }
                .addOnFailureListener { e ->
                    println("Error al actualizar la receta: $e")
                }
        }

    }
}