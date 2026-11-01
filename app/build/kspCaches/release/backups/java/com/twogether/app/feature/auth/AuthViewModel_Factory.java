package com.twogether.app.feature.auth;

import com.twogether.app.domain.usecase.SignInWithGoogle;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class AuthViewModel_Factory implements Factory<AuthViewModel> {
  private final Provider<SignInWithGoogle> signInWithGoogleProvider;

  private AuthViewModel_Factory(Provider<SignInWithGoogle> signInWithGoogleProvider) {
    this.signInWithGoogleProvider = signInWithGoogleProvider;
  }

  @Override
  public AuthViewModel get() {
    return newInstance(signInWithGoogleProvider.get());
  }

  public static AuthViewModel_Factory create(Provider<SignInWithGoogle> signInWithGoogleProvider) {
    return new AuthViewModel_Factory(signInWithGoogleProvider);
  }

  public static AuthViewModel newInstance(SignInWithGoogle signInWithGoogle) {
    return new AuthViewModel(signInWithGoogle);
  }
}
