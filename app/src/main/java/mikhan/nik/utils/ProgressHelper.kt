package mikhan.nik.utils

object ProgressHelper {
    fun getProgress (allItemsCount: Int, selectedItemsCount: Int,): Float {
        return if (selectedItemsCount == 0) 0.0f
        else selectedItemsCount.toFloat() / allItemsCount.toFloat()
    }
}