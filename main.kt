import java.util.Stack

val set_of_signs = charArrayOf('+','-','*','/')
var wynik : Any = 0

//Zamiana kolejności itemów związana jest z kolejnością zdejmowania wartości ze stosu
fun dodawanie(item1: Double, item2: Double): Double = item2 + item1
fun odejmowanie(item1: Double, item2: Double): Double = item2 - item1
fun mnozenie(item1: Double, item2: Double): Double = item2 * item1
fun dzielenie(item1: Double, item2: Double) : Any {
    if(item1 != 0.0) return item2 / item1
    else return "DZIEL0"
}


fun main(args: Array<String>) {

    println("Hello Kotlin!")

    var stackOfValues = Stack<Double>()
    var stackOfSigns = Stack<Char>()

    print("Enter value: ")
    val enteredValue : Double = readLine()!!.toDouble()
    stackOfValues.push(enteredValue)

    print("Enter sign: ")
    val enteredSign = readLine()!![0] //Czytaj tylko jeden, pierwszy wprowadzony znak
    stackOfSigns.push(enteredSign)

    print("Enter value: ")
    val enteredValue2 : Double = readLine()!!.toDouble()
    stackOfValues.push(enteredValue2)

    println("stackOfValues: " + stackOfValues)
    println("stackOfSigns: " + stackOfSigns)

    if(stackOfSigns.isEmpty() == false || stackOfValues.isEmpty() == false)
    when (stackOfSigns.pop()) {
        '+' -> wynik = dodawanie(stackOfValues.pop(),stackOfValues.pop())
        '-' -> wynik = odejmowanie(stackOfValues.pop(),stackOfValues.pop())
        '*' -> wynik = mnozenie(stackOfValues.pop(),stackOfValues.pop())
        '/' -> wynik = dzielenie(stackOfValues.pop(),stackOfValues.pop())
        }

    println("Wynik: " + wynik)

    println("stackOfValues: " + stackOfValues)
    println("stackOfSigns: " + stackOfSigns)
}

//https://chercher.tech/kotlin/stack-kotlin