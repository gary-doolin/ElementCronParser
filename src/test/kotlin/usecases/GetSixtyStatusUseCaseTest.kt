package usecases

import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class GetSixtyStatusUseCaseTest {
    lateinit var useCase: GetSixtyStatusUseCase

    @BeforeTest
    fun setup() {
        useCase = GetSixtyStatusUseCase()
    }

    @Test
    fun `when invoke is called correct scheduled time is returned`() {
        val scheduledTime = "* 01"

        val runTimeStatus = useCase.invoke(scheduledTime)

        assertEquals("01:00 today", runTimeStatus)
    }
}
