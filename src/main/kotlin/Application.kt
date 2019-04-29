import calculadora.Calculadora
import calculadora.CalculadoraG4Z4
import calculadora.CalculadoraPosicaoCerta
import calculadora.CalculadoraProximidade
import reader.PalpitesReader

fun main(args: Array<String>) {

    val resource = PalpitesReader::class.java.getResource("/Oráculos 2018 (respostas).xlsx")
    val palpites = PalpitesReader().obterPalpitesArquivo(resource.openStream())
    val resultado = mapOf(
        "Palmeiras" to 1,
        "Flamengo" to 2,
        "Internacional" to 3,
        "Grêmio" to 4,
        "São Paulo" to 5,
        "Atlético Mineiro" to 6,
        "Athletico Paranaense" to 7,
        "Cruzeiro" to 8,
        "Botafogo" to 9,
        "Santos" to 10,
        "Bahia" to 11,
        "Fluminense" to 12,
        "Corinthians" to 13,
        "Chapecoense" to 14,
        "Ceará" to 15,
        "Vasco da Gama" to 16,
        "Sport" to 17,
        "América" to 18,
        "Vitória" to 19,
        "Paraná Clube" to 20
    )

    val calculadora : Calculadora = CalculadoraPosicaoCerta(resultado, CalculadoraG4Z4(resultado, CalculadoraProximidade(resultado, null)))
    palpites.map{it.email to calculadora.calcularTotal(it)}.forEach(::println)

}