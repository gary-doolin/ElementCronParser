package viewmodel

import ConfigItem
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import usecases.*
import kotlin.test.BeforeTest
import kotlin.test.Test

class CronViewModelTest {

    @MockK lateinit var getSixtyStatusUseCase: GetSixtyStatusUseCase
    @MockK lateinit var getHourlyStatusUseCase: GetHourlyStatusUseCase
    @MockK lateinit var getMinuteStatusUseCase: GetMinuteStatusUseCase
    @MockK lateinit var getDailyStatusUseCase: GetDailyStatusUseCase
    @MockK lateinit var getConfigItemListUseCase: GetConfigItemListUseCase

    lateinit var viewModel: CronViewModel

    @BeforeTest
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = CronViewModel(
            getHourlyStatusUseCase,
            getDailyStatusUseCase,
            getSixtyStatusUseCase,
            getMinuteStatusUseCase,
            getConfigItemListUseCase
        )
    }

    @Test
    fun `when get next scheduled run is called, get daily status is called`() {
        val list = listOf("30 1 /bin/run_me_daily")
        every { getDailyStatusUseCase.invoke(any(), any()) } returns "1:30 today"
        every { getConfigItemListUseCase.invoke(any()) } returns listOf(ConfigItem("1:30", "/bin/run_me_daily"))

        viewModel.getNextScheduledRun("14:20", list)
        verify(exactly = 0) { getHourlyStatusUseCase.invoke(any(), any()) }
        verify(exactly = 0) { getMinuteStatusUseCase.invoke(any(), any()) }
        verify(exactly = 0) { getSixtyStatusUseCase.invoke(any())}
        verify(exactly = 1) { getConfigItemListUseCase.invoke(list) }
        verify(exactly = 1) {getDailyStatusUseCase.invoke(any(), any())}
    }

    @Test
    fun `when get next scheduled run is called, get hourly status is called`() {
        val list = listOf("30 1 /bin/run_me_hourly")
        every { getDailyStatusUseCase.invoke(any(), any()) } returns "1:30 today"
        every { getHourlyStatusUseCase.invoke(any(), any()) } returns "16:30 today"
        every { getConfigItemListUseCase.invoke(any()) } returns listOf(ConfigItem("16:30", "/bin/run_me_hourly"))

        viewModel.getNextScheduledRun("14:20", list)
        verify(exactly = 0) { getDailyStatusUseCase.invoke(any(), any()) }
        verify(exactly = 0) { getMinuteStatusUseCase.invoke(any(), any()) }
        verify(exactly = 0) { getSixtyStatusUseCase.invoke(any())}
        verify(exactly = 1) { getConfigItemListUseCase.invoke(list) }
        verify(exactly = 1) { getHourlyStatusUseCase.invoke(any(), any()) }
    }

    @Test
    fun `when get next scheduled run is called, get minute status is called`() {
        val list = listOf("30 1 /bin/run_me_every_minute")
        every { getDailyStatusUseCase.invoke(any(), any()) } returns "1:30 today"
        every { getHourlyStatusUseCase.invoke(any(), any()) } returns "16:30 today"
        every { getMinuteStatusUseCase.invoke(any(), any()) } returns "12:30 today"
        every { getConfigItemListUseCase.invoke(any()) } returns listOf(ConfigItem("16:30", "/bin/run_me_every_minute"))

        viewModel.getNextScheduledRun("14:20", list)

        verify(exactly = 0) { getDailyStatusUseCase.invoke(any(), any()) }
        verify(exactly = 0) { getHourlyStatusUseCase.invoke(any(), any()) }
        verify(exactly = 0) { getSixtyStatusUseCase.invoke(any())}
        verify(exactly = 1) { getConfigItemListUseCase.invoke(list) }
        verify(exactly = 1) { getMinuteStatusUseCase.invoke(any(), any()) }
    }

    @Test
    fun `when get next scheduled run is called, get sixty minute status is called`() {
        val list = listOf("30 1 /bin/run_me_sixty_times")
        every { getDailyStatusUseCase.invoke(any(), any()) } returns "1:30 today"
        every { getHourlyStatusUseCase.invoke(any(), any()) } returns "16:30 today"
        every { getMinuteStatusUseCase.invoke(any(), any()) } returns "12:30 today"
        every { getSixtyStatusUseCase.invoke(any()) } returns "17:00 today"
        every { getConfigItemListUseCase.invoke(any()) } returns listOf(ConfigItem("16:30", "/bin/run_me_sixty_times"))

        viewModel.getNextScheduledRun("14:20", list)

        verify(exactly = 0) { getDailyStatusUseCase.invoke(any(), any()) }
        verify(exactly = 0) { getHourlyStatusUseCase.invoke(any(), any()) }
        verify(exactly = 0) { getMinuteStatusUseCase.invoke(any(), any()) }
        verify(exactly = 1) { getConfigItemListUseCase.invoke(list) }
        verify(exactly = 1) { getSixtyStatusUseCase.invoke(any())}
    }

    @Test
    fun `when get next scheduled run is called, invalid schedule command is entered, verify no use case is called`() {
        val list = listOf("30 1 /bin/run_me_sixty_times")
        every { getDailyStatusUseCase.invoke(any(), any()) } returns "1:30 today"
        every { getHourlyStatusUseCase.invoke(any(), any()) } returns "16:30 today"
        every { getMinuteStatusUseCase.invoke(any(), any()) } returns "12:30 today"
        every { getSixtyStatusUseCase.invoke(any()) } returns "17:00 today"
        every { getConfigItemListUseCase.invoke(any()) } returns listOf(ConfigItem("16:30", "/bin/run_me_sixty_times_error"))

        viewModel.getNextScheduledRun("14:20", list)

        verify(exactly = 0) { getDailyStatusUseCase.invoke(any(), any()) }
        verify(exactly = 0) { getHourlyStatusUseCase.invoke(any(), any()) }
        verify(exactly = 0) { getMinuteStatusUseCase.invoke(any(), any()) }
        verify(exactly = 1) { getConfigItemListUseCase.invoke(list) }
        verify(exactly = 0) { getSixtyStatusUseCase.invoke(any())}
    }
}
