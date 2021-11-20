package ba.grbo.practical.presentation.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ba.grbo.practical.framework.data.state.LoginEvent
import ba.grbo.practical.framework.data.state.LoginEvent.EmailChanged
import ba.grbo.practical.framework.data.state.LoginEvent.FacebookLoginButtonClicked
import ba.grbo.practical.framework.data.state.LoginEvent.GoogleLoginButtonClicked
import ba.grbo.practical.framework.data.state.LoginEvent.LoginButtonClicked
import ba.grbo.practical.framework.data.state.LoginEvent.PasswordChanged
import ba.grbo.practical.framework.data.state.Screen
import ba.grbo.practical.presentation.login.Login
import ba.grbo.practical.presentation.login.LoginViewModel

@Composable
fun PracticalNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.LOGIN.route
    ) {
        composable(Screen.LOGIN.route) {
            val viewModel = hiltViewModel<LoginViewModel>()
            Login(
                email = viewModel.state.email,
                password = viewModel.state.password,
                feedback = viewModel.state.feedback,
                onEmailChanged = { email -> viewModel.onEvent(EmailChanged(email)) },
                onPasswordChanged = { pw -> viewModel.onEvent(PasswordChanged(pw)) },
                onLoginClicked = { viewModel.onEvent(LoginButtonClicked) },
                onGoogleLoginClicked = { viewModel.onEvent(GoogleLoginButtonClicked) },
                onFacebookLoginClicked = { viewModel.onEvent(FacebookLoginButtonClicked) }
            )
        }

        composable(Screen.SIGN_UP.route) {

        }

        composable(Screen.HOME.route) {

        }
    }
}