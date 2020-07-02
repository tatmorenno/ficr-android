package com.example.ageapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /** Evento de click do btnEnviar */
        btnEnviar.setOnClickListener {
            /** Verificando se uma data foi insira */
            if (inputAno.text.toString().isEmpty()) {
                /** Exibindo Toast de alerta */
                Toast.makeText(this, "Insira uma Data", Toast.LENGTH_SHORT).show()
            } else {
                validarIdade(inputAno.text.toString())
            }
        }
    }

    /**
    fun onSubmit(view:View) {
    val currentYear = Calendar.getInstance().get(Calendar.YEAR)
    tvIdade.text = "Idade : " + (currentYear - inputAno.text.toString().toInt())
    }*/

    /** Metodo que valida a data inserida pelo usuário, se o ano dela for igual ou maior que o ano atual o metodo de calcularIdade()
     * não é chamado */
    private fun validarIdade(dataEditText: String) {
        // Nesse subString() estou obtendo os 4 últimos digitos da data e convertendo para Int para posteriormente se feita a validadação
        if (dataEditText.substring(dataEditText.length - 4).toInt() >= Calendar.getInstance().get(Calendar.YEAR)) {
            Toast.makeText(this, "Data de Nascimento Inválida!", Toast.LENGTH_SHORT).show()
        } else {
            // Convertendo a data para tipo Date usando a classe SimpleDateFormat com pattern do padrão de data BR
            // parse() retorna um objeto tipo Date que vai ser passado para o calcularIdade()
            calcularIdade(SimpleDateFormat("dd/MM/yyyy").parse(dataEditText))
        }
    }

    private fun calcularIdade(dataNascimento : Date) {
        // Criando objeto com a data atual
        val dataAtual = Calendar.getInstance()
        // Criando objeto GregorianCalendar
        val dataNascimentoGregorian = GregorianCalendar()
        // Definindo a data do GregorianCalendar para a data de nascimento
        dataNascimentoGregorian.time = dataNascimento
        // Obtendo a idade com base no ano
        var idade = dataAtual.get(Calendar.YEAR) - dataNascimentoGregorian.get(Calendar.YEAR)
        dataNascimentoGregorian.add(Calendar.YEAR, idade)
        // Se a data de hoje é antes da data de nascimento, então diminui 1
        if (dataAtual.before(dataNascimentoGregorian)) {
            // Diminuindo 1 de idade
            idade--
        }
        /** Se a idade for igual a 0 quer dizer que o usuário tem menos de um ano */
        if (idade == 0) {
            tvIdade.text = "Menos de um 1 ano de idade"
        } else {
            tvIdade.text = "Sua idade é ${idade} anos"
        }
    }
}