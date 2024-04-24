package com.example.examencalculadora

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.examencalculadora.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var operacion: String? = null
    private var operando1: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Setear los onClickListener para los botones
        binding.apply {
            btn0.setOnClickListener { numeroPulsado("0") }
            btn1.setOnClickListener { numeroPulsado("1") }
            btn2.setOnClickListener { numeroPulsado("2") }
            btn3.setOnClickListener { numeroPulsado("3") }
            btn4.setOnClickListener { numeroPulsado("4") }
            btn5.setOnClickListener { numeroPulsado("5") }
            btn6.setOnClickListener { numeroPulsado("6") }
            btn7.setOnClickListener { numeroPulsado("7") }
            btn8.setOnClickListener { numeroPulsado("8") }
            btn9.setOnClickListener { numeroPulsado("9") }
            btnPunto.setOnClickListener { numeroPulsado(".") }

            btnSuma.setOnClickListener { operacionPulsada('+') }
            btnResta.setOnClickListener { operacionPulsada('-') }
            btnMult.setOnClickListener { operacionPulsada('*') }
            btndiv.setOnClickListener { operacionPulsada('/') }

            btnBorrar.setOnClickListener { borrar() }
            btnLimpiar.setOnClickListener { clear() }
            btnSalir.setOnClickListener { finish() }

            btnCalcular.setOnClickListener { calcularResultado() }
        }
    }

    private fun numeroPulsado(digito: String) {
        binding.txtcaja.append(digito)
    }

    private fun operacionPulsada(operador: Char) {
        operacion = operador.toString()
        operando1 = binding.txtcaja.text.toString().toDoubleOrNull()
        binding.txtcaja.text.clear()
    }

    private fun calcularResultado() {
        val operando2 = binding.txtcaja.text.toString().toDoubleOrNull()
        if (operando1 != null && operando2 != null && operacion != null) {
            val resultado = when (operacion) {
                "+" -> operando1!! + operando2
                "-" -> operando1!! - operando2
                "*" -> operando1!! * operando2
                "/" -> if (operando2 != 0.0) operando1!! / operando2 else Double.NaN
                else -> Double.NaN
            }
            binding.txtcaja.text.clear()
            binding.txtcaja.append(resultado.toString())
            operacion = null
            operando1 = null
        }
    }

    private fun borrar() {
        val texto = binding.txtcaja.text.toString()
        if (texto.isNotEmpty()) {
            binding.txtcaja.setText(texto.substring(0, texto.length - 1))
        }
    }

    private fun clear() {
        binding.txtcaja.text.clear()
        operacion = null
        operando1 = null
    }
}
