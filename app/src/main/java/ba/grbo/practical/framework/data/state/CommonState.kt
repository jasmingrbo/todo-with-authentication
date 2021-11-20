package ba.grbo.practical.framework.data.state

import androidx.annotation.StringRes
import ba.grbo.practical.framework.mics.DEFAULT

data class Feedback(@StringRes val message: Int, val error: Boolean) {
    companion object {
        val DEFAULT = Feedback(message = Int.DEFAULT, error = false)
    }
}