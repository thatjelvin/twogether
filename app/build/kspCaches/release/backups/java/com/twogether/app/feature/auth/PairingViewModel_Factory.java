package com.twogether.app.feature.auth;

import com.twogether.app.domain.usecase.CreateCouple;
import com.twogether.app.domain.usecase.JoinCouple;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class PairingViewModel_Factory implements Factory<PairingViewModel> {
  private final Provider<CreateCouple> createCoupleProvider;

  private final Provider<JoinCouple> joinCoupleProvider;

  private PairingViewModel_Factory(Provider<CreateCouple> createCoupleProvider,
      Provider<JoinCouple> joinCoupleProvider) {
    this.createCoupleProvider = createCoupleProvider;
    this.joinCoupleProvider = joinCoupleProvider;
  }

  @Override
  public PairingViewModel get() {
    return newInstance(createCoupleProvider.get(), joinCoupleProvider.get());
  }

  public static PairingViewModel_Factory create(Provider<CreateCouple> createCoupleProvider,
      Provider<JoinCouple> joinCoupleProvider) {
    return new PairingViewModel_Factory(createCoupleProvider, joinCoupleProvider);
  }

  public static PairingViewModel newInstance(CreateCouple createCouple, JoinCouple joinCouple) {
    return new PairingViewModel(createCouple, joinCouple);
  }
}
