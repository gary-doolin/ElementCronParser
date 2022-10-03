package usecases

class GetSixtyStatusUseCase {
    fun invoke(scheduledTime: String): String {
        val scheduledTimeElements = scheduledTime.trim().split("\\s+".toRegex())
        val scheduledTimeHour = scheduledTimeElements[1]

        return "$scheduledTimeHour:00 today"
    }
}
