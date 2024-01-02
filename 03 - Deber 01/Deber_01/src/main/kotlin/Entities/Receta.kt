package Entities

import java.util.*
import kotlin.collections.ArrayList

class Receta(
    val nombre : String,
    var esPlatoTemporada : Boolean,
    var precio : Float,
    var fechaAgregacionMenu : Date,
    var porcionesPlato : Int,
)
{
    var ingredientes : ArrayList<Ingrediente> = ArrayList<Ingrediente>()

    override fun toString(): String {
        var resultadoIngredientes : String = ""
        if(ingredientes.size != 0)
            resultadoIngredientes = ingredientes.joinToString("; ") { it.nombre }

        return "Nombre: ${nombre} \nEs plato de temporada?: ${esPlatoTemporada} \nPrecio: ${precio}" +
                "\nFecha de agregación al menú: ${fechaAgregacionMenu} \nPorciones por plato: ${porcionesPlato}" +
                "\nIngredientes: [${resultadoIngredientes}]\n"
    }
}