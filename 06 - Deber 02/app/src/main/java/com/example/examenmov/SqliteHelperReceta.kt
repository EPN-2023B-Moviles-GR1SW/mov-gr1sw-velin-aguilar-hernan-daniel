package com.example.examenmov

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.examen.models.Receta
import java.util.Date


class SqliteHelperReceta (contexto : Context?,):
    SQLiteOpenHelper(contexto, "dbReceta", null, 1){


        override fun onCreate(db: SQLiteDatabase?) {
            val scriptSQLCrearTablaReceta = """
            CREATE TABLE RECETA (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT NOT NULL,
                esPlatoTemporada INTEGER,
                precio REAL NOT NULL,
                fechaAgregacionMenu TEXT NOT NULL,
                porcionesPlato INTEGER NOT NULL
            )
        """.trimIndent()

            db?.execSQL(scriptSQLCrearTablaReceta)
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldversion: Int, newversion: Int) {
            TODO("Not yet implemented")
        }

        fun crearReceta(
            nombre : String,
            esPlatoTemporada : Int,
            precio : Float,
            fechaAgregacion : String,
            porciones : Int
        ) : Boolean {
            val basedatosEscritura = writableDatabase
            val valoresAGuardar = ContentValues()
            valoresAGuardar.put("nombre", nombre)
            valoresAGuardar.put("esPlatoTemporada", esPlatoTemporada)
            valoresAGuardar.put("precio", precio)
            valoresAGuardar.put("fechaAgregacionMenu", fechaAgregacion)
            valoresAGuardar.put("porcionesPlato", porciones)

            val resultadoGuardar = basedatosEscritura.insert(
                "RECETA", null, valoresAGuardar
            )
            basedatosEscritura.close()
            return (resultadoGuardar.toInt() != -1 )

        }

        fun eliminarReceta(
            nombre : String?
        ) : Boolean {
            val conexionEscritura = writableDatabase
            val parametrosConsultaDelete = arrayOf(nombre.toString())
            val resultadoEliminacion = conexionEscritura.delete(
                "RECETA", "nombre=?", parametrosConsultaDelete
            )

            writableDatabase.close()

            return resultadoEliminacion.toInt() == -1
        }


        fun actualizarReceta(
            nombre : String,
            esPlatoTemporada : Int,
            precio : Float,
            fechaAgregacion : String,
            porciones : Int,
            id : Int
        ) : Boolean{
            val conexionEscritura = writableDatabase
            val valoresAActualizar = ContentValues()
            valoresAActualizar.put("nombre", nombre)
            valoresAActualizar.put("esPlatoTemporada", esPlatoTemporada)
            valoresAActualizar.put("precio", precio)
            valoresAActualizar.put("fechaAgregacionMenu", fechaAgregacion)
            valoresAActualizar.put("porcionesPlato", porciones)
            valoresAActualizar.put("id", id)


            val parametrosAActualizar = arrayOf(id.toString())
            val resultadoActualizacion = conexionEscritura.update(
                "RECETA",
                valoresAActualizar,
                "id=?",
                parametrosAActualizar
            )
            conexionEscritura.close()
            print("aguacate 1111")
            return resultadoActualizacion.toInt() != -1
        }

        fun consultarRecetaPorID(id : Int) : Receta? {
            var baseDatosLectura = readableDatabase
            val scritConsultaLectura = """
            SELECT * FROM RECETA WHERE ID = ?
            """.trimIndent()
            val parametrosConsultaLectura = arrayOf(id.toString())
            val resultadoConsultaLectura = baseDatosLectura.rawQuery(
                scritConsultaLectura,
                parametrosConsultaLectura
            )

            val existeUsuario = resultadoConsultaLectura.moveToFirst()
            var recetaEncontrada: Receta? = null

            if (resultadoConsultaLectura.moveToFirst()) {
                val nombre = resultadoConsultaLectura.getString(0)
                val esPlatoTemporada =
                    resultadoConsultaLectura.getInt(1) != 0 // Suponiendo que 1 = true, 0 = false
                val precio = resultadoConsultaLectura.getFloat(2)
                val fechaAgregacionMenu =
                    Date(resultadoConsultaLectura.getString(3)) // Asumiendo que la fecha está almacenada en formato de texto
                val porcionesPlato = resultadoConsultaLectura.getInt(4)

                recetaEncontrada = Receta(
                    nombre,
                    esPlatoTemporada,
                    precio,
                    fechaAgregacionMenu,
                    porcionesPlato,
                    mutableListOf()
                )
            }

            resultadoConsultaLectura.close()
            baseDatosLectura.close()
            return recetaEncontrada
        }

    fun consultarRecetas(): MutableMap<Int, Receta> {
        val mapaRecetas = mutableMapOf<Int, Receta>()
        val baseDatosLectura = this.readableDatabase
        val scriptConsultaRecetas = "SELECT * FROM Receta"
        val resultadoConsultaRecetas = baseDatosLectura.rawQuery(scriptConsultaRecetas, null)
        if (resultadoConsultaRecetas.moveToFirst()) {
            do {
                val id = resultadoConsultaRecetas.getInt(0)
                val nombre = resultadoConsultaRecetas.getString(1)
                val esPlatoTemporada = resultadoConsultaRecetas.getInt(2) > 0
                val precio = resultadoConsultaRecetas.getFloat(3)
                val fechaAgregacionMenu = resultadoConsultaRecetas.getString(4)
                val porcionesPlato = resultadoConsultaRecetas.getInt(5)

                val receta = Receta(nombre, esPlatoTemporada, precio, porcionesPlato)
                mapaRecetas[id] = receta
            } while (resultadoConsultaRecetas.moveToNext())
        }
        resultadoConsultaRecetas.close()
        baseDatosLectura.close()

        return mapaRecetas
    }

}