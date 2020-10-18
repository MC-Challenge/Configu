import net.challenge.configu.container.ValueContainer
import net.challenge.configu.scanner.ValueScanner
import net.challenge.configu.value.VTag
import net.challenge.configu.value.impl.VBool
import net.challenge.configu.value.impl.VColor
import java.awt.Color

class Module(private val name: String) : ValueContainer(ValueScanner) {

    @VTag(name = "Enabled")
    val enabled = VBool(false)

    @VTag(name = "Color")
    val color = VColor(Color(55, 200, 0, 20))

    override fun getFileName(): String {
        return name
    }
}