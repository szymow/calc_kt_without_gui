import java.util.Stack

var wynik : Double = 0.0

var stackOfValues = Stack<Double>()
var stackOfSigns = Stack<Char>()

//Zamiana kolejności itemów związana jest z kolejnością zdejmowania wartości ze stosu
fun dodawanie(item1: Double, item2: Double): Double = item2 + item1
fun odejmowanie(item1: Double, item2: Double): Double = item2 - item1
fun mnozenie(item1: Double, item2: Double): Double = item2 * item1
fun dzielenie(item1: Double, item2: Double) : Double {
    if(item1 != 0.0) return item2 / item1
    else return Double.NaN
}

fun dzialanie(){
    if(!stackOfSigns.isEmpty() || !stackOfValues.isEmpty())
        when (stackOfSigns.pop()) {
            '+' -> wynik = dodawanie(stackOfValues.pop(),stackOfValues.pop())
            '-' -> wynik = odejmowanie(stackOfValues.pop(),stackOfValues.pop())
            '*' -> wynik = mnozenie(stackOfValues.pop(),stackOfValues.pop())
            '/' -> wynik = dzielenie(stackOfValues.pop(),stackOfValues.pop())
        }
    stackOfValues.push(wynik)
}


fun main() {

    println("Hello Kotlin!")

    do {
        print("Wpisz liczbę: ")
        val enteredValue: Double = readLine()!!.toDouble()
        stackOfValues.push(enteredValue)

        print("Wpisz znak: ")
        val enteredSign = readLine()!![0] //Czytaj tylko jeden, pierwszy wprowadzony znak
        if (enteredSign in arrayOf ('+','-','/','*')) stackOfSigns.push(enteredSign)

        if(enteredSign != '=') {
            print("Wpisz liczbę: ")
            val enteredValue2: Double = readLine()!!.toDouble()
            stackOfValues.push(enteredValue2)
        }

        dzialanie()

        println("Wpisz {=} jeśli chcesz poznać wynik.")
        println("Lub wpisz kolejny znak jeżeli chcesz kontynuować działanie.")
        val enteredSign2 = readLine()!![0]
        if (enteredSign2 in arrayOf ('+','-','/','*')) stackOfSigns.push(enteredSign2)

    } while (enteredSign != '=' && enteredSign2 != '=')

    println("stackOfValues: $stackOfValues")
    println("stackOfSigns: $stackOfSigns")
    

    println("Wynik: $wynik")

    println("stackOfValues: $stackOfValues")
    println("stackOfSigns: $stackOfSigns")
}

//https://chercher.tech/kotlin/stack-kotlin