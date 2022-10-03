package viewmodel

import usecases.*

private const val RUN_ME_DAILY = "/bin/run_me_daily"
private const val RUN_ME_HOURLY = "/bin/run_me_hourly"
private const val RUN_ME_EVERY_MINUTE = "/bin/run_me_every_minute"
private const val RUN_ME_SIXTY_TIMES = "/bin/run_me_sixty_times"

class CronViewModel (
    private val getHourlyStatusUseCase: GetHourlyStatusUseCase,
    private val getDailyStatusUseCase: GetDailyStatusUseCase,
    private val getSixtyStatusUseCase: GetSixtyStatusUseCase,
    private val getMinuteStatusUseCase: GetMinuteStatusUseCase,
    private val getConfigItemListUseCase: GetConfigItemListUseCase
) {

    fun getNextScheduledRun(currentTime: String, configItemStringList: List<String>) {
        val configItemList = getConfigItemListUseCase.invoke(configItemStringList)

        configItemList.forEach {  configItem ->
            val scheduleStatus =
                when (configItem.command) {
                    RUN_ME_DAILY -> getDailyStatusUseCase.invoke(configItem, currentTime)
                    RUN_ME_HOURLY -> getHourlyStatusUseCase.invoke(configItem, currentTime)
                    RUN_ME_EVERY_MINUTE -> getMinuteStatusUseCase.invoke(currentTime, configItem.command)
                    RUN_ME_SIXTY_TIMES -> getSixtyStatusUseCase.invoke(configItem)
                    else -> "Invalid Command"
                }
            println(scheduleStatus)
        }
    }
}
