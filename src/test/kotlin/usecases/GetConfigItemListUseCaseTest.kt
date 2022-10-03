package usecases

import ConfigItem
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import mapper.ConfigListItemMapper
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class GetConfigItemListUseCaseTest {

    @MockK
    lateinit var mapper: ConfigListItemMapper
    lateinit var useCase: GetConfigItemListUseCase

    @BeforeTest
    fun setup() {
        MockKAnnotations.init(this)
        useCase = GetConfigItemListUseCase(mapper)
    }

    @Test
    fun `when calling invoke with full time and command, mapper is called correctly`() {
        val list = listOf("30 1 /bin/run_me_daily")
        every { mapper.map(any()) } returns ConfigItem("30 1", "run_me_daily")
        val config = useCase.invoke(list)

        assertEquals("30 1", config[0].scheduledTime)
        assertEquals("run_me_daily", config[0].command)
    }

    @Test
    fun `when calling invoke with partial time and command, mapper is called correctly`() {
        val list = listOf("30 * /bin/run_me_hourly")
        every { mapper.map(any()) } returns ConfigItem("30 *", "run_me_hourly")
        val config = useCase.invoke(list)

        verify { mapper.map("30 * /bin/run_me_hourly") }

        assertEquals("30 *", config[0].scheduledTime)
        assertEquals("run_me_hourly", config[0].command)
    }
}
