package com.twogether.app.domain.usecase;

import com.twogether.app.domain.repository.AuthRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
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
public final class SignInWithGoogle_Factory implements Factory<SignInWithGoogle> {
  private final Provider<AuthRepository> authRepositoryProvider;

  private SignInWithGoogle_Factory(Provider<AuthRepository> authRepositoryProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
  }

  @Override
  public SignInWithGoogle get() {
    return newInstance(authRepositoryProvider.get());
  }

  public static SignInWithGoogle_Factory create(Provider<AuthRepository> authRepositoryProvider) {
    return new SignInWithGoogle_Factory(authRepositoryProvider);
  }

  public static SignInWithGoogle newInstance(AuthRepository authRepository) {
    return new SignInWithGoogle(authRepository);
  }
}
