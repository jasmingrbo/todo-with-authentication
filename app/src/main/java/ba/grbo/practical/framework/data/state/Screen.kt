package ba.grbo.practical.framework.data.state

enum class Screen(val route: String) {
    LOGIN("login"),
    SIGN_UP("sign up"),
    RESTORE_PASSWORD("restore password"),
    HOME("home")
}