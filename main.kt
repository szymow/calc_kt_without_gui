import java.util.Stack

var wynik : Double = 0.0
var pierwsza_liczba : Boolean = true

var stackOfValues = Stack<Double>()
var stackOfSigns = Stack<Char>()

var wyswietlacz = mutableListOf<String>()

//Zamiana kolejności itemów związana jest z kolejnością zdejmowania wartości ze stosu
fun dodawanie(item1: Double, item2: Double): Double = item2 + item1
fun odejmowanie(item1: Double, item2: Double): Double = item2 - item1
fun mnozenie(item1: Double, item2: Double): Double = item1 * item2
fun dzielenie(item1: Double, item2: Double) : Double {
    if(item1 != 0.0) return item2 / item1
    else return Double.NaN
}

fun liczenie(){
    if(stackOfSigns.isNotEmpty() && stackOfValues.isNotEmpty()) {
        when (stackOfSigns.pop()) {
            '+' -> wynik = dodawanie(stackOfValues.pop(), stackOfValues.pop())
            '-' -> wynik = odejmowanie(stackOfValues.pop(), stackOfValues.pop())
            '*' -> wynik = mnozenie(stackOfValues.pop(), stackOfValues.pop())
            '/' -> wynik = dzielenie(stackOfValues.pop(), stackOfValues.pop())
        }
        if (wynik.isNaN()){
            print("Nie można dzielić przez zero")
            System.exit(0)
        }
        else stackOfValues.push(wynik)
    }
}

fun wpisz_liczbe() {
    print("Wpisz liczbę: ")
    val enteredValue: Double = readLine()!!.toDouble()
    stackOfValues.push(enteredValue)
    wyswietlacz.add(enteredValue.toString())
}

fun wpisz_znak() : Boolean {
    print("Wpisz znak: ")
    val enteredSign = readLine()!![0] //Czytaj tylko jeden, pierwszy wprowadzony znak
    if(stackOfSigns.isEmpty()){
        if (enteredSign in arrayOf ('+','-','/','*')) {
            stackOfSigns.push(enteredSign)
            wyswietlacz.add(enteredSign.toString())
            return true
        }
    }
    if (stackOfSigns.isNotEmpty()){
        if (enteredSign in arrayOf('+','-') && stackOfSigns.peek() in arrayOf('+','-')) {
            dzialanie()
            stackOfSigns.push(enteredSign)
            wyswietlacz.add(enteredSign.toString())
            return true
        }
        if(enteredSign in arrayOf('*','/') && stackOfSigns.peek() in arrayOf('+','-')){
            stackOfSigns.push(enteredSign)
            wyswietlacz.add(enteredSign.toString())
            return true
        }
        if(enteredSign in arrayOf('+','-') && stackOfSigns.peek() in arrayOf('*','/')){
            dzialanie()
            stackOfSigns.push(enteredSign)
            wyswietlacz.add(enteredSign.toString())
            return true
        }
        if (enteredSign in arrayOf('*','/') && stackOfSigns.peek() in arrayOf('*','/')) {
            dzialanie()
            stackOfSigns.push(enteredSign)
            wyswietlacz.add(enteredSign.toString())
            return true
        }
        if(enteredSign == '='){
            do dzialanie()
                while(stackOfSigns.isNotEmpty())
            return false
        }
    }
    return false
}

fun dzialanie(){
    println(wyswietlacz)
    println("Przed dokonaniem działania")
    println("\t stackOfValues: $stackOfValues")
    println("\t stackOfSigns: $stackOfSigns")

    liczenie()

    println("Po dokonaniu działania")
    println("\t stackOfValues: $stackOfValues")
    println("\t stackOfSigns: $stackOfSigns")
}


fun main() {

    println("Szymon Woyda 227458")

    do {
        if(pierwsza_liczba) {
            pierwsza_liczba = false
            wpisz_liczbe()
        }

        val kontynuuj : Boolean = wpisz_znak()

        if(kontynuuj) wpisz_liczbe()

    } while (stackOfSigns.isNotEmpty())

    println("Wynik: $wynik")
}

//https://chercher.tech/kotlin/stack-kotlin