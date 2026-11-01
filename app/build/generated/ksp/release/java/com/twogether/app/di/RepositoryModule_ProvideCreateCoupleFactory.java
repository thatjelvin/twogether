package com.twogether.app.di;

import com.twogether.app.domain.repository.PairingRepository;
import com.twogether.app.domain.usecase.CreateCouple;
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
public final class RepositoryModule_ProvideCreateCoupleFactory implements Factory<CreateCouple> {
  private final Provider<PairingRepository> pairingRepositoryProvider;

  private RepositoryModule_ProvideCreateCoupleFactory(
      Provider<PairingRepository> pairingRepositoryProvider) {
    this.pairingRepositoryProvider = pairingRepositoryProvider;
  }

  @Override
  public CreateCouple get() {
    return provideCreateCouple(pairingRepositoryProvider.get());
  }

  public static RepositoryModule_ProvideCreateCoupleFactory create(
      Provider<PairingRepository> pairingRepositoryProvider) {
    return new RepositoryModule_ProvideCreateCoupleFactory(pairingRepositoryProvider);
  }

  public static CreateCouple provideCreateCouple(PairingRepository pairingRepository) {
    return Preconditions.checkNotNullFromProvides(RepositoryModule.INSTANCE.provideCreateCouple(pairingRepository));
  }
}
