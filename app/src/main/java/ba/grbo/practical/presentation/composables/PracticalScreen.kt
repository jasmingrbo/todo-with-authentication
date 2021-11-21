package ba.grbo.practical.presentation.composables

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect

@Composable
fun PracticalScreen(
    modifier: Modifier = Modifier,
    loggedIn: Boolean?,
) {
    Crossfade(targetState = loggedIn) { targetState ->
        if (targetState == null) Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(modifier = Modifier.size(36.dp))
        } else {
            val navController = rememberNavController()
            NavHost(
                modifier = modifier,
                navController = navController,
                startDestination = if (targetState) HOME.route else LOGIN.route
            ) {
                composable(LOGIN.route) {
                    val viewModel = hiltViewModel<LoginViewModel>()

                    NavigateToHome(
                        flow = viewModel.loggedIn,
                        navController = navController
                    )

                    LoginScreen(
                        email = viewModel.state.email,
                        password = viewModel.state.password,
                        loading = viewModel.state.loading,
                        feedback = viewModel.state.feedback,
                        onEmailChange = { email -> viewModel.onEvent(EmailChanged(email)) },
                        onResetEmailButtonClicked = { viewModel.onEvent(ResetEmailButtonClicked) },
                        onPasswordChange = { pw -> viewModel.onEvent(PasswordChanged(pw)) },
                        onForgotPasswordTextClicked = { navController.navigate(RESTORE_PASSWORD.route) },
                        onPasswordVisibilityButtonClicked = {
                            viewModel.onEvent(PasswordVisibilityButtonClicked)
                        },
                        onResetPasswordButtonClicked = {
                            viewModel.onEvent(
                                ResetPasswordButtonClicked
                            )
                        },
                        onLoginButtonClicked = { viewModel.onEvent(LoginButtonClicked) },
                        onGoogleLoginButtonClicked = { viewModel.onEvent(GoogleLoginButtonClicked) },
                        onFacebookLoginButtonClicked = {
                            viewModel.onEvent(
                                FacebookLoginButtonClicked
                            )
                        },
                        onSignUpTextClicked = { navController.navigate(SIGN_UP.route) }
                    )
                }

                composable(SIGN_UP.route) {
                    val viewModel = hiltViewModel<SignUpViewModel>()

                    NavigateToHome(
                        flow = viewModel.signedUp,
                        navController = navController
                    )

                    SignUpScreen(
                        email = viewModel.state.email,
                        password = viewModel.state.password,
                        repeatedPassword = viewModel.state.repeatedPassword,
                        loading = viewModel.state.loading,
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

                composable(RESTORE_PASSWORD.route) {
                    val viewModel = hiltViewModel<RestorePasswordViewModel>()
                    RestorePasswordScreen(
                        email = viewModel.state.email,
                        loading = viewModel.state.loading,
                        feedback = viewModel.state.feedback,
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
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun NavigateToHome(
    flow: StateFlow<Unit?>,
    navController: NavHostController,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    LaunchedEffect(key1 = Unit) {
        flow.collect {
            it?.run {
                navController.navigate(HOME.route) {
                    popUpTo(LOGIN.route) { inclusive = true }
                }
                delay(300)
                keyboardController?.hide()
            }
        }
    }
}