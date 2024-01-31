package com.example.examenmov

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.examen.models.Ingrediente
import com.example.examen.models.Receta


class SqliteHelperIngrediente (contexto : Context?,):
    SQLiteOpenHelper(contexto, "dbIngrediente", null, 1){


        override fun onCreate(db: SQLiteDatabase?) {
            val scriptSQLCrearTablaIngrediente = """
            CREATE TABLE INGREDIENTE (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT NOT NULL,
                esImportado INTEGER,
                precioPorKilo REAL NOT NULL,
                fechaCaducidad TEXT NOT NULL,
                caloriasPorKilo INTEGER NOT NULL
            )
        """.trimIndent()

            val scriptSQLCrearTablaRecetaIngrediente = """
            CREATE TABLE RECETA_INGREDIENTE (
                receta_id INTEGER,
                ingrediente_id INTEGER,
                FOREIGN KEY (receta_id) REFERENCES Receta(id),
                FOREIGN KEY (ingrediente_id) REFERENCES Ingrediente(id)  
            )
        """.trimIndent()

            db?.execSQL(scriptSQLCrearTablaIngrediente)
            db?.execSQL(scriptSQLCrearTablaRecetaIngrediente)
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldversion: Int, newversion: Int) {
            // TODO:  
        }

        fun crearIngrediente(
            nombre : String,
            esImportado : Int,
            precioPorKilo : Float,
            fechaCaducidad : String,
            caloriasPorKilo : Int,
            key : Int
        ) : Boolean {
            val basedatosEscritura = writableDatabase
            val valoresAGuardar = ContentValues()
            valoresAGuardar.put("nombre", nombre)
            valoresAGuardar.put("esImportado", esImportado)
            valoresAGuardar.put("precioPorKilo", precioPorKilo.toFloat())
            valoresAGuardar.put("fechaCaducidad", fechaCaducidad)
            valoresAGuardar.put("caloriasPorKilo", caloriasPorKilo)

            val resultadoGuardar = basedatosEscritura.insert(
                "INGREDIENTE", null, valoresAGuardar
            )
            val nuevosValoresGuardar = ContentValues()
            nuevosValoresGuardar.put("receta_id", key)
            nuevosValoresGuardar.put("ingrediente_id", resultadoGuardar)
            val crossResultado = basedatosEscritura.insert("RECETA_INGREDIENTE", null, nuevosValoresGuardar)
            basedatosEscritura.close()
            return (resultadoGuardar.toInt() != -1 )

        }

        fun eliminarIngrediente(
            receta_id : Int?
        ) : Boolean {
            val conexionEscritura = writableDatabase
            val parametrosConsultaDelete = arrayOf(receta_id.toString())
            val parametrosConsultaExtra = arrayOf(receta_id.toString())
            val prueba = conexionEscritura.delete(
                "RECETA_INGREDIENTE", "receta_id=?", parametrosConsultaExtra
            )
            val resultadoEliminacion = conexionEscritura.delete(
                "INGREDIENTE", "id=?", parametrosConsultaDelete
            )

            writableDatabase.close()

            return resultadoEliminacion.toInt() == -1
        }


        fun actualizarIngrediente(
            nombre : String,
            esImportado : Int,
            precioPorKilo : Float,
            fechaCaducidad : String,
            caloriasPorKilo : Int,
            id : Int
        ) : Boolean{
            val conexionEscritura = writableDatabase
            val valoresAActualizar = ContentValues()
            valoresAActualizar.put("nombre", nombre)
            valoresAActualizar.put("esImportado", esImportado)
            valoresAActualizar.put("precioPorKilo", precioPorKilo)
            valoresAActualizar.put("fechaCaducidad", fechaCaducidad)
            valoresAActualizar.put("caloriasPorKilo", caloriasPorKilo)
            valoresAActualizar.put("id", id)


            val parametrosAActualizar = arrayOf(id.toString())
            val resultadoActualizacion = conexionEscritura.update(
                "INGREDIENTE",
                valoresAActualizar,
                "id=?",
                parametrosAActualizar
            )
            conexionEscritura.close()
            return resultadoActualizacion.toInt() != -1
        }

    fun consultarIngredientes(id : Int): MutableList<Ingrediente> {
        val mapaIngredientes = mutableMapOf<Int, Ingrediente>()
        val baseDatosLectura = this.readableDatabase
        val scriptConsultaRecetas = "SELECT * FROM Ingrediente JOIN Receta_Ingrediente ON " +
                "Ingrediente.id = Receta_Ingrediente.ingrediente_id WHERE Receta_Ingrediente.receta_id=?"
        val argsQuery = arrayOf(id.toString())
        val resultadoConsultaRecetas = baseDatosLectura.rawQuery(scriptConsultaRecetas, argsQuery)
        if (resultadoConsultaRecetas.moveToFirst()) {
            do {
                val id = resultadoConsultaRecetas.getInt(0)
                val nombre = resultadoConsultaRecetas.getString(1)
                val esImportado = resultadoConsultaRecetas.getInt(2) > 0
                val precioPorKilo = resultadoConsultaRecetas.getFloat(3)
                val fechaAgregacion = resultadoConsultaRecetas.getString(4)
                val caloriasPorKilo = resultadoConsultaRecetas.getInt(5)

                val ingrediente = Ingrediente(precioPorKilo.toDouble(), nombre, caloriasPorKilo, esImportado)
                mapaIngredientes[id] = ingrediente
            } while (resultadoConsultaRecetas.moveToNext())
        }
        resultadoConsultaRecetas.close()
        baseDatosLectura.close()

        return mapaIngredientes.values.toMutableList()
    }

    fun obtenerIdIngrediente(nombre: String): Int? {
        val baseDatosLectura = this.readableDatabase
        val scriptConsultaRecetas = "SELECT * FROM Ingrediente WHERE nombre=?"
        val argsQuery = arrayOf(nombre.toString())
        val id = baseDatosLectura.rawQuery(scriptConsultaRecetas, argsQuery)

        id.moveToPosition(0)
        baseDatosLectura.close()
        return id.getInt(0)
    }

}