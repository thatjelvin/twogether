package com.twogether.app.di;

import com.twogether.app.domain.repository.PairingRepository;
import com.twogether.app.domain.usecase.JoinCouple;
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
public final class RepositoryModule_ProvideJoinCoupleFactory implements Factory<JoinCouple> {
  private final Provider<PairingRepository> pairingRepositoryProvider;

  private RepositoryModule_ProvideJoinCoupleFactory(
      Provider<PairingRepository> pairingRepositoryProvider) {
    this.pairingRepositoryProvider = pairingRepositoryProvider;
  }

  @Override
  public JoinCouple get() {
    return provideJoinCouple(pairingRepositoryProvider.get());
  }

  public static RepositoryModule_ProvideJoinCoupleFactory create(
      Provider<PairingRepository> pairingRepositoryProvider) {
    return new RepositoryModule_ProvideJoinCoupleFactory(pairingRepositoryProvider);
  }

  public static JoinCouple provideJoinCouple(PairingRepository pairingRepository) {
    return Preconditions.checkNotNullFromProvides(RepositoryModule.INSTANCE.provideJoinCouple(pairingRepository));
  }
}
