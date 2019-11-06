import java.util.Stack

var wynik : Double = 0.0
var poczatek : Boolean = true

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
    println("Przed dokonaniem działania")
    println("\t stackOfValues: $stackOfValues")
    println("\t stackOfSigns: $stackOfSigns")

    if(!stackOfSigns.isEmpty() && !stackOfValues.isEmpty()) {
        when (stackOfSigns.pop()) {
            '+' -> wynik = dodawanie(stackOfValues.pop(), stackOfValues.pop())
            '-' -> wynik = odejmowanie(stackOfValues.pop(), stackOfValues.pop())
            '*' -> wynik = mnozenie(stackOfValues.pop(), stackOfValues.pop())
            '/' -> wynik = dzielenie(stackOfValues.pop(), stackOfValues.pop())
        }
        stackOfValues.push(wynik)
    }

    println("Po dokonaniem działania")
    println("\t stackOfValues: $stackOfValues")
    println("\t stackOfSigns: $stackOfSigns")
}


fun main() {
    println("Szymon Woyda 227458")

    do {
        if(poczatek) {
            poczatek = false
            print("Wpisz liczbę: ")
            val enteredValue: Double = readLine()!!.toDouble()
            stackOfValues.push(enteredValue)
        }

        dzialanie()
        print("Wpisz znak: ")
        val enteredSign = readLine()!![0] //Czytaj tylko jeden, pierwszy wprowadzony znak
        if (enteredSign in arrayOf ('+','-','/','*')) stackOfSigns.push(enteredSign)

        if(enteredSign != '=') {
            print("Wpisz liczbę: ")
            val enteredValue2: Double = readLine()!!.toDouble()
            stackOfValues.push(enteredValue2)
        }

    } while (enteredSign != '=')

    println("Wynik: $wynik")
}

//https://chercher.tech/kotlin/stack-kotlin