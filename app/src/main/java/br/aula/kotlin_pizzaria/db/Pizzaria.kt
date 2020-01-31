package br.aula.kotlin_pizzaria.db

import java.io.Serializable

data class Pizzaria
(
    var id: Long = 0,
    var nomeEstabelecimento: String? = null,
    var endereco: String? = null,
    var telefone: Long? = null) : Serializable {

    override fun toString(): String {
        return nomeEstabelecimento.toString()
    }


}