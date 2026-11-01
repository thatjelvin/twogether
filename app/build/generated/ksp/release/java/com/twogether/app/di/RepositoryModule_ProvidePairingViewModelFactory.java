package com.twogether.app.di;

import com.twogether.app.domain.usecase.CreateCouple;
import com.twogether.app.domain.usecase.JoinCouple;
import com.twogether.app.feature.auth.PairingViewModel;
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
public final class RepositoryModule_ProvidePairingViewModelFactory implements Factory<PairingViewModel> {
  private final Provider<CreateCouple> createCoupleProvider;

  private final Provider<JoinCouple> joinCoupleProvider;

  private RepositoryModule_ProvidePairingViewModelFactory(
      Provider<CreateCouple> createCoupleProvider, Provider<JoinCouple> joinCoupleProvider) {
    this.createCoupleProvider = createCoupleProvider;
    this.joinCoupleProvider = joinCoupleProvider;
  }

  @Override
  public PairingViewModel get() {
    return providePairingViewModel(createCoupleProvider.get(), joinCoupleProvider.get());
  }

  public static RepositoryModule_ProvidePairingViewModelFactory create(
      Provider<CreateCouple> createCoupleProvider, Provider<JoinCouple> joinCoupleProvider) {
    return new RepositoryModule_ProvidePairingViewModelFactory(createCoupleProvider, joinCoupleProvider);
  }

  public static PairingViewModel providePairingViewModel(CreateCouple createCouple,
      JoinCouple joinCouple) {
    return Preconditions.checkNotNullFromProvides(RepositoryModule.INSTANCE.providePairingViewModel(createCouple, joinCouple));
  }
}
