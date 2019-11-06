
class StackWithList{
    val elements: MutableList<Any> = mutableListOf()
    fun isStackEmpty() = elements.isEmpty()
    fun size() = elements.size
    fun push(item: Any) = elements.add(item)
    fun pop() : Any? {
        val item = elements.lastOrNull()
        if(!isStackEmpty()){
            elements.removeAt(size()-1)
        }
        return item
    }
    fun peek(): Any? = elements.lastOrNull()

    override fun toString(): String = elements.toString()
}


val set_of_signs = charArrayOf('+','-','*','/')
var wynik : Any = 0
var niewlasciwyznak  = false

fun dodawanie(item1: Int, item2: Int): Int = item1 + item2
fun odejmowanie(item1: Int, item2: Int): Int = item1 - item2
fun mnozenie(item1: Int, item2: Int): Int = item1 * item2
fun dzielenie(item1: Int, item2: Int) : Any {
    if(item2 != 0) return item1 / item2
    else return "DZIEL0"
}


fun main(args: Array<String>) {

    println("Hello Kotlin!")

    var stackOfValues = StackWithList()
    var stackOfSigns = StackWithList()

    print("Enter value: ")
    val enteredValue = Integer.valueOf(readLine())
    stackOfValues.push(enteredValue)

    print("Enter sign: ")
    val enteredSign = readLine()!![0] //Czytaj tylko jeden, pierwszy wprowadzony znak
    stackOfSigns.push(enteredSign)

    print("Enter value: ")
    val enteredValue2 = Integer.valueOf(readLine())
    stackOfValues.push(enteredValue2)

    println("stackOfValues: " + stackOfValues)
    println("stackOfSigns: " + stackOfSigns)

    when (enteredSign) {
        '+' -> wynik = dodawanie(enteredValue,enteredValue2)
        '-' -> wynik = odejmowanie(enteredValue,enteredValue2)
        '*' -> wynik = mnozenie(enteredValue,enteredValue2)
        '/' -> wynik = dzielenie(enteredValue,enteredValue2)

        else -> { // Note the block
            niewlasciwyznak = true
//            print("Niewłaściwy znak \n")
        }
    }
    if(!niewlasciwyznak){
        println("Wynik: " + wynik)
    }
    else
    println("Wynik: " + "niewłaściwy znak")

}

//https://chercher.tech/kotlin/stack-kotlin