package com.twogether.app.data.repository;

import com.google.firebase.auth.FirebaseAuth;
import com.twogether.app.data.datasource.PairingDataSource;
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
public final class PairingRepositoryImpl_Factory implements Factory<PairingRepositoryImpl> {
  private final Provider<PairingDataSource> dataSourceProvider;

  private final Provider<FirebaseAuth> authProvider;

  private PairingRepositoryImpl_Factory(Provider<PairingDataSource> dataSourceProvider,
      Provider<FirebaseAuth> authProvider) {
    this.dataSourceProvider = dataSourceProvider;
    this.authProvider = authProvider;
  }

  @Override
  public PairingRepositoryImpl get() {
    return newInstance(dataSourceProvider.get(), authProvider.get());
  }

  public static PairingRepositoryImpl_Factory create(Provider<PairingDataSource> dataSourceProvider,
      Provider<FirebaseAuth> authProvider) {
    return new PairingRepositoryImpl_Factory(dataSourceProvider, authProvider);
  }

  public static PairingRepositoryImpl newInstance(PairingDataSource dataSource, FirebaseAuth auth) {
    return new PairingRepositoryImpl(dataSource, auth);
  }
}
