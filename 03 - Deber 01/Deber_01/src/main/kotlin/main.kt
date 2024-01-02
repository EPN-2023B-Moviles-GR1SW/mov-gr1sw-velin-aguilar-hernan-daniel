import Entities.Ingrediente
import Entities.Receta
import UseCase.IngredientesUseCase
import UseCase.RecetasUseCase
import java.text.SimpleDateFormat
import java.util.*

val ingredientesUseCase = IngredientesUseCase()
val recetasUseCase  = RecetasUseCase()
val formatoFecha = SimpleDateFormat("dd/MM/yyyy")

fun main()
{
    var opcionMenu : Int = -1
    while(opcionMenu != 0){
        println("A qué registros desea acceder?")
        println("1 Ingredientes")
        println("2 Recetas")
        println("0 Salir")
        opcionMenu = readln().toInt()

        when(opcionMenu){
            0 -> { println("Hasta luego :)") }
            1 -> { menuIngredientes() }
            2 -> { menuRecetas() }
            else -> { println("ERROR! La opción seleccionada no es válida.") }
        }
    }
}

fun menuIngredientes(){
    var opcionMenu = -1

    println("Qué deseas hacer?")
    println("1 Ver Registros de Ingredientes")
    println("2 Agregar Registro de Ingrediente")
    println("3 Actualizar Registro")
    println("4 Eliminar Registro por Nombre")
    opcionMenu = readln().toInt()
    println(opcionMenu)

    when(opcionMenu){
        1 -> {
            ingredientesUseCase.obtenerIngredientes().forEach{
                println(it.toString())
            }
        }
        2 -> {
            print("Nombre: ")
            val nombre : String = readln()

            print("Es Importado?: ")
            var temp = readln()
            val esImportado : Boolean = temp.equals("s")

            print("Precio por kilo: ")
            val precio = readln().toFloat()

            print("Fecha caducidad: ")
            val fechaCaducidad = readln()

            print("Calorías por kilo: ")
            val calorias = readln().toInt()

            ingredientesUseCase.agregarIngrediente(Ingrediente(nombre, esImportado, precio, formatoFecha.parse(fechaCaducidad), calorias))
        }
        3 -> {
            print("Qué registro desea actualizar: ")
            val nombre : String = readln()

            println("Actualización de información:")
            print("Es Importado?: ")
            var temp = readln()
            val esImportado : Boolean = temp.equals("s")

            print("Precio por kilo: ")
            val precio = readln().toFloat()

            print("Fecha caducidad: ")
            val fechaCaducidad = readln()

            print("Calorías por kilo: ")
            val calorias = readln().toInt()

            ingredientesUseCase.actualizarIngrediente(nombre, Ingrediente(nombre, esImportado, precio, formatoFecha.parse(fechaCaducidad), calorias))

        }
        4 -> {
            print("Nombre del ingrediente a eliminar: ")
            ingredientesUseCase.eliminarIngredientePorNombre(readln())
        }
        else -> {
            println("ERROR! La opción seleccionada no es válida.")
        }
    }
}

fun menuRecetas() {
    var opcionMenu = -1

    println("Qué deseas hacer?")
    println("1 Ver Registros de Recetas")
    println("2 Agregar Registro de Receta")
    println("3 Actualizar Registro")
    println("4 Eliminar Registro por Nombre")
    println("5 Agregar un ingrediente a una receta")
    println("6 Eliminar un ingrediente de una receta")
    opcionMenu = readln().toInt()

    when (opcionMenu) {
        1 -> {
            recetasUseCase.obtenerRecetas().forEach {
                println(it.toString())
            }
        }
        2 -> {
            print("Nombre: ")
            val nombre: String = readln()

            print("Es un plato de temporada?: ")
            val temp = readln()
            val esPlatoTemporada: Boolean = temp.equals("s")

            print("Precio: ")
            val precio = readln().toFloat()

            print("Porciones por plato: ")
            val porciones = readln().toInt()

            recetasUseCase.agregarReceta(Receta(nombre, esPlatoTemporada, precio, Date(), porciones))
        }
        3 -> {
            print("Qué registro desea actualizar: ")
            val nombre: String = readln()

            println("Actualización de información:")
            print("Es un plato de temporada?: ")
            val temp = readln()
            val esPlatoTemporada: Boolean = temp.equals("s")

            print("Precio: ")
            val precio = readln().toFloat()

            print("Fecha de agregación al menú: ")
            val fecha = readln()

            print("Porciones por plato: ")
            val porciones = readln().toInt()

            recetasUseCase.actualizarReceta(
                nombre,
                Receta(nombre, esPlatoTemporada, precio, formatoFecha.parse(fecha), porciones)
            )

        }
        4 -> {
            print("Nombre de la receta a eliminar: ")
            recetasUseCase.eliminarRecetasPorNombre(readln())
        }
        5 ->{
            println("Ingrese el nombre de la receta:")
            var receta : String = readln()
            println("Ingrese el nombre del ingrediente:")
            var ingrediente : String = readln()

            recetasUseCase.agregarIngrediente(receta,ingrediente)
        }
        6 ->{
            println("Ingrese el nombre de la receta:")
            var receta : String = readln()
            println("Ingrese el nombre del ingrediente:")
            var ingrediente : String = readln()

            recetasUseCase.eliminarIngrediente(receta, ingrediente)
        }
        else -> {
            println("ERROR! La opción seleccionada no es válida.")
        }
    }
}