package usecases

class GetMinuteStatusUseCase {
    fun invoke(currentTime: String, command: String): String = "$currentTime today $command"
}
