package com.twogether.app.di;

import com.google.firebase.auth.FirebaseAuth;
import com.twogether.app.data.datasource.PairingDataSource;
import com.twogether.app.domain.repository.PairingRepository;
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
public final class RepositoryModule_ProvidePairingRepositoryFactory implements Factory<PairingRepository> {
  private final Provider<PairingDataSource> dataSourceProvider;

  private final Provider<FirebaseAuth> authProvider;

  private RepositoryModule_ProvidePairingRepositoryFactory(
      Provider<PairingDataSource> dataSourceProvider, Provider<FirebaseAuth> authProvider) {
    this.dataSourceProvider = dataSourceProvider;
    this.authProvider = authProvider;
  }

  @Override
  public PairingRepository get() {
    return providePairingRepository(dataSourceProvider.get(), authProvider.get());
  }

  public static RepositoryModule_ProvidePairingRepositoryFactory create(
      Provider<PairingDataSource> dataSourceProvider, Provider<FirebaseAuth> authProvider) {
    return new RepositoryModule_ProvidePairingRepositoryFactory(dataSourceProvider, authProvider);
  }

  public static PairingRepository providePairingRepository(PairingDataSource dataSource,
      FirebaseAuth auth) {
    return Preconditions.checkNotNullFromProvides(RepositoryModule.INSTANCE.providePairingRepository(dataSource, auth));
  }
}
