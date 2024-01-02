package UseCase

import Entities.Ingrediente
import Entities.Receta
import Repositories.IngredientesRepository
import Repositories.RecetasRepository

class RecetasUseCase {
    val repository = RecetasRepository()
    val ingredientes = IngredientesRepository()

    fun agregarReceta(receta: Receta) : Boolean{

        if(repository.obtenerRecetaPorNombre(receta.nombre) != null) {
            println("ERROR! La receta YA se encuentra registrado")
            return false
        }

        repository.agregarReceta(receta)
        println("Registro agregado exitosamente")
        return true
    }

    fun obtenerRecetas(): List<Receta> {
        return repository.obtenerRecetas()
    }

    fun eliminarRecetasPorNombre(nombre: String) : Boolean {
        if(repository.obtenerRecetaPorNombre(nombre) == null) {
            println("ERROR! La receta NO se encuentra registrado")
            return false
        }
        repository.eliminarRecetaPorNombre(nombre)
        println("Registro eliminado con éxito")
        return true
    }

    fun actualizarReceta(nombre: String, dto : Receta) : Boolean {
        if(repository.obtenerRecetaPorNombre(nombre) == null){
            println("ERROR! La receta NO se encuentra registrado")
            return false
        }

        repository.actualizarReceta(nombre, dto)
        println("Registro actualizado exitosamente")
        return true
    }

    fun agregarIngrediente(receta: String, nombreIngrediente : String) : Boolean{
        if(repository.obtenerRecetaPorNombre(receta) == null){
            println("ERROR! La receta NO se encuentra registrada")
            return false
        }
        if(ingredientes.obtenerIngredientePorNombre(nombreIngrediente) == null){
            println("ERROR! El ingrediente NO se encuentra registrado")
            return false
        }

        val ingrediente = ingredientes.obtenerIngredientePorNombre(nombreIngrediente)
        if (ingrediente != null) {
            repository.agregarIngrediente(receta, ingrediente)
            println("El ingrediente ha sido agregado exitosamente a la receta")
            return true
        }
        println("Ha ocurrido un error al agregar el ingrediente")
        return false
    }

    fun eliminarIngrediente(receta: String, nombreIngrediente : String) : Boolean{
        if(repository.obtenerRecetaPorNombre(receta) == null){
            println("ERROR! La receta NO se encuentra registrada")
            return false
        }
        if(ingredientes.obtenerIngredientePorNombre(nombreIngrediente) == null){
            println("ERROR! El ingrediente NO se encuentra registrado")
            return false
        }

        val ingrediente = ingredientes.obtenerIngredientePorNombre(nombreIngrediente)
        if (ingrediente != null) {
            repository.eliminarIngrediente(receta, ingrediente)
            println("El ingrediente ha sido eliminado exitosamente de la receta")
            return true
        }
        println("Ha ocurrido un error al agregar el ingrediente")
        return false
    }
}