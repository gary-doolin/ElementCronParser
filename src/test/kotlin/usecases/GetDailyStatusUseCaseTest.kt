package usecases

import ConfigItem
import org.junit.jupiter.api.Test
import kotlin.test.BeforeTest
import kotlin.test.assertEquals

class GetDailyStatusUseCaseTest {
    lateinit var useCase: GetDailyStatusUseCase

    @BeforeTest
    fun setup() {
        useCase = GetDailyStatusUseCase()
    }

    @Test
    fun `when scheduled time (hour) is greater than current time (hour), day is today`() {
        val currentTime = "13:10"
        val configItem = ConfigItem("30 16", "/bin/run_me_daily")
        val scheduleStatus = useCase.invoke(configItem, currentTime)

        assertEquals("16:30 today /bin/run_me_daily", scheduleStatus)
    }

    @Test
    fun `when scheduled time (hour) is less than current time (hour), day is tomorrow`() {
        val currentTime = "16:30"
        val configItem = ConfigItem("20 15", "/bin/run_me_daily")
        val scheduleStatus = useCase.invoke(configItem, currentTime)

        assertEquals("15:20 tomorrow /bin/run_me_daily", scheduleStatus)
    }

    @Test
    fun `when scheduled time (hour) is equal to current time (hour), scheduled time (minutes) are less than current time (minutes), day is tomorrow`() {
        val currentTime = "16:30"
        val configItem = ConfigItem("20 16", "/bin/run_me_daily")

        val scheduleStatus = useCase.invoke(configItem, currentTime)

        assertEquals("16:20 tomorrow /bin/run_me_daily", scheduleStatus)
    }

    @Test
    fun `when scheduled time (hour) is equal to current time (hour), scheduled time (minutes) are greater than current time (minutes), day is today`() {
        val currentTime = "16:30"
        val configItem = ConfigItem("40 16", "/bin/run_me_daily")

        val scheduleStatus = useCase.invoke(configItem, currentTime)

        assertEquals("16:40 today /bin/run_me_daily", scheduleStatus)
    }
}
