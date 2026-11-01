package com.twogether.app.data.repository;

import com.google.firebase.auth.FirebaseAuth;
import com.twogether.app.data.datasource.RitualDataSource;
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
public final class RitualRepositoryImpl_Factory implements Factory<RitualRepositoryImpl> {
  private final Provider<RitualDataSource> dataSourceProvider;

  private final Provider<FirebaseAuth> authProvider;

  private RitualRepositoryImpl_Factory(Provider<RitualDataSource> dataSourceProvider,
      Provider<FirebaseAuth> authProvider) {
    this.dataSourceProvider = dataSourceProvider;
    this.authProvider = authProvider;
  }

  @Override
  public RitualRepositoryImpl get() {
    return newInstance(dataSourceProvider.get(), authProvider.get());
  }

  public static RitualRepositoryImpl_Factory create(Provider<RitualDataSource> dataSourceProvider,
      Provider<FirebaseAuth> authProvider) {
    return new RitualRepositoryImpl_Factory(dataSourceProvider, authProvider);
  }

  public static RitualRepositoryImpl newInstance(RitualDataSource dataSource, FirebaseAuth auth) {
    return new RitualRepositoryImpl(dataSource, auth);
  }
}
