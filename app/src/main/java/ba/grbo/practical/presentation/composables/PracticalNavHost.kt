package ba.grbo.practical.presentation.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
import ba.grbo.practical.framework.data.state.Screen
import ba.grbo.practical.framework.data.state.SignUpEvent
import ba.grbo.practical.presentation.login.LoginScreen
import ba.grbo.practical.presentation.login.LoginViewModel
import ba.grbo.practical.presentation.signup.SignUpScreen
import ba.grbo.practical.presentation.signup.SignUpViewModel

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
            LoginScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                email = viewModel.state.email,
                password = viewModel.state.password,
                feedback = viewModel.state.feedback,
                onEmailChange = { email -> viewModel.onEvent(EmailChanged(email)) },
                onResetEmailButtonClicked = { viewModel.onEvent(ResetEmailButtonClicked) },
                onPasswordChange = { pw -> viewModel.onEvent(PasswordChanged(pw)) },
                onPasswordVisibilityButtonClicked = {
                    viewModel.onEvent(PasswordVisibilityButtonClicked)
                },
                onResetPasswordButtonClicked = { viewModel.onEvent(ResetPasswordButtonClicked) },
                onLoginButtonClicked = { viewModel.onEvent(LoginButtonClicked) },
                onGoogleLoginButtonClicked = { viewModel.onEvent(GoogleLoginButtonClicked) },
                onFacebookLoginButtonClicked = { viewModel.onEvent(FacebookLoginButtonClicked) },
                onSignUpTextClicked = { navController.navigate(Screen.SIGN_UP.route) }
            )
        }

        composable(Screen.SIGN_UP.route) {
            val viewModel = hiltViewModel<SignUpViewModel>()
            SignUpScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                email = viewModel.state.email,
                password = viewModel.state.password,
                feedback = viewModel.state.feedback,
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

        composable(Screen.HOME.route) {

        }
    }
}