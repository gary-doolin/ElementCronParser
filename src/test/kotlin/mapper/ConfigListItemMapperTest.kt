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
        assertEquals("/bin/run_me_daily", configItem.command)
    }

    @Test
    fun `when passing hourly config item string, it maps correctly`() {
        val configItem = mapper.map("30 * /bin/run_me_hourly")

        assertEquals("30 *", configItem.scheduledTime)
        assertEquals("/bin/run_me_hourly", configItem.command)
    }

    @Test
    fun `when passing minute config item string, it maps correctly`() {
        val configItem = mapper.map("* * /bin/run_me_every_minute")

        assertEquals("* *", configItem.scheduledTime)
        assertEquals("/bin/run_me_every_minute", configItem.command)
    }

    @Test
    fun `when passing sixty time config item string, it maps correctly`() {
        val configItem = mapper.map("* 12 /bin/run_me_sixty_times")

        assertEquals("* 12", configItem.scheduledTime)
        assertEquals("/bin/run_me_sixty_times", configItem.command)
    }
}
