package com.example.examen.models

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.examenmov.R


class CustomBaseAdapter(private val context: Context, private val datos: MutableMap<Int, Receta>) : BaseAdapter() {

    override fun getCount(): Int {
        return datos.size
    }

    override fun getItem(position: Int): Receta? {
        return datos[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var vista = convertView
        if (vista == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            vista = inflater.inflate(R.layout.activity_custom_list_item, null)
        }

        val tv_nombre = vista?.findViewById<TextView>(R.id.tv_nombre)
        val tv_apellido = vista?.findViewById<TextView>(R.id.tv_precio)
        val item = datos.values.elementAt(position)
        tv_nombre?.text = item?.nombre
        tv_apellido?.text = item?.precio.toString()
        return vista!!
    }

}