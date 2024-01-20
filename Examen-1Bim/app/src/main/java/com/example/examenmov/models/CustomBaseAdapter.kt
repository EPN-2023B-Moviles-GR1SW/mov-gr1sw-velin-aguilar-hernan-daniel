package com.example.examen.models

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.examenmov.R


class CustomBaseAdapter(private val context: Context, private val datos: MutableMap<Int, Receta>) : BaseAdapter() {

    // Retorna la cantidad de elementos en tu conjunto de datos
    override fun getCount(): Int {
        return datos.size
    }

    // Retorna el objeto en la posición especificada en tu conjunto de datos
    override fun getItem(position: Int): Receta? {
        return datos[position]
    }

    // Retorna el ID asociado con el objeto en la posición especificada
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    // Crea y retorna una vista para cada elemento en el conjunto de datos
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var vista = convertView
        if (vista == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            vista = inflater.inflate(R.layout.activity_custom_list_item, null)
        }

        // Personaliza la vista con los datos del elemento en la posición actual
        val tv_nombre = vista?.findViewById<TextView>(R.id.tv_nombre)
        val tv_apellido = vista?.findViewById<TextView>(R.id.tv_precio)
        tv_nombre?.text = datos[position + 1]?.nombre
        tv_apellido?.text = datos[position + 1]?.precio.toString()
        return vista!!
    }

}