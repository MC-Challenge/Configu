import net.challenge.configu.container.ValueContainer
import net.challenge.configu.events.ValueChangeEvent
import net.challenge.configu.events.ValueChangeListener
import net.challenge.configu.scanner.ValueScanner
import net.challenge.configu.value.VTag
import net.challenge.configu.value.impl.VBool
import net.challenge.configu.value.impl.VColor
import net.challenge.configu.value.impl.VNumber
import java.awt.Color

class Module(private val name: String) : ValueContainer(ValueScanner) {

    @VTag(name = "Enabled")
    val enabled = VBool(false)

    @VTag(name = "Color")
    val color = VColor(Color(55, 200, 0, 20))

    @VTag(name = "Size")
    val size = VNumber(3, 2, 100)
        .setChangeListener(object : ValueChangeListener<Double> {
            override fun onChange(event: ValueChangeEvent<Double>) {
                println("Change to " + event.after)
            }
        })

    @VTag(name = "Scale")
    val scale = VNumber(4, 1, 103)

    override fun getFileName(): String {
        return name
    }
}