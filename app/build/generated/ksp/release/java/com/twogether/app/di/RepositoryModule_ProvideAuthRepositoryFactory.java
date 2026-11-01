package com.twogether.app.di;

import com.google.firebase.auth.FirebaseAuth;
import com.twogether.app.domain.repository.AuthRepository;
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
public final class RepositoryModule_ProvideAuthRepositoryFactory implements Factory<AuthRepository> {
  private final Provider<FirebaseAuth> authProvider;

  private RepositoryModule_ProvideAuthRepositoryFactory(Provider<FirebaseAuth> authProvider) {
    this.authProvider = authProvider;
  }

  @Override
  public AuthRepository get() {
    return provideAuthRepository(authProvider.get());
  }

  public static RepositoryModule_ProvideAuthRepositoryFactory create(
      Provider<FirebaseAuth> authProvider) {
    return new RepositoryModule_ProvideAuthRepositoryFactory(authProvider);
  }

  public static AuthRepository provideAuthRepository(FirebaseAuth auth) {
    return Preconditions.checkNotNullFromProvides(RepositoryModule.INSTANCE.provideAuthRepository(auth));
  }
}
