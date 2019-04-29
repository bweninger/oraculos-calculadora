package calculadora

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CalculadoraTest {

    private val result: Map<String, Int> = hashMapOf(
        "Palmeiras" to 1,
        "Grêmio" to 2,
        "Cruzeiro" to 3,
        "Flamengo" to 4,
        "Santos" to 5,
        "São Paulo" to 6,
        "Athletico Paranaense" to 7,
        "Internacional" to 8,
        "Corinthians" to 9,
        "Fluminense" to 10,
        "Atlético Mineiro" to 11,
        "Bahia" to 12,
        "Vasco da Gama" to 13,
        "Fortaleza" to 14,
        "Chapecoense" to 15,
        "Ceará" to 16,
        "Botafogo" to 17,
        "Avaí" to 18,
        "Goiás" to 19,
        "CSA" to 20
    )

    @Test
    fun testPosicaoCerta(){
        val calc = CalculadoraPosicaoCerta(result, null)
        val palpite = hashMapOf(
            "Palmeiras" to 1,
            "Cruzeiro" to 2,
            "Grêmio" to 3,
            "São Paulo" to 4,
            "Flamengo" to 5,
            "Santos" to 6,
            "Athletico Paranaense" to 7,
            "Corinthians" to 8,
            "Internacional" to 9,
            "Atlético Mineiro" to 10,
            "Fluminense" to 11,
            "Bahia" to 12,
            "Vasco da Gama" to 13,
            "Ceará" to 14,
            "Fortaleza" to 15,
            "Botafogo" to 16,
            "Chapecoense" to 17,
            "Goiás" to 18,
            "CSA" to 19,
            "Avaí" to 20
        )

        //4 acertos na mosca - Palmeiras 1, CAP 7, Bahia 12, Vasco 13
        Assertions.assertEquals(calc.calcularTotal(palpite), 32)
    }

    @Test
    fun testAcertosG4ouZ4() {
        val palpite = hashMapOf(
            "Palmeiras" to 1,
            "Cruzeiro" to 2,
            "Grêmio" to 3,
            "São Paulo" to 4,
            "Flamengo" to 5,
            "Santos" to 6,
            "Athletico Paranaense" to 7,
            "Corinthians" to 8,
            "Internacional" to 9,
            "Atlético Mineiro" to 10,
            "Fluminense" to 11,
            "Bahia" to 12,
            "Vasco da Gama" to 13,
            "Ceará" to 14,
            "Fortaleza" to 15,
            "Botafogo" to 16,
            "Chapecoense" to 17,
            "Goiás" to 18,
            "CSA" to 19,
            "Avaí" to 20
        )

        val calc = CalculadoraG4Z4(result, null)

        //3 acertos no G4, 3 acertos no Z4
        Assertions.assertEquals(calc.calcularTotal(palpite), 30)
    }

    @Test
    fun testPosicaoVizinha() {
        val palpite = hashMapOf(
            "Palmeiras" to 1,
            "Cruzeiro" to 2,
            "Grêmio" to 3,
            "São Paulo" to 4,
            "Flamengo" to 5,
            "Santos" to 6,
            "Athletico Paranaense" to 7,
            "Corinthians" to 8,
            "Internacional" to 9,
            "Atlético Mineiro" to 10,
            "Fluminense" to 11,
            "Bahia" to 12,
            "Vasco da Gama" to 13,
            "Ceará" to 14,
            "Fortaleza" to 15,
            "Botafogo" to 16,
            "Chapecoense" to 17,
            "Goiás" to 18,
            "CSA" to 19,
            "Avaí" to 20
        )

        val calc = CalculadoraProximidade(result, null)
        Assertions.assertEquals(calc.calcularTotal(palpite), 36)
    }

    @Test
    fun testCalcularTotal() {
        val palpite = hashMapOf(
            "Palmeiras" to 1,
            "Cruzeiro" to 2,
            "Grêmio" to 3,
            "São Paulo" to 4,
            "Flamengo" to 5,
            "Santos" to 6,
            "Athletico Paranaense" to 7,
            "Corinthians" to 8,
            "Internacional" to 9,
            "Atlético Mineiro" to 10,
            "Fluminense" to 11,
            "Bahia" to 12,
            "Vasco da Gama" to 13,
            "Ceará" to 14,
            "Fortaleza" to 15,
            "Botafogo" to 16,
            "Chapecoense" to 17,
            "Goiás" to 18,
            "CSA" to 19,
            "Avaí" to 20
        )

        val calc : Calculadora = CalculadoraPosicaoCerta(result, CalculadoraG4Z4(result, CalculadoraProximidade(result, null)))

        Assertions.assertEquals(calc.calcularTotal(palpite), 98)
    }

}