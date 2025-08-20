package com.example.appsoma2

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val edtNum1 = findViewById<EditText>(R.id.edt_num1)
        val edtNum2 = findViewById<EditText>(R.id.edt_num2)
        val bntCalcular = findViewById<Button>(R.id.bnt_calcular)
        val txvResultado = findViewById<TextView>(R.id.txv_resultado)
        val spinnerOperacao = findViewById<Spinner>(R.id.spinner_operacao)
        val rgModoCalculo = findViewById<RadioGroup>(R.id.rg_modo_calculo)
        val rbCalculadora = findViewById<RadioButton>(R.id.rb_calculadora)

        val operacoes = arrayOf("Soma", "Subtração", "Multiplicação", "Divisão")
        //Liga o Array do backend com o Frontend e cria cria como ira ser mostrado
        val liga = ArrayAdapter(this, android.R.layout.simple_spinner_item, operacoes)
        liga.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerOperacao.adapter = liga


        //alteraçao da interface com base na escolha
        rgModoCalculo.setOnCheckedChangeListener { _, checkedId ->  // mostra qual id foi selecionado no RadioGroup
            if (checkedId == R.id.rb_calculadora) {
                edtNum1.hint = "Primeiro Valor"
                edtNum2.hint = "Segundo Valor"
                spinnerOperacao.visibility = View.VISIBLE //comando para ver o spinner
            } else {
                edtNum1.hint = "R"
                edtNum2.hint = "I"
                spinnerOperacao.visibility = View.GONE //comando para esconder ele
            }
        }

        bntCalcular.setOnClickListener {
            val num1 = edtNum1.text.toString().toDouble()
            val num2 = edtNum2.text.toString().toDouble()
            var resultadoFinal: Double = 0.0

            if (rbCalculadora.isChecked) { //olha qual opçao escolheu e seleciona a caculadora ou a lei
                val operacao = spinnerOperacao.selectedItem.toString()
                when (operacao) {
                    "Soma" -> {
                        resultadoFinal = num1 + num2
                    }
                    "Subtração" -> {
                        resultadoFinal = num1 - num2
                    }
                    "Multiplicação" -> {
                        resultadoFinal = num1 * num2
                    }
                    "Divisão" -> {
                        if (num2 != 0.0) {
                            resultadoFinal = num1 / num2
                        } else {
                            txvResultado.text = "Erro: Divisão por zero"
                        }
                    }
                }
                txvResultado.text = "O Resultado é: $resultadoFinal"
            } else {
                val resistencia = num1
                val corrente = num2
                resultadoFinal = resistencia * corrente
                txvResultado.text = "V = $resultadoFinal"
            }
        }
    }
}