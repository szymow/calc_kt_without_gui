import java.util.Stack

const val ZERO : Double = 0.0
var wynik : Double = ZERO
var pierwsza_liczba : Boolean = true

var stosWartosci = Stack<Double>()
var stosZnakow = Stack<Char>()

var wyswietlacz = ArrayList<String>()

//Zamiana kolejności itemów związana jest z kolejnością zdejmowania wartości ze stosu
fun dodawanie(zmienna1: Double, zmienna2: Double): Double = zmienna2 + zmienna1
fun odejmowanie(zmienna1: Double, zmienna2: Double): Double = zmienna2 - zmienna1
fun mnozenie(zmienna1: Double, zmienna2: Double): Double = zmienna1 * zmienna2
fun dzielenie(zmienna1: Double, zmienna2: Double) : Double = zmienna2 / zmienna1

fun silnia(item1: Double) : Double{
    val factorial : Double = silnia_recursja(item1)
    return factorial }

fun silnia_recursja(num: Double): Double {
    if (num >= 1.0)     return num * silnia_recursja(num - 1.0)
    else        return 1.0 }

fun sprawdz_dziel0(value: Double){
    if (stosZnakow.isNotEmpty()){
        if (stosZnakow.peek() == ('/') && value == ZERO){
            print("Nie można dzielić przez zero")
            System.exit(ZERO.toInt()) }
    }
}

fun aktualizuj(sign: Char){
    stosZnakow.push(sign)
    wyswietlacz.add(sign.toString()) }

fun liczenie(){
    if(stosZnakow.isNotEmpty() && stosWartosci.isNotEmpty()) {

        when (stosZnakow.pop()) {
            '+' -> wynik = dodawanie(stosWartosci.pop(), stosWartosci.pop())
            '-' -> wynik = odejmowanie(stosWartosci.pop(), stosWartosci.pop())
            '*' -> wynik = mnozenie(stosWartosci.pop(), stosWartosci.pop())
            '/' -> wynik = dzielenie(stosWartosci.pop(), stosWartosci.pop())
        }
        stosWartosci.push(wynik) }
}

fun wpisz() : Boolean{
    println("Wartość/Znak: ")
    val wpisana = readLine()
    try{
        val wpisanaWartosc = wpisana!!.toDouble()
        wpisano_liczbe(wpisanaWartosc)
        return true }

    catch (nfe: NumberFormatException){
        val wpisanyZnak = wpisana!![0]
        wpisano_znak(wpisanyZnak)
        //Kontynuj wpisywanie znaków pod warunkiem, że nie zostało wpisane '='
        return wpisanyZnak != '=' }
}

fun wpisano_liczbe(tymczasowa: Double) {
    sprawdz_dziel0(tymczasowa)
    stosWartosci.push(tymczasowa)
    wyswietlacz.add(tymczasowa.toString()) }

fun wpisano_znak(znak : Char) : Boolean {
    //Jeżeli stos liczb jest pusty a został wpisany znak dodaj do stosu liczb 0 na poczatku
    if(stosWartosci.isEmpty() && znak != '('){
        stosWartosci.push(ZERO)
        wyswietlacz.add((ZERO).toString()) }

    //'p' plus/minus +/-    //'s' silnia n!
    if(znak == 'p' || znak == 's'){
        var zmiana = stosWartosci.pop()
        wyswietlacz.removeAt(wyswietlacz.lastIndex)
        if(znak == 'p') zmiana = -zmiana
        if(znak == 's') zmiana = silnia(zmiana)
        stosWartosci.push(zmiana)
        wyswietlacz.add(zmiana.toString())
        return false }

    if(stosZnakow.isEmpty()){
        if (znak in arrayOf ('+','-','/','*','(')) {
            aktualizuj(znak)
            return true } }

    if (stosZnakow.isNotEmpty()){
        if (znak == ')'){
            wyswietlacz.add(znak.toString())
            do dzialanie()
            while(stosZnakow.peek() != '(')
            stosZnakow.pop()
            return true }

        if (znak in arrayOf('+','-') && stosZnakow.peek() in arrayOf('+','-')||
            znak in arrayOf('*','/') && stosZnakow.peek() in arrayOf('*','/')||
            znak in arrayOf('+','-','*','/') && stosZnakow.peek() in arrayOf('*','/')){
            dzialanie()
            aktualizuj(znak)
            return true }

        if (znak in arrayOf('+','-') && stosZnakow.peek() in arrayOf('*','/','(',')')||
            znak in arrayOf('*','/') && stosZnakow.peek() in arrayOf('+','-')||
            znak in arrayOf('*','/') && stosZnakow.peek() in arrayOf('(',')')||
            znak == ('(') && stosZnakow.peek() in arrayOf('+','-','*','/')){
            aktualizuj(znak)
            return true }

        if(znak == '='){
            do dzialanie()
                while(stosZnakow.isNotEmpty())
            return false
        }
    }
    return false
}

fun dzialanie(){
    println("Przed dokonaniem działania")
    println("\t stosWartosci: $stosWartosci")
    println("\t stosZnakow: $stosZnakow")

    liczenie()

    println("Po dokonaniu działania")
    println("\t stosWartosci: $stosWartosci")
    println("\t stosZnakow: $stosZnakow") }

fun main() {
    println("Szymon Woyda 227458")

    do{
        if (pierwsza_liczba) {
            pierwsza_liczba = false
            wpisz() }

        val kontynuuj: Boolean = wpisz()
       if(kontynuuj) wpisz()
    } while (kontynuuj)

    println(wyswietlacz)
    println("Wynik: $wynik")
}