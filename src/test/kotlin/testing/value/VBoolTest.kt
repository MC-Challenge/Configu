package testing.value

import net.challenge.configu.value.impl.VBool
import org.junit.Assert
import org.junit.Test

class VBoolTest {

    @Test
    fun toggleValue() {
        val setting = VBool(true)
        setting.toggle()

        Assert.assertEquals(setting.value, false)
    }
}