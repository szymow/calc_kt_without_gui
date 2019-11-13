import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import tornadofx.*
import javafx.scene.input.KeyEvent
import Operator.*
import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.scene.layout.AnchorPane

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

    private fun operator(x:String){
        if(Regex("[0-9.]").matches(x)){
            display.text += x
        }
        else{
            when(x){
                "+" -> onAction(add(displayValue))
                "-" -> onAction(sub(displayValue))
                "*" -> onAction(mult(displayValue))
                "/" -> onAction(div(displayValue))
                "=" -> display.text = state.calculate((displayValue)).toString()
            }
        }
    }
}