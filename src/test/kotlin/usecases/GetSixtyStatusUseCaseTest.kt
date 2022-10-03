package usecases

import ConfigItem
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
        val configItem = ConfigItem("* 01", "/bin/run_me_sixty_times")
        val runTimeStatus = useCase.invoke(configItem)

        assertEquals("01:00 today /bin/run_me_sixty_times", runTimeStatus)
    }
}
