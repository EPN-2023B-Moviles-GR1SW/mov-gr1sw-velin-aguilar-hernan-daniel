package Repositories

import java.io.File

abstract class Repository() {
    abstract protected var rutaArchivo : String

    abstract protected fun cargarDatos()

    abstract protected fun guardarDatos()
}