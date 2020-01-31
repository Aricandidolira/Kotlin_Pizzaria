package br.aula.kotlin_pizzaria

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import br.aula.kotlin_pizzaria.db.Pizzaria
import br.aula.kotlin_pizzaria.db.PizzariaRepository
import kotlinx.android.synthetic.main.activity_new_pizza.*
import java.text.SimpleDateFormat
import java.util.*

class NewPizza : AppCompatActivity()
{


    var cal = Calendar.getInstance()
    var pizzaria: Pizzaria? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_pizza)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (intent?.getSerializableExtra("pizzaria") != null) {
            pizzaria = intent?.getSerializableExtra("pizzaria") as Pizzaria
            txtNome?.setText(pizzaria?.nomeEstabelecimento)
            txtEndereco?.setText(pizzaria?.endereco)
            txtTelefone?.setText(pizzaria?.telefone?.toString())
        }else{
            pizzaria = Pizzaria()
        }

        val ab = supportActionBar

        // Enable the Up button
        ab?.setDisplayHomeAsUpEnabled(true)


        btnEnviar.setOnClickListener {
            pizzaria?.nomeEstabelecimento = txtNome?.text.toString()
            pizzaria?.endereco = txtEndereco?.text.toString()
            pizzaria?.telefone = txtTelefone?.text.toString().toLong()

            if(pizzaria?.id?.toInt() == 0){
                PizzariaRepository(this).create(pizzaria!!)
            }else{
                PizzariaRepository(this).update(pizzaria!!)
            }

            finish()
        }
    }


}
