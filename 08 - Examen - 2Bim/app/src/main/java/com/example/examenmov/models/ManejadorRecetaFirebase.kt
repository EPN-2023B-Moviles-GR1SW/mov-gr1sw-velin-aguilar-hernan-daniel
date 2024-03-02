package com.example.examen.models

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

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

        fun eliminarIngrediente(idReceta: String, nombreIngrediente: String) {
            db.collection("recetas").document(idReceta).collection("ingredientes")
                .whereEqualTo("nombre", nombreIngrediente)
                .get()
                .addOnSuccessListener { documentos ->
                    if (documentos.isEmpty) {
                        println("No se encontró el ingrediente con nombre: $nombreIngrediente")
                        return@addOnSuccessListener
                    }

                    for (documento in documentos) {
                        db.collection("recetas").document(idReceta).collection("ingredientes").document(documento.id)
                            .delete()
                            .addOnSuccessListener {
                                println("Ingrediente $nombreIngrediente eliminado correctamente")
                            }
                            .addOnFailureListener { e ->
                                println("Error eliminando ingrediente: $nombreIngrediente, $e")
                            }
                    }
                }
                .addOnFailureListener { e ->
                    println("Error buscando ingrediente $nombreIngrediente para eliminar: $e")
                }
        }


        fun eliminarReceta(nombreReceta: String) {
            db.collection("recetas")
                .whereEqualTo("nombre", nombreReceta)
                .get()
                .addOnSuccessListener { documentos ->
                    for (documento in documentos) {
                        var idDocumento = documento.id
                        db.collection("recetas").document(documento.id)
                            .delete()
                            .addOnSuccessListener {
                                listaRecetas.remove(documento.id)
                                println("Receta eliminada correctamente")
                                db.collection("recetas").document(documento.id).collection("ingredientes")
                                    .get()
                                    .addOnSuccessListener { ingredientesReceta ->
                                        for (ingrediente in ingredientesReceta) {
                                            db.collection("recetas").document(documento.id).collection("ingredientes").document(ingrediente.id)
                                                .delete()
                                                .addOnSuccessListener {
                                                    println("Documento ${ingrediente.id} eliminado correctamente")
                                                }
                                                .addOnFailureListener { e ->
                                                    println("Error eliminando documento: ${ingrediente.id}, $e")
                                                }
                                        }
                                        if (ingredientesReceta.isEmpty) {
                                            println("La subcolección está vacía o no existe.")
                                        }
                                    }
                                    .addOnFailureListener { e ->
                                        println("Error al buscar documentos para eliminar en la subcolección: $e")
                                    }
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

        fun obtenerListaRecetas(): MutableMap<String, Receta>{
            println("Iniciando lectura de documentos...")
            db.collection("recetas")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        try {
                            val receta = document.toObject(Receta::class.java)
                            //receta.ingredientes = document;
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

        suspend fun obtenerListaIngredientes(id: String): MutableList<Ingrediente>? = withContext(
            Dispatchers.IO) {
            val listaIngredientes: MutableList<Ingrediente> = mutableListOf()
            try {
                val result = db.collection("recetas").document(id).collection("ingredientes").get().await()
                for (document in result) {
                    val ingrediente = document.toObject(Ingrediente::class.java)
                    listaIngredientes.add(ingrediente)
                }
            } catch (e: Exception) {
                println("Error al obtener documentos: $e")
            }
            listaIngredientes
        }

        fun agregarIngrediente(id : String, nombre: String, precioPorKilo: Double,
                               caloriasPorKilo : Int, importado : Boolean){
            val nuevoIngrediente = Ingrediente(precioPorKilo, nombre ,caloriasPorKilo, importado)
            db.collection("recetas").document(id).collection("ingredientes")
                .add(nuevoIngrediente)
                .addOnSuccessListener { documentReference ->
                    println("Ingrediente agregado con ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    println("Error agregando receta: $e")
                }
        }

        fun editarReceta(idReceta: String, nuevoNombre: String? = null, nuevoPrecio: Float? = null, ) {
            db.collection("recetas").document(idReceta)
                .update(
                    mapOf(
                        "nombre" to nuevoNombre,
                        "precio" to nuevoPrecio,
                    )
                )
                .addOnSuccessListener {
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