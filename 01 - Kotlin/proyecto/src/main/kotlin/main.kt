import java.util.*
import kotlin.collections.ArrayList

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

    //val coqueteo = if(estadoCivilWhen != "C") "SI" else "NO"

    //TIPOS DE ARREGLOS
    //ESTÁTICO
    val arregloEstatico : Array<Int> = arrayOf(1, 2, 3)
    println(arregloEstatico)
    //DINÁMICO
    val arregloDinamico : ArrayList<Int> = arrayListOf(1, 2, 3, 4, 5, 6, 7, 8, 9)

    println(arregloDinamico)
    arregloDinamico.add(10)
    arregloDinamico.add(11)
    println(arregloDinamico)

    //OPERADORES DE ARREGLOS
    //For Each -> Unit
    //Iterar un arreglo
    val respuestaForEach : Unit = arregloDinamico.forEach{
        valorActual : Int -> println("Valor Actual : ${valorActual}")
    }

    //it -> elemento iterado
    //arregloDinamico.forEach(println(it))

    arregloEstatico.forEachIndexed(){ indice : Int, valorActual ->
        println("Valor Actual : ${valorActual} Indice : ${indice}")
    }

    //MAP --> GENERA OTRO ARREGLO
    val respuestaMap : kotlin.collections.List<Double> = arregloDinamico.map { valorActual : Int ->
        return@map valorActual.toDouble() + 100}
    val respuestaMapDos = arregloDinamico.map { it + 100}

    //FILTER --> RETORNA SOLO LOS ELEMENTOS QUE CUMPLEN CON LA CONDICION ESTABLECIDA
    val respuestaFilter : kotlin.collections.List<Int> = arregloDinamico.filter { valorActual : Int ->
        return@filter valorActual > 5}
    val respuestaFilterDos = arregloDinamico.filter { it > 5}

    //OR (Any -> Alguno cumple) ------ AND (All -> todos cumplen)
    val respuestaAny : Boolean = arregloDinamico.any{valorActual : Int -> return@any (valorActual > 5)}
    val respuestaAll : Boolean = arregloDinamico.all{valorActual : Int -> return@all (valorActual > 5)}

    //REDUCE
    val respuestaReduce : Int = arregloDinamico.reduce{
        acumulado : Int,
        valorActual -> return@reduce (acumulado + valorActual)
    }
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

    public fun sumar() : Int{
        val total = numeroUno + numeroDos
        agregarAlHistorial(total)
        return total;
    }

    companion object{
        val pi = 3.14;

        fun elevarAlCuadrado(num : Int) : Int{
            return num * num;
        }

        val historialSuma = arrayListOf<Int>()

        fun agregarAlHistorial(valorNuevoSuma : Int){
            historialSuma.add(valorNuevoSuma)
        }
    }

}
