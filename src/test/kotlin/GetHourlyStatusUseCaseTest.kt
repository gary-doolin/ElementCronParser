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
        val scheduledTime = "33 *"
        val currentTime = "16:30"
        val runTimeStatus = useCase.invoke(scheduledTime, currentTime)

        assertEquals("16:33 today", runTimeStatus.status)
    }

    @Test
    fun `when scheduled runtime minute is less than current time minute, hour is current time hour +1`() {
        val scheduledTime = "33 *"
        val currentTime = "16:34"
        val runTimeStatus = useCase.invoke(scheduledTime, currentTime)

        assertEquals("17:33 today", runTimeStatus.status)
    }
}
