package com.twogether.app.di;

import com.google.firebase.firestore.FirebaseFirestore;
import com.twogether.app.data.datasource.RitualDataSource;
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
public final class RepositoryModule_ProvideRitualDataSourceFactory implements Factory<RitualDataSource> {
  private final Provider<FirebaseFirestore> firestoreProvider;

  private RepositoryModule_ProvideRitualDataSourceFactory(
      Provider<FirebaseFirestore> firestoreProvider) {
    this.firestoreProvider = firestoreProvider;
  }

  @Override
  public RitualDataSource get() {
    return provideRitualDataSource(firestoreProvider.get());
  }

  public static RepositoryModule_ProvideRitualDataSourceFactory create(
      Provider<FirebaseFirestore> firestoreProvider) {
    return new RepositoryModule_ProvideRitualDataSourceFactory(firestoreProvider);
  }

  public static RitualDataSource provideRitualDataSource(FirebaseFirestore firestore) {
    return Preconditions.checkNotNullFromProvides(RepositoryModule.INSTANCE.provideRitualDataSource(firestore));
  }
}
