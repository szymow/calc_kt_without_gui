import java.util.Stack

var wynik : Double = 0.0
var pierwsza_liczba : Boolean = true

var stackOfValues = Stack<Double>()
var stackOfSigns = Stack<Char>()

var wyswietlacz = ArrayList<String>()

//Zamiana kolejności itemów związana jest z kolejnością zdejmowania wartości ze stosu
fun dodawanie(item1: Double, item2: Double): Double = item2 + item1
fun odejmowanie(item1: Double, item2: Double): Double = item2 - item1
fun mnozenie(item1: Double, item2: Double): Double = item1 * item2
fun dzielenie(item1: Double, item2: Double) : Double = item2 / item1

fun silnia(item1: Double) : Double{
    val factorial : Double = silnia_recursja(item1)
    return factorial
}

fun silnia_recursja(num: Double): Double {
    if (num >= 1.0)
        return num * silnia_recursja(num - 1.0)
    else
        return 1.0
}

fun sprawdz_dziel0(value: Double){
    if (stackOfSigns.isNotEmpty()){
        if (stackOfSigns.peek() == ('/') && value == 0.0){
            print("Nie można dzielić przez zero")
            System.exit(0)
        }
    }
}

fun update(sign: Char){
    stackOfSigns.push(sign)
    wyswietlacz.add(sign.toString())
}

fun liczenie(){
    if(stackOfSigns.isNotEmpty() && stackOfValues.isNotEmpty()) {

        when (stackOfSigns.pop()) {
            '+' -> wynik = dodawanie(stackOfValues.pop(), stackOfValues.pop())
            '-' -> wynik = odejmowanie(stackOfValues.pop(), stackOfValues.pop())
            '*' -> wynik = mnozenie(stackOfValues.pop(), stackOfValues.pop())
            '/' -> wynik = dzielenie(stackOfValues.pop(), stackOfValues.pop())
        }
        stackOfValues.push(wynik)
    }
}

fun wpisz() : Boolean{
    println("Wpisz coś: ")
    val entered = readLine()
    try{
        val enteredValue = entered!!.toDouble()
        wpisano_liczbe(enteredValue)
        return true
    }
    catch (nfe: NumberFormatException){
        val enteredSign = entered!![0]
        wpisano_znak(enteredSign)
        //Kontynuj wpisywanie znaków pod warunkiem, że nie zostało wpisane '='
        return enteredSign != '='
    }
}

fun wpisano_liczbe(value2: Double) {
    sprawdz_dziel0(value2)
    stackOfValues.push(value2)
    wyswietlacz.add(value2.toString())
}

fun wpisano_znak(sign2 : Char) : Boolean {
    //Jeżeli stos liczb jest pusty a został wpisany znak dodaj do stosu liczb 0 na poczatku
    if(stackOfValues.isEmpty()){
        stackOfValues.push(0.0)
        wyswietlacz.add((0.0).toString())
    }
    //'p' plus/minus +/-    //'s' silnia n!
    if(sign2 == 'p' || sign2 == 's'){
        var zmiana = stackOfValues.pop()
        wyswietlacz.removeAt(wyswietlacz.lastIndex)
        if(sign2 == 'p') zmiana = -zmiana
        if(sign2 == 's') zmiana = silnia(zmiana)
        stackOfValues.push(zmiana)
        wyswietlacz.add(zmiana.toString())
    }
    if(stackOfSigns.isEmpty()){
        if (sign2 in arrayOf ('+','-','/','*')) {
            update(sign2)
            return true
        }
    }
    if (stackOfSigns.isNotEmpty()){
        if (sign2 in arrayOf('+','-') && stackOfSigns.peek() in arrayOf('+','-')||
            sign2 in arrayOf('+','-') && stackOfSigns.peek() in arrayOf('*','/')||
            sign2 in arrayOf('*','/') && stackOfSigns.peek() in arrayOf('*','/')) {
            dzialanie()
            update(sign2)
            return true
        }
        if(sign2 in arrayOf('*','/') && stackOfSigns.peek() in arrayOf('+','-')){
            update(sign2)
            return true
        }
        if(sign2 == '='){
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
            wpisz()
        }

        val kontynuuj : Boolean = wpisz()

        if(kontynuuj) wpisz()

    } while (stackOfSigns.isNotEmpty())

    println("Wynik: $wynik")
}