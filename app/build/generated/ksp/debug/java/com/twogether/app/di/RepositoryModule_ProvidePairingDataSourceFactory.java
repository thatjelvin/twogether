package com.twogether.app.di;

import com.google.firebase.firestore.FirebaseFirestore;
import com.twogether.app.data.datasource.PairingDataSource;
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
public final class RepositoryModule_ProvidePairingDataSourceFactory implements Factory<PairingDataSource> {
  private final Provider<FirebaseFirestore> firestoreProvider;

  private RepositoryModule_ProvidePairingDataSourceFactory(
      Provider<FirebaseFirestore> firestoreProvider) {
    this.firestoreProvider = firestoreProvider;
  }

  @Override
  public PairingDataSource get() {
    return providePairingDataSource(firestoreProvider.get());
  }

  public static RepositoryModule_ProvidePairingDataSourceFactory create(
      Provider<FirebaseFirestore> firestoreProvider) {
    return new RepositoryModule_ProvidePairingDataSourceFactory(firestoreProvider);
  }

  public static PairingDataSource providePairingDataSource(FirebaseFirestore firestore) {
    return Preconditions.checkNotNullFromProvides(RepositoryModule.INSTANCE.providePairingDataSource(firestore));
  }
}
