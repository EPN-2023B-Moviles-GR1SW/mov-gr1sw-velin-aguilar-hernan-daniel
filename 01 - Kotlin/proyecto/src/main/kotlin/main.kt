import java.util.*

fun main(){

    //TIPOS VARIABLES
    //INMUTABLES (val, no se reasignan "=")
    val inmutable: String = "Daniel"

    //MUTABLES (var, se pueden reasignar)
    var mutable : String = "Velin"




    //VARIALBES PRIMITIVAS
    val nombreEstudiante : String = "Daniel Velin"
    val sueldo : Double = 0.25
    val estadoCivil : Char = 'S'
    val mayorEdad : Boolean = true

    //CLASES JAVA
    val fechaNacimiento : Date = Date()




    val estadoCivilWhen : String = "C"
    when(estadoCivilWhen){
        ("C") -> {
            println("Casado")
        }
        "S" -> {
            println("Soltero")
        }
        else -> {
            println("Viudo")
        }
    }

    val coqueteo = if(estadoCivilWhen != "C") "SI" else "NO"

}