package com.twogether.app.di;

import android.content.Context;
import com.google.firebase.auth.FirebaseAuth;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class FirebaseModule_ProvideAuthFactory implements Factory<FirebaseAuth> {
  private final Provider<Context> contextProvider;

  private FirebaseModule_ProvideAuthFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public FirebaseAuth get() {
    return provideAuth(contextProvider.get());
  }

  public static FirebaseModule_ProvideAuthFactory create(Provider<Context> contextProvider) {
    return new FirebaseModule_ProvideAuthFactory(contextProvider);
  }

  public static FirebaseAuth provideAuth(Context context) {
    return Preconditions.checkNotNullFromProvides(FirebaseModule.INSTANCE.provideAuth(context));
  }
}
