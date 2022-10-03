package viewmodel

import usecases.*

private const val RUN_ME_DAILY = "run_me_daily"
private const val RUN_ME_HOURLY = "run_me_hourly"
private const val RUN_ME_EVERY_MINUTE = "run_me_every_minute"
private const val RUN_ME_SIXTY_TIMES = "run_me_sixty_times"

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
           val scheduleStatus =  when (configItem.command) {
                RUN_ME_DAILY -> getDailyStatusUseCase.invoke(configItem.scheduledTime, currentTime)
                RUN_ME_HOURLY -> getHourlyStatusUseCase.invoke(configItem.scheduledTime, currentTime)
                RUN_ME_EVERY_MINUTE -> getMinuteStatusUseCase.invoke(currentTime)
                RUN_ME_SIXTY_TIMES -> getSixtyStatusUseCase.invoke(configItem.scheduledTime)
                else -> "Invalid"
            }

            println(scheduleStatus)
        }
    }
}
