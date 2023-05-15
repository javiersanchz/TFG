package com.example.tfg

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import com.example.tfg.databinding.ActivityPantallaPrincipalBinding
import com.example.tfg.databinding.ActivityRestaurantesBinding

class Restaurantes : AppCompatActivity() {

    private lateinit var textoAnimacion: String
    private lateinit var textView: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurantes)

        //binding
        val binding = ActivityRestaurantesBinding.inflate(layoutInflater)
        setContentView(binding.root)


        textView = findViewById(R.id.textoComienzo)
        textoAnimacion = "¿Qué buscamos hoy?"

        animarTexto()

        //si das en el boton de desayunos te lleva a la actividad de desayunos




        findViewById<View>(R.id.btnDesayunos).setOnClickListener {
            val intent = Intent(this, Desayunos::class.java)
            startActivity(intent)
            //searchPlaces("desayunos veganos")

        }

        findViewById<View>(R.id.btnComidas).setOnClickListener {
            searchPlaces("comidas veganas")
        }

        findViewById<View>(R.id.btnSupermercados).setOnClickListener {
            searchPlaces("supermercados veganos")
        }

        findViewById<View>(R.id.btnTiendasEcologicas).setOnClickListener {
            searchPlaces("tiendas ecologicas")
        }
    }

    private fun searchPlaces(query: String) {
        val gmmIntentUri = Uri.parse("geo:0,0?q=$query")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        if (mapIntent.resolveActivity(packageManager) != null) {
            startActivity(mapIntent)
        }
    }

    private fun animarTexto() {
        val handler = Handler(Looper.getMainLooper())

        val builder = StringBuilder()

        for (i in textoAnimacion.indices) {
            handler.postDelayed({
                builder.append(textoAnimacion[i])
                textView.text = builder.toString()
            }, i * 150L)
        }
    }
}
