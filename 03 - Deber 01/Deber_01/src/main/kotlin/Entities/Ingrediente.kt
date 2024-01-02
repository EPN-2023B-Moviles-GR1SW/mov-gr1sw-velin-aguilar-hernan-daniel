package Entities
import java.util.*

class Ingrediente(
    val nombre : String,
    var esImportado : Boolean,
    var precioPorKilo : Float,
    var fechaCaducidad : Date,
    var caloriasPorKilo : Int
)
{
    override fun toString(): String {
        return "Nombre: ${nombre} \nEs importado?: ${esImportado} \nPrecio por kilo: ${precioPorKilo}" +
                "\nFecha de caducidad: ${fechaCaducidad} \nCalorias por kilo: ${caloriasPorKilo}\n"
    }
}