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

fun imprimirNombre(nombre : String) : Unit{
    println("Nombre : ${nombre}")
}

//? -> Nullable
fun calcularSueldo(sueldo : Double, tasa : Double = 12.00, bonoEspecial : Double? = null) : Double {
    if(bonoEspecial == null){
        return sueldo * (100/tasa)
    }
    else{
        bonoEspecial.dec()
        return sueldo * (100/tasa) + bonoEspecial
    }
}



abstract class NumerosJava{
    protected val numeroUno : Int
    private val numeroDos: Int

    constructor(uno : Int, dos : Int){
        this.numeroUno = uno
        this.numeroDos = dos
        println("Inicializado")
    }
}


abstract class Numeros(
    //uno : Int, //Parametro
    //Propiedades
    protected val numeroUno : Int,
    protected val numeroDos : Int
){
    init{
        this.numeroUno; this.numeroDos
        numeroUno; numeroDos
        println("Inicializando")
    }
}



class Suma(uno : Int, dos : Int) : Numeros(uno, dos){
    init{
        this.numeroUno;numeroUno;
        this.numeroDos;numeroDos;
    }

    //segundo constructor
    constructor(uno : Int? , dos : Int) : this(
        if(uno == null) 0 else uno,
        dos){
        numeroDos
        }

    //tercer constructor
    constructor(uno : Int , dos : Int?) : this(uno,
        if(dos == null) 0 else dos)
}
