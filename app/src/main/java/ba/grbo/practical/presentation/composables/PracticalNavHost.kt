package ba.grbo.practical.presentation.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ba.grbo.practical.framework.data.state.LoginEvent.EmailChanged
import ba.grbo.practical.framework.data.state.LoginEvent.FacebookLoginButtonClicked
import ba.grbo.practical.framework.data.state.LoginEvent.GoogleLoginButtonClicked
import ba.grbo.practical.framework.data.state.LoginEvent.LoginButtonClicked
import ba.grbo.practical.framework.data.state.LoginEvent.PasswordChanged
import ba.grbo.practical.framework.data.state.LoginEvent.PasswordVisibilityButtonClicked
import ba.grbo.practical.framework.data.state.LoginEvent.ResetEmailButtonClicked
import ba.grbo.practical.framework.data.state.LoginEvent.ResetPasswordButtonClicked
import ba.grbo.practical.framework.data.state.RestorePasswordEvent
import ba.grbo.practical.framework.data.state.Screen.HOME
import ba.grbo.practical.framework.data.state.Screen.LOGIN
import ba.grbo.practical.framework.data.state.Screen.RESTORE_PASSWORD
import ba.grbo.practical.framework.data.state.Screen.SIGN_UP
import ba.grbo.practical.framework.data.state.SignUpEvent
import ba.grbo.practical.presentation.screens.home.HomeScreen
import ba.grbo.practical.presentation.screens.home.HomeViewModel
import ba.grbo.practical.presentation.screens.login.LoginScreen
import ba.grbo.practical.presentation.screens.login.LoginViewModel
import ba.grbo.practical.presentation.screens.restorepassword.RestorePasswordScreen
import ba.grbo.practical.presentation.screens.restorepassword.RestorePasswordViewModel
import ba.grbo.practical.presentation.screens.signup.SignUpScreen
import ba.grbo.practical.presentation.screens.signup.SignUpViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun PracticalNavHost(
    modifier: Modifier = Modifier,
    auth: FirebaseAuth,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = if (auth.currentUser == null) LOGIN.route else HOME.route
    ) {
        composable(LOGIN.route) {
            val viewModel = hiltViewModel<LoginViewModel>()
            LoginScreen(
                email = viewModel.state.email,
                password = viewModel.state.password,
                onEmailChange = { email -> viewModel.onEvent(EmailChanged(email)) },
                onResetEmailButtonClicked = { viewModel.onEvent(ResetEmailButtonClicked) },
                onPasswordChange = { pw -> viewModel.onEvent(PasswordChanged(pw)) },
                onForgotPasswordTextClicked = { navController.navigate(RESTORE_PASSWORD.route) },
                onPasswordVisibilityButtonClicked = {
                    viewModel.onEvent(PasswordVisibilityButtonClicked)
                },
                onResetPasswordButtonClicked = { viewModel.onEvent(ResetPasswordButtonClicked) },
                onLoginButtonClicked = { viewModel.onEvent(LoginButtonClicked) },
                onGoogleLoginButtonClicked = { viewModel.onEvent(GoogleLoginButtonClicked) },
                onFacebookLoginButtonClicked = { viewModel.onEvent(FacebookLoginButtonClicked) },
                onSignUpTextClicked = { navController.navigate(SIGN_UP.route) }
            )
        }

        composable(SIGN_UP.route) {
            val viewModel = hiltViewModel<SignUpViewModel>()
            SignUpScreen(
                email = viewModel.state.email,
                password = viewModel.state.password,
                repeatedPassword = viewModel.state.repeatedPassword,
                onEmailChange = { email -> viewModel.onEvent(SignUpEvent.EmailChanged(email)) },
                onResetEmailButtonClicked = {
                    viewModel.onEvent(SignUpEvent.ResetEmailButtonClicked)
                },
                onPasswordChange = { password ->
                    viewModel.onEvent(SignUpEvent.PasswordChanged(password))
                },
                onPasswordVisibilityButtonClicked = {
                    viewModel.onEvent(SignUpEvent.PasswordVisibilityButtonClicked)
                },
                onResetPasswordButtonClicked = {
                    viewModel.onEvent(SignUpEvent.ResetPasswordButtonClicked)
                },
                onRepeatedPasswordChange = { password ->
                    viewModel.onEvent(SignUpEvent.RepeatedPasswordChanged(password))
                },
                onRepeatedPasswordVisibilityButtonClicked = {
                    viewModel.onEvent(SignUpEvent.RepeatedPasswordVisibilityButtonClicked)
                },
                onResetRepeatedPasswordButtonClicked = {
                    viewModel.onEvent(SignUpEvent.ResetRepeatedPasswordButtonClicked)
                },
                onGoogleSignUpButtonClicked = {
                    viewModel.onEvent(SignUpEvent.GoogleSignUpButtonClicked)
                },
                onFacebookSignUpButtonClicked = {
                    viewModel.onEvent(SignUpEvent.FacebookSignUpButtonClicked)
                },
                onSignUpButtonClicked = { viewModel.onEvent(SignUpEvent.SignUpButtonClicked) }
            )
        }

        composable(RESTORE_PASSWORD.route) {
            val viewModel = hiltViewModel<RestorePasswordViewModel>()
            RestorePasswordScreen(
                email = viewModel.state.email,
                onEmailChange = { email ->
                    viewModel.onEvent(RestorePasswordEvent.EmailChanged(email))
                },
                onResetEmailButtonClicked = {
                    viewModel.onEvent(RestorePasswordEvent.ResetEmailButtonClicked)
                },
                onRestorePasswordClicked = {
                    viewModel.onEvent(RestorePasswordEvent.RestorePasswordButtonClicked)
                }
            )
        }

        composable(HOME.route) {
            val viewModel = hiltViewModel<HomeViewModel>()
            HomeScreen()
        }
    }
}