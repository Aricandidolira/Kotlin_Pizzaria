package br.aula.kotlin_pizzaria

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.Toast
import br.aula.kotlin_pizzaria.db.Pizzaria
import br.aula.kotlin_pizzaria.db.PizzariaRepository
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_new_pizza.*

class InfoPizza : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_pizza)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)



        val pizzarias = PizzariaRepository(this).findAll()
        val adapter
                = ArrayAdapter(this, android.R.layout.simple_list_item_1, pizzarias)

        var listaPizzaria = lista // lista corresponde ao id que está no layout no componente ListView
        lista.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        // a classe R puxa o layout
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    // Comportamento do Menu: recebe o id do menu e realiza uma ação
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.novo -> {
                    val intent = Intent(this, NewPizza::class.java)
                startActivity(intent)
                return false
            }

            R.id.mapa -> {
                Toast.makeText(this, "Mapa", Toast.LENGTH_LONG).show()
                return false
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }


}

