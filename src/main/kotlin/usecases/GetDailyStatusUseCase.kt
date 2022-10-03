package usecases

import ConfigItem

private const val TODAY = "today"
private const val TOMORROW = "tomorrow"

class GetDailyStatusUseCase {
    fun invoke(configItem: ConfigItem, currentTime: String) : String{
        val day = getScheduledDay(configItem.scheduledTime, currentTime)
        val time = getFormattedScheduledTime(configItem.scheduledTime)
        return "$time $day ${configItem.command}"
    }

    private fun getScheduledDay(scheduledTime: String, currentTime: String): String {
        val scheduledTimeElements = scheduledTime.trim().split("\\s+".toRegex())
        val scheduledTimeHour = scheduledTimeElements[1].toInt()
        val scheduledTimeMinute = scheduledTimeElements[0].toInt()

        val currentTimeElements = currentTime.split(":")
        val currentTimeHour = currentTimeElements[0].toInt()
        val currentTimeMinute = currentTimeElements[1].toInt()

        if (scheduledTimeHour == currentTimeHour) {
            return if (scheduledTimeMinute < currentTimeMinute) TOMORROW else TODAY
        }

        return if (scheduledTimeHour < currentTimeHour) TOMORROW else TODAY
    }

    private fun getFormattedScheduledTime(scheduledTime: String): String {
        val scheduleTimeElements: List<String> = scheduledTime.trim().split("\\s+".toRegex())
        return scheduleTimeElements[1]+":"+ scheduleTimeElements[0]
    }
}