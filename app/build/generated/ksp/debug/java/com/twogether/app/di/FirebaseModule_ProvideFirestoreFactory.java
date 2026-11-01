package com.twogether.app.di;

import android.content.Context;
import com.google.firebase.firestore.FirebaseFirestore;
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
public final class FirebaseModule_ProvideFirestoreFactory implements Factory<FirebaseFirestore> {
  private final Provider<Context> contextProvider;

  private FirebaseModule_ProvideFirestoreFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public FirebaseFirestore get() {
    return provideFirestore(contextProvider.get());
  }

  public static FirebaseModule_ProvideFirestoreFactory create(Provider<Context> contextProvider) {
    return new FirebaseModule_ProvideFirestoreFactory(contextProvider);
  }

  public static FirebaseFirestore provideFirestore(Context context) {
    return Preconditions.checkNotNullFromProvides(FirebaseModule.INSTANCE.provideFirestore(context));
  }
}
