package com.example.examenmov.models

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.examen.models.Ingrediente
import com.example.examenmov.R

class BaseAdapterIngrediente(private val context: Context, private val datos: MutableList<Ingrediente>) :
    BaseAdapter() {
    override fun getCount(): Int {
        return datos.size
    }

    // Retorna el objeto en la posición especificada en tu conjunto de datos
    override fun getItem(position: Int): Ingrediente? {
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
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            vista = inflater.inflate(R.layout.ingredientes_items, null)
        }

        // Personaliza la vista con los datos del elemento en la posición actual
        val tv_nota = vista?.findViewById<TextView>(R.id.tv_precio_ingrediente)
        val tv_ingrediente = vista?.findViewById<TextView>(R.id.tv_ingrediente)
        val tv_sipasa = vista?.findViewById<TextView>(R.id.tv_sipasa)
        tv_ingrediente?.text = "${datos[position]?.nombre.toString()}:"
        tv_nota?.text = datos[position]?.precioPorKilo.toString()
        if (datos[position]?.esImportado == true){
            tv_sipasa?.text = "Pasa"
        }else{
            tv_sipasa?.text = "No Pasa"
        }

        return vista!!
    }
}