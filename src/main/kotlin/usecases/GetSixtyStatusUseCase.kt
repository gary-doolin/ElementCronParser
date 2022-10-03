package usecases

import ConfigItem

class GetSixtyStatusUseCase {
    fun invoke(configItem: ConfigItem): String {
        val scheduledTimeElements = configItem.scheduledTime.trim().split("\\s+".toRegex())
        val scheduledTimeHour = scheduledTimeElements[1]

        return "$scheduledTimeHour:00 today " + configItem.command
    }
}
