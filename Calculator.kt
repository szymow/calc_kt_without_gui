import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import tornadofx.*
import javafx.scene.input.KeyEvent
import Operator.*
import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.scene.layout.AnchorPane
import java.util.Stack

const val ZERO : Double = 0.0
var wynik : Double = ZERO
var pierwsza_liczba : Boolean = true

var stosWartosci = Stack<Double>()
var stosZnakow = Stack<Char>()

var wyswietlacz = ArrayList<String>()

fun silnia(item1: Double) : Double{
    val factorial : Double = silnia_recursja(item1)
    return factorial }

fun silnia_recursja(num: Double): Double {
    if (num >= 1.0)     return num * silnia_recursja(num - 1.0)
    else        return 1.0 }

fun aktualizuj(sign: Char){
    stosZnakow.push(sign)
    wyswietlacz.add(sign.toString()) }

class Calculator : View() {
    override val root: VBox by fxml()
    @FXML lateinit var display: Label
    @FXML lateinit var display2: Label

    init {
        title = "Szymon Woyda 227458"

        root.lookupAll(".button").forEach{ b ->
            b.setOnMouseClicked {
                operator((b as Button).text)
            }
        }

        root.addEventFilter(KeyEvent.KEY_TYPED){
            operator(it.character.toUpperCase().replace("\r","="))
        }
    }

    var state: Operator = add(0.0)

    fun onAction(fn: Operator){
        state=fn
        display.text=""
    }

    val displayValue: Double
        get() = when(display.text){
            ""->0.0
            else->display.text.toDouble()
        }

    fun sprawdz_dziel0(value: Double){
        if (stosZnakow.isNotEmpty()){
            if (stosZnakow.peek() == ('/') && value == ZERO){
                print("Nie można dzielić przez zero")
                System.exit(ZERO.toInt()) }
        }
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

    private fun operator(x:String) : Boolean{
        if(Regex("[0-9.]").matches(x)){
            display.text += x
            wpisano_liczbe(wpisanaWartosc)
        }
        else{
            wpisano_znak(wpisanyZnak)
        }
    }
}