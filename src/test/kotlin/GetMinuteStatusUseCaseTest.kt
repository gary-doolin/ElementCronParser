import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class GetMinuteStatusUseCaseTest {

    lateinit var useCase: GetMinuteStatusUseCase

    @BeforeTest
    fun setup() {
        useCase = GetMinuteStatusUseCase()
    }

    @Test
    fun `when invoke is called, run time status is current time`() {
        val runTimeStatus = useCase.invoke("15:10")

        assertEquals("15:10 today", runTimeStatus.status)
    }
}