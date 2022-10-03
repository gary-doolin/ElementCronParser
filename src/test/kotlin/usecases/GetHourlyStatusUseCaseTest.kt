package usecases

import ConfigItem
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class GetHourlyStatusUseCaseTest {
    lateinit var useCase: GetHourlyStatusUseCase

    @BeforeTest
    fun setup() {
        useCase = GetHourlyStatusUseCase()
    }

    @Test
    fun `when scheduled runtime minute is greater than current time minute, hour is current time hour`() {
        val currentTime = "16:30"
        val configItem = ConfigItem("33 *", "/bin/run_me_hourly")
        val runTimeStatus = useCase.invoke(configItem, currentTime)

        assertEquals("16:33 today /bin/run_me_hourly", runTimeStatus)
    }

    @Test
    fun `when scheduled runtime minute is less than current time minute, hour is current time hour +1`() {
        val currentTime = "16:34"
        val configItem = ConfigItem("33 *", "/bin/run_me_hourly")

        val runTimeStatus = useCase.invoke(configItem, currentTime)

        assertEquals("17:33 today /bin/run_me_hourly", runTimeStatus)
    }
}
