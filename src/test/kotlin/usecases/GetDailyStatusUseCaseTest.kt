package usecases

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
        val scheduledTime = "30 16"
        val currentTime = "13:10"

        val scheduleStatus = useCase.invoke(scheduledTime, currentTime)

        assertEquals("16:30 today", scheduleStatus)
    }

    @Test
    fun `when scheduled time (hour) is less than current time (hour), day is tomorrow`() {
        val scheduledTime = "20 15"
        val currentTime = "16:30"

        val scheduleStatus = useCase.invoke(scheduledTime, currentTime)

        assertEquals("15:20 tomorrow", scheduleStatus)
    }

    @Test
    fun `when scheduled time (hour) is equal to current time (hour), scheduled time (minutes) are less than current time (minutes), day is tomorrow`() {
        val scheduledTime = "20 16"
        val currentTime = "16:30"

        val scheduleStatus = useCase.invoke(scheduledTime, currentTime)

        assertEquals("16:20 tomorrow", scheduleStatus)
    }

    @Test
    fun `when scheduled time (hour) is equal to current time (hour), scheduled time (minutes) are greater than current time (minutes), day is today`() {
        val scheduledTime = "40 16"
        val currentTime = "16:30"

        val scheduleStatus = useCase.invoke(scheduledTime, currentTime)

        assertEquals("16:40 today", scheduleStatus)
    }
}
