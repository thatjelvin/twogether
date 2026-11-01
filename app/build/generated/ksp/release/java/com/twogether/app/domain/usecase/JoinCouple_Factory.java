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
public final class JoinCouple_Factory implements Factory<JoinCouple> {
  private final Provider<PairingRepository> pairingRepositoryProvider;

  private JoinCouple_Factory(Provider<PairingRepository> pairingRepositoryProvider) {
    this.pairingRepositoryProvider = pairingRepositoryProvider;
  }

  @Override
  public JoinCouple get() {
    return newInstance(pairingRepositoryProvider.get());
  }

  public static JoinCouple_Factory create(Provider<PairingRepository> pairingRepositoryProvider) {
    return new JoinCouple_Factory(pairingRepositoryProvider);
  }

  public static JoinCouple newInstance(PairingRepository pairingRepository) {
    return new JoinCouple(pairingRepository);
  }
}
