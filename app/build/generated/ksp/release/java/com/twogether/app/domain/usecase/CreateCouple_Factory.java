package com.twogether.app.domain.usecase;

import com.twogether.app.domain.repository.PairingRepository;
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
public final class CreateCouple_Factory implements Factory<CreateCouple> {
  private final Provider<PairingRepository> pairingRepositoryProvider;

  private CreateCouple_Factory(Provider<PairingRepository> pairingRepositoryProvider) {
    this.pairingRepositoryProvider = pairingRepositoryProvider;
  }

  @Override
  public CreateCouple get() {
    return newInstance(pairingRepositoryProvider.get());
  }

  public static CreateCouple_Factory create(Provider<PairingRepository> pairingRepositoryProvider) {
    return new CreateCouple_Factory(pairingRepositoryProvider);
  }

  public static CreateCouple newInstance(PairingRepository pairingRepository) {
    return new CreateCouple(pairingRepository);
  }
}
