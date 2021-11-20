package ba.grbo.practical.framework.data.state

import ba.grbo.practical.framework.mics.DEFAULT

data class Feedback(val message: String, val error: Boolean) {
    companion object {
        val DEFAULT = Feedback(message = String.DEFAULT, error = false)
    }
}