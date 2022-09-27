class GetHourlyStatusUseCase {
    fun invoke(scheduledTime: String, currentTime: String): RunTimeStatus.Hourly {
        val scheduledTimeElements = scheduledTime.trim().split("\\s+".toRegex())
        val scheduledTimeMinute = scheduledTimeElements[0].toInt()

        val currentTimeElements = currentTime.split(":")
        val currentTimeHourString = currentTimeElements[0]
        val currentTimeHourInt = currentTimeHourString.toInt()
        val currentTimeMinute = currentTimeElements[1].toInt()

        val nextScheduledTime = if (scheduledTimeMinute > currentTimeMinute)
            getNextHourlyRunTime(currentTimeHourInt, scheduledTimeMinute)
        else
            getNextHourlyRunTime(currentTimeHourInt+1, scheduledTimeMinute)

        return RunTimeStatus.Hourly(nextScheduledTime)
    }

    private fun getNextHourlyRunTime(hour: Int, scheduledTimeMinute:Int) =
        "$hour:$scheduledTimeMinute today"
}
