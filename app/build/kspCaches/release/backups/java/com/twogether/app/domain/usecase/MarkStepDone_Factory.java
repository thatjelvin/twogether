package com.twogether.app.domain.usecase;

import com.twogether.app.domain.repository.RitualRepository;
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
public final class MarkStepDone_Factory implements Factory<MarkStepDone> {
  private final Provider<RitualRepository> ritualRepositoryProvider;

  private MarkStepDone_Factory(Provider<RitualRepository> ritualRepositoryProvider) {
    this.ritualRepositoryProvider = ritualRepositoryProvider;
  }

  @Override
  public MarkStepDone get() {
    return newInstance(ritualRepositoryProvider.get());
  }

  public static MarkStepDone_Factory create(Provider<RitualRepository> ritualRepositoryProvider) {
    return new MarkStepDone_Factory(ritualRepositoryProvider);
  }

  public static MarkStepDone newInstance(RitualRepository ritualRepository) {
    return new MarkStepDone(ritualRepository);
  }
}
