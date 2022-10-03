package usecases

import ConfigItem

class GetHourlyStatusUseCase {
    fun invoke(configItem: ConfigItem, currentTime: String): String {
        val scheduledTimeElements = configItem.scheduledTime.trim().split("\\s+".toRegex())
        val scheduledTimeMinute = scheduledTimeElements[0].toInt()
        val currentTimeElements = currentTime.split(":")
        val currentTimeHourString = currentTimeElements[0]
        val currentTimeHourInt = currentTimeHourString.toInt()
        val currentTimeMinute = currentTimeElements[1].toInt()

        return if (scheduledTimeMinute > currentTimeMinute)
            getNextHourlyRunTime(configItem, currentTimeHourInt, scheduledTimeMinute)
        else
            getNextHourlyRunTime(configItem, currentTimeHourInt + 1, scheduledTimeMinute)
    }

    private fun getNextHourlyRunTime(configItem: ConfigItem, hour: Int, scheduledTimeMinute:Int) =
        "$hour:$scheduledTimeMinute today "+configItem.command
}