package com.example.application

class BBaseDatosMemoria {

    //EMPEZAR EL COMPANION OBJECT

    companion object{
        val arregloBEntrenador = arrayListOf<BEntrenador>()

        init{
            arregloBEntrenador.add(BEntrenador(1, "Pancho", "a@a.com"))
            arregloBEntrenador.add(BEntrenador(2, "Jose", "b@b.com"))
            arregloBEntrenador.add(BEntrenador(3, "Daniel", "c@c.com"))
        }
    }
}