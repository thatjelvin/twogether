package com.twogether.app.di;

import com.google.firebase.auth.FirebaseAuth;
import com.twogether.app.data.datasource.RitualDataSource;
import com.twogether.app.domain.repository.RitualRepository;
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
public final class RepositoryModule_ProvideRitualRepositoryFactory implements Factory<RitualRepository> {
  private final Provider<RitualDataSource> dataSourceProvider;

  private final Provider<FirebaseAuth> authProvider;

  private RepositoryModule_ProvideRitualRepositoryFactory(
      Provider<RitualDataSource> dataSourceProvider, Provider<FirebaseAuth> authProvider) {
    this.dataSourceProvider = dataSourceProvider;
    this.authProvider = authProvider;
  }

  @Override
  public RitualRepository get() {
    return provideRitualRepository(dataSourceProvider.get(), authProvider.get());
  }

  public static RepositoryModule_ProvideRitualRepositoryFactory create(
      Provider<RitualDataSource> dataSourceProvider, Provider<FirebaseAuth> authProvider) {
    return new RepositoryModule_ProvideRitualRepositoryFactory(dataSourceProvider, authProvider);
  }

  public static RitualRepository provideRitualRepository(RitualDataSource dataSource,
      FirebaseAuth auth) {
    return Preconditions.checkNotNullFromProvides(RepositoryModule.INSTANCE.provideRitualRepository(dataSource, auth));
  }
}
