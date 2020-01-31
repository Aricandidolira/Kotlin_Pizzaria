package br.aula.kotlin_pizzaria.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import br.aula.kotlin_pizzaria.db.ConstantsDB.PIZZARIA_DB_NAME
import br.aula.kotlin_pizzaria.db.ConstantsDB.PIZZARIA_DB_TABLE

import org.jetbrains.anko.db.ManagedSQLiteOpenHelper
import org.jetbrains.anko.db.*

class BancoDadosHelper(context: Context) :
    ManagedSQLiteOpenHelper(ctx = context ,
        name = "$PIZZARIA_DB_NAME",  version = 1) {


    private val scriptSQLCreate = arrayOf(
        "INSERT INTO $PIZZARIA_DB_TABLE VALUES(1,'Ponto da Pizza','Av. João Ferraz Neto, 680 - Jardim Ferreira Dias, Jaú - SP, 17209-655',1436221137);",
        "INSERT INTO $PIZZARIA_DB_TABLE VALUES(2,'La Bambina','R. XV de Novembro, 1293, Dois Córregos - SP, 17300-000, 04121-120',1436521620);",
        "INSERT INTO $PIZZARIA_DB_TABLE VALUES(3,'Mister Pizza','R. Dr. Antônio Teixeira Sobrinho, 432, Mineiros do Tietê - SP, 17320-000',1436463030);",
        "INSERT INTO $PIZZARIA_DB_TABLE VALUES(4,'Donatellos Pizzaria','R. Maj. Ascânio, 21 - Vila Brasil, Jaú - SP, 17202-305',1436255353);")

    companion object {
        private var instance: BancoDadosHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): BancoDadosHelper {
            if (instance == null) {
                instance = BancoDadosHelper(ctx.applicationContext)
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable("$PIZZARIA_DB_TABLE", true,
            "id" to INTEGER + PRIMARY_KEY + UNIQUE,
            "nomeEstabelecimento" to TEXT,
            "endereco" to INTEGER,
            "telefone" to INTEGER
        )

        // insere dados iniciais na tabela
        scriptSQLCreate.forEach {sql ->
            db.execSQL(sql)
        }

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable("$PIZZARIA_DB_NAME", true)
        onCreate(db)

    }

}

val Context.database: BancoDadosHelper get()
= BancoDadosHelper.getInstance(applicationContext)
