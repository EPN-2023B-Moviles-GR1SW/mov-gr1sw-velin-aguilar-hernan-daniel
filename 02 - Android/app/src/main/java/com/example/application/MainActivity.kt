package com.example.application

import android.app.Activity
import android.app.Instrumentation.ActivityResult
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Snackbar
import com.google.android.material.snackbar.Snackbar


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val callbackContenidoIntentExplicito = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
            result -> if(result.resultCode == Activity.RESULT_OK){
                if(result.data != null){
                    val data = result.data
                    mostrarSnackbar("${data?.getStringExtra("nombreModificado")}");
                }
            }
        }

        val callbackIntentPickUri = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
                result -> if(result.resultCode == Activity.RESULT_OK){
                    if(result.data != null){
                        val uri : Uri = result.data!!.data!!
                        val cursor = contentResolver.query(uri, null, null,
                            null, null ,null)
                        cursor?.moveToFirst()
                        val indiceTelefono =
                            cursor?.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                        val telefono = cursor?.getString(indiceTelefono!!)
                        cursor?.close()
                        mostrarSnackbar("Telefono${telefono}")
                    }
                }
        }

        EBaseDatos.tablaEntrenador = ESqliteHelperEntrenador(this)
        setContentView(R.layout.activity_main);

        val botonIntentImplicito = findViewById<Button>(R.id.btn_ir_intent_implicito)
        botonIntentImplicito.setOnClickListener{
            val intentConRespuesta = Intent(Intent.ACTION_PICK,
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI)
            callbackIntentPickUri.launch(intentConRespuesta)
        }

        val botonIntentExplicito = findViewById<Button>(R.id.btn_ir_intent_explicito)
        botonIntentExplicito.setOnClickListener{
            abrirActividadConParametros(CIntentExplicitoParametros::class.java)
        }

        val botonCicloVida = findViewById<Button>(R.id.btn_ciclo_vida)
        botonCicloVida.setOnClickListener{
            irActividad(ACicloVida::class.java)
        }

        val botonListView = findViewById<Button>(R.id.btn_ir_list_view)
        botonListView.setOnClickListener{
            irActividad(BListView::class.java)
        }

        val botonGoogleMaps = findViewById<Button>(R.id.btn_google_maps)
        botonGoogleMaps.setOnClickListener{
            irActividad(GGoogleMapsActivity::class.java)
        }

    }

    fun irActividad(clase : Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    fun mostrarSnackbar(texto:String){
        Snackbar.make(findViewById(R.id.id_layout_main), texto, Snackbar.LENGTH_LONG).show()
    }

    fun abrirActividadConParametros(clase: Class<*>){
        val intentExplicito = Intent(this,clase)
        intentExplicito.putExtra("nombre", "Daniel")
        intentExplicito.putExtra("apellido", "Velin")
        intentExplicito.putExtra("edad", "22")
        //callbackContenidoIntentExplicito.launch(intentExplicito)
    }
}
