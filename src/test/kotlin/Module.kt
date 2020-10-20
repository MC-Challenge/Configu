import net.challenge.configu.container.ValueContainer
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
    val size = VNumber(-4).setDistance(2, 100)

    @VTag(name = "Scale")
    val scale = VNumber(10.3432432434).setDistance(10.0, 31.0)

    override fun getFileName(): String {
        return name
    }
}