package test.createx.dogsimulator.utils

class FormatTimeUtils {
    companion object {
        fun formatMilliseconds(milliseconds: Long): String {
            val totalSeconds = milliseconds / 1000
            val minutes = totalSeconds / 60
            val seconds = totalSeconds % 60
            return if (seconds < 10) {
                "$minutes:0$seconds"
            } else {
                "$minutes:$seconds"
            }
        }
    }
}