package br.aula.kotlin_pizzaria.db

import android.content.Context
import br.aula.kotlin_pizzaria.db.ConstantsDB.PIZZARIA_DB_TABLE
import org.jetbrains.anko.db.*
import timber.log.Timber

class PizzariaRepository(val context: Context)
{

    fun findAll() : ArrayList<Pizzaria> = context.database.use {
        val pizzarias = ArrayList<Pizzaria>()

        select(PIZZARIA_DB_TABLE, "id", "nomeEstabelecimento", "endereco", "telefone")
            .parseList(object: MapRowParser<List<Pizzaria>> {
                override fun parseRow(columns: Map<String, Any?>): List<Pizzaria> {
                    val pizzaria = Pizzaria(
                        id = columns.getValue("id").toString()?.toLong(),
                        nomeEstabelecimento = columns.getValue("nomeEstabelecimento")?.toString(),
                        endereco = columns.getValue("endereco")?.toString(),
                        telefone = columns.getValue("telefone")?.toString()?.toLong())
                    pizzarias.add(pizzaria)
                    return pizzarias
                }
            })

        pizzarias
    }


    fun create(pizzaria: Pizzaria) = context.database.use {
        insert(PIZZARIA_DB_TABLE,
            "nome" to pizzaria.nomeEstabelecimento,
            "endereco" to pizzaria.endereco,
            "telefone" to pizzaria.telefone)
    }


    fun update(pizzaria: Pizzaria) = context.database.use {
        val updateResult = update(PIZZARIA_DB_TABLE,
            "nome" to pizzaria.nomeEstabelecimento,
            "endereco" to pizzaria.endereco,
            "telefone" to pizzaria.telefone)
            .whereArgs("id = {id}","id" to pizzaria.id).exec()

     Timber.d("Update result code is $updateResult")
    }


    fun delete(id: Int) = context.database.use {
        delete(PIZZARIA_DB_TABLE, whereClause
        = "id = {pizzariaId}", args = *arrayOf("pizzariaId" to id)
        )
    }

}
