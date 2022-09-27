sealed class RunTimeStatus {
    data class EachMinute (val status: String): RunTimeStatus()
    data class SixtyTimes (val status: String): RunTimeStatus()
    data class Hourly (val status: String): RunTimeStatus()
    data class Daily (val status: String): RunTimeStatus()
    data class Error (val status: String): RunTimeStatus()
}

