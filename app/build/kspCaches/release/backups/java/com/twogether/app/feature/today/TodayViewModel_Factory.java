package com.twogether.app.feature.today;

import com.twogether.app.domain.repository.AuthRepository;
import com.twogether.app.domain.repository.PairingRepository;
import com.twogether.app.domain.repository.RitualRepository;
import com.twogether.app.domain.usecase.MarkStepDone;
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
public final class TodayViewModel_Factory implements Factory<TodayViewModel> {
  private final Provider<AuthRepository> authRepositoryProvider;

  private final Provider<PairingRepository> pairingRepositoryProvider;

  private final Provider<RitualRepository> ritualRepositoryProvider;

  private final Provider<MarkStepDone> markStepDoneProvider;

  private TodayViewModel_Factory(Provider<AuthRepository> authRepositoryProvider,
      Provider<PairingRepository> pairingRepositoryProvider,
      Provider<RitualRepository> ritualRepositoryProvider,
      Provider<MarkStepDone> markStepDoneProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
    this.pairingRepositoryProvider = pairingRepositoryProvider;
    this.ritualRepositoryProvider = ritualRepositoryProvider;
    this.markStepDoneProvider = markStepDoneProvider;
  }

  @Override
  public TodayViewModel get() {
    return newInstance(authRepositoryProvider.get(), pairingRepositoryProvider.get(), ritualRepositoryProvider.get(), markStepDoneProvider.get());
  }

  public static TodayViewModel_Factory create(Provider<AuthRepository> authRepositoryProvider,
      Provider<PairingRepository> pairingRepositoryProvider,
      Provider<RitualRepository> ritualRepositoryProvider,
      Provider<MarkStepDone> markStepDoneProvider) {
    return new TodayViewModel_Factory(authRepositoryProvider, pairingRepositoryProvider, ritualRepositoryProvider, markStepDoneProvider);
  }

  public static TodayViewModel newInstance(AuthRepository authRepository,
      PairingRepository pairingRepository, RitualRepository ritualRepository,
      MarkStepDone markStepDone) {
    return new TodayViewModel(authRepository, pairingRepository, ritualRepository, markStepDone);
  }
}
