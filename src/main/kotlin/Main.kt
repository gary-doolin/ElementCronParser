import mapper.ConfigListItemMapper
import usecases.*
import viewmodel.CronViewModel
import java.io.File

fun main(args: Array<String>) {

    val mapper = ConfigListItemMapper()
    val getHourlyStatusUseCase = GetHourlyStatusUseCase()
    val getDailyStatusUseCase = GetDailyStatusUseCase()
    val getSixtyStatusUseCase = GetSixtyStatusUseCase()
    val getMinuteStatusUseCase = GetMinuteStatusUseCase()
    val getConfigItemListUseCase = GetConfigItemListUseCase(mapper)
    val viewModel = CronViewModel(
        getHourlyStatusUseCase,
        getDailyStatusUseCase,
        getSixtyStatusUseCase,
        getMinuteStatusUseCase,
        getConfigItemListUseCase
    )

    val configList = readFileToList(args[1])
    viewModel.getNextScheduledRun(args[0], configList)
}
private fun readFileToList(fileName: String): List<String> = File(fileName).readLines()
