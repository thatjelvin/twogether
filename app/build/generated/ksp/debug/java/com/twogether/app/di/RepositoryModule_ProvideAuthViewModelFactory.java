package com.twogether.app.di;

import com.twogether.app.domain.usecase.SignInWithGoogle;
import com.twogether.app.feature.auth.AuthViewModel;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class RepositoryModule_ProvideAuthViewModelFactory implements Factory<AuthViewModel> {
  private final Provider<SignInWithGoogle> signInWithGoogleProvider;

  private RepositoryModule_ProvideAuthViewModelFactory(
      Provider<SignInWithGoogle> signInWithGoogleProvider) {
    this.signInWithGoogleProvider = signInWithGoogleProvider;
  }

  @Override
  public AuthViewModel get() {
    return provideAuthViewModel(signInWithGoogleProvider.get());
  }

  public static RepositoryModule_ProvideAuthViewModelFactory create(
      Provider<SignInWithGoogle> signInWithGoogleProvider) {
    return new RepositoryModule_ProvideAuthViewModelFactory(signInWithGoogleProvider);
  }

  public static AuthViewModel provideAuthViewModel(SignInWithGoogle signInWithGoogle) {
    return Preconditions.checkNotNullFromProvides(RepositoryModule.INSTANCE.provideAuthViewModel(signInWithGoogle));
  }
}
