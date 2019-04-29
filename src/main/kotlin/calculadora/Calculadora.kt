package calculadora

import com.natpryce.konfig.ConfigurationProperties
import com.natpryce.konfig.Key
import com.natpryce.konfig.intType
import model.Palpite
import kotlin.math.absoluteValue

abstract class Calculadora(protected val resultado: Map<String, Int>, val propriedadePeso: String, private val proximo: Calculadora? = null) {

    private val config = ConfigurationProperties.fromResource("pesos.properties")

    fun calcularTotal(palpite: Palpite): Int {

        val key = Key(propriedadePeso, intType)
        val peso = config[key]
        return this.calcular(palpite) * peso + (proximo?.calcularTotal(palpite) ?: 0)
    }

    protected abstract fun calcular(palpite: Palpite): Int
}

class CalculadoraPosicaoCerta(resultado: Map<String, Int>, proximo: Calculadora?) : Calculadora(resultado, "calculadora.pesos.na-mosca", proximo) {

    override fun calcular(palpite: Palpite): Int {
        val zipped = resultado.entries.zip(palpite.classificacao.entries)
        val acertos = zipped.filter { it.first == it.second }
        acertos.forEach{
            println("${palpite.email}: Acerto de Posição Certa: ${it.first}")
        }
        return acertos.count()
    }
}

class CalculadoraG4Z4(resultado: Map<String, Int>, proximo: Calculadora?) : Calculadora(resultado, "calculadora.pesos.g4-z4", proximo) {
    override fun calcular(palpite: Palpite): Int {
        val resultList = resultado.toList().sortedBy { it.second }

        val g4 = resultList.take(4).map { it.first }
        val z4 = resultList.takeLast(4).map { it.first }

        val guessList = palpite.classificacao.toList().sortedBy { it.second }


        val g4GuessedCorrectly = guessList.take(4).filter { g4.contains(it.first) }
        val z4GuessedCorrectly = guessList.takeLast(4).filter { z4.contains(it.first) }
        g4GuessedCorrectly.forEach{ println("${palpite.email}: Acerto de G4 : ${it.first} - ${it.second}")}
        z4GuessedCorrectly.forEach{ println("${palpite.email}: Acerto de Z4 : ${it.first} - ${it.second}")}
        return g4GuessedCorrectly.count() + z4GuessedCorrectly.count()

    }

}

class CalculadoraProximidade(resultado: Map<String, Int>, proximo: Calculadora?) : Calculadora(resultado, "calculadora.pesos.posicao-vizinha", proximo) {
    override fun calcular(palpite: Palpite): Int {
        val acertos = resultado.map { (it.key to it.value.minus(palpite.classificacao[it.key] ?: 99).absoluteValue) }
            .filter { it.second == 1 }

        acertos.forEach{println("${palpite.email}: Acerto de posição vizinha ${it.first}")}
        return acertos.count()
    }
}