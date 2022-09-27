class GetMinuteStatusUseCase {
    fun invoke(currentTime: String): RunTimeStatus.EachMinute = RunTimeStatus.EachMinute("$currentTime today")
}
