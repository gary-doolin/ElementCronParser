package mapper

import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ConfigListItemMapperTest {
    lateinit var mapper: ConfigListItemMapper

    @BeforeTest
    fun setup() {
        mapper = ConfigListItemMapper()
    }

    @Test
    fun `when passing daily config item string, it maps correctly`() {
        val configItem = mapper.map("30 1 /bin/run_me_daily")

        assertEquals("30 1", configItem.scheduledTime)
        assertEquals("run_me_daily", configItem.command)
    }

    @Test
    fun `when passing hourly config item string, it maps correctly`() {
        val configItem = mapper.map("30 * /bin/run_me_hourly")

        assertEquals("30 *", configItem.scheduledTime)
        assertEquals("run_me_hourly", configItem.command)
    }
}
