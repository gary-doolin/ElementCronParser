package usecases

import ConfigItem
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
        val configItem = ConfigItem("* *", "/bin/run_me_every_minute")
        val runTimeStatus = useCase.invoke("15:10", configItem.command)

        assertEquals("15:10 today /bin/run_me_every_minute", runTimeStatus)
    }
}
