package UseCase

import Entities.Ingrediente
import Repositories.IngredientesRepository

class IngredientesUseCase {
    val repository = IngredientesRepository()

    fun agregarIngrediente(ingrediente: Ingrediente) : Boolean{

        if(repository.obtenerIngredientePorNombre(ingrediente.nombre) != null) {
            println("ERROR! El ingrediente YA se encuentra registrada")
            return false
        }

        repository.agregarIngrediente(ingrediente)
        println("Registro agregado exitosamente")
        return true
    }

    fun obtenerIngredientes(): List<Ingrediente> {
        return repository.obtenerIngredientes()
    }

    fun eliminarIngredientePorNombre(nombre: String) : Boolean {
        if(repository.obtenerIngredientePorNombre(nombre) == null) {
            println("ERROR! El ingrediente NO se encuentra registrada")
            return false
        }
        repository.eliminarIngredientePorNombre(nombre)
        println("Registro eliminado con éxito")
        return true
    }

    fun actualizarIngrediente(nombre: String, dto : Ingrediente) : Boolean {
        if(repository.obtenerIngredientePorNombre(nombre) == null){
            println("ERROR! El ingrediente NO se encuentra registrada")
            return false
        }

        repository.actualizarIngrediente(nombre, dto)
        println("Registro actualizado exitosamente")
        return true
    }
}