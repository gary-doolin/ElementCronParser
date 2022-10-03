package usecases

class GetMinuteStatusUseCase {
    fun invoke(currentTime: String): String = "$currentTime today"
}
