package com.example.guess

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import android.widget.ToggleButton
import androidx.activity.ComponentActivity
import java.text.SimpleDateFormat
import java.util.*

import android.view.View
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainlayout)

        //Formato fecha
        val formatoSalida = SimpleDateFormat("dd 'de' MMMM hh:mm a", Locale("es", "ES"))
        val calendar = Calendar.getInstance() // Obtener la instancia actual de Calendar
        val fechaFormateada = formatoSalida.format(calendar.time) // Formatear la fecha actual
        val textViewFecha = findViewById<TextView>(R.id.fecha_y_hora) // Asegúrate de que coincida con el ID de tu TextView en tu layout XML
        textViewFecha.text = fechaFormateada


        //otros elementos
        val cajaTexto: EditText = findViewById(R.id.cajaTexto)
        val caracteres: TextView = findViewById(R.id.caracteres)
        val guess: TextView = findViewById(R.id.guess)
        guess.alpha = 0.18F
        val botonVisibilidad: ToggleButton = findViewById(R.id.cuadrado)
        botonVisibilidad.isChecked = true
        botonVisibilidad.setOnClickListener{
            if(botonVisibilidad.isChecked){
                botonVisibilidad.alpha = 0F
                guess.alpha = 0F
            }else{
                botonVisibilidad.alpha = 1F
                guess.alpha = 0.18F
            }
        }



        var sumaResultado: Int
        cajaTexto.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val texto = s.toString()

                // Dividir el texto en líneas
                val lineas = texto.split("\n")

                // Contar la cantidad total de caracteres
                val cantidadCaracteres = s?.toString()?.replace("\n", "")?.length ?: 0

                // Sumar los números de cada línea
                sumaResultado = 0 // Reiniciar la suma
                for (linea in lineas) {
                    if (linea.isNotBlank()) {
                        sumaResultado += try {
                            linea.toInt()
                        } catch (e: NumberFormatException) {
                            0 // Si no es un número válido, sumar 0
                        }
                    }
                }
                val resultado = sumaResultado // Tomar el valor de la suma

                val proximoMultiploDeNueve = (resultado + 8) / 9 * 9

                val diferencia = Math.abs(resultado - proximoMultiploDeNueve)

                if(sumaResultado==0){
                }else{
                    guess.text = "$diferencia"
                }

                caracteres.text = "$cantidadCaracteres caracteres"
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

    }

}
