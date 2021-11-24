package ba.grbo.practical.framework.di

import android.content.Context
import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped

@InstallIn(ActivityRetainedComponent::class)
@Module
object GoogleProvider {
    @ActivityRetainedScoped
    @Provides
    fun provideGoogleSignInOptions(): GoogleSignInOptions =
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("428079389758-j7il0c4rb4vc26og4o94un3tqdirelag.apps.googleusercontent.com")
            .requestEmail()
            .build()

    @ActivityRetainedScoped
    @Provides
    fun provideGoogleSignInClient(
        gso: GoogleSignInOptions,
        @ApplicationContext context: Context,
    ): GoogleSignInClient = GoogleSignIn.getClient(context, gso)

    @ActivityRetainedScoped
    @Provides
    fun provideGetSignInAccountFromIntent(): (Intent?) -> Task<GoogleSignInAccount> =
        { intent -> GoogleSignIn.getSignedInAccountFromIntent(intent) }

    @ActivityRetainedScoped
    @Provides
    fun provideGoogleAuthProvider(): (String?, String?) -> AuthCredential =
        { idToken, accessToken -> GoogleAuthProvider.getCredential(idToken, accessToken) }


    @ActivityRetainedScoped
    @Provides
    fun provideSignOutOfGoogle(
        gsc: GoogleSignInClient
    ): () -> Unit = { gsc.signOut() } // Ignoring success, failed, canceled for simplicity
}