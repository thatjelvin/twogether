package com.twogether.app.di;

import com.twogether.app.domain.repository.AuthRepository;
import com.twogether.app.domain.usecase.SignInWithGoogle;
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
public final class RepositoryModule_ProvideSignInWithGoogleFactory implements Factory<SignInWithGoogle> {
  private final Provider<AuthRepository> authRepositoryProvider;

  private RepositoryModule_ProvideSignInWithGoogleFactory(
      Provider<AuthRepository> authRepositoryProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
  }

  @Override
  public SignInWithGoogle get() {
    return provideSignInWithGoogle(authRepositoryProvider.get());
  }

  public static RepositoryModule_ProvideSignInWithGoogleFactory create(
      Provider<AuthRepository> authRepositoryProvider) {
    return new RepositoryModule_ProvideSignInWithGoogleFactory(authRepositoryProvider);
  }

  public static SignInWithGoogle provideSignInWithGoogle(AuthRepository authRepository) {
    return Preconditions.checkNotNullFromProvides(RepositoryModule.INSTANCE.provideSignInWithGoogle(authRepository));
  }
}
