package com.twogether.app;

import com.twogether.app.feature.auth.AuthViewModel;
import com.twogether.app.feature.auth.PairingViewModel;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;

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
public final class MainActivity_MembersInjector implements MembersInjector<MainActivity> {
  private final Provider<AuthViewModel> authViewModelProvider;

  private final Provider<PairingViewModel> pairingViewModelProvider;

  private MainActivity_MembersInjector(Provider<AuthViewModel> authViewModelProvider,
      Provider<PairingViewModel> pairingViewModelProvider) {
    this.authViewModelProvider = authViewModelProvider;
    this.pairingViewModelProvider = pairingViewModelProvider;
  }

  @Override
  public void injectMembers(MainActivity instance) {
    injectAuthViewModel(instance, authViewModelProvider.get());
    injectPairingViewModel(instance, pairingViewModelProvider.get());
  }

  public static MembersInjector<MainActivity> create(Provider<AuthViewModel> authViewModelProvider,
      Provider<PairingViewModel> pairingViewModelProvider) {
    return new MainActivity_MembersInjector(authViewModelProvider, pairingViewModelProvider);
  }

  @InjectedFieldSignature("com.twogether.app.MainActivity.authViewModel")
  public static void injectAuthViewModel(MainActivity instance, AuthViewModel authViewModel) {
    instance.authViewModel = authViewModel;
  }

  @InjectedFieldSignature("com.twogether.app.MainActivity.pairingViewModel")
  public static void injectPairingViewModel(MainActivity instance,
      PairingViewModel pairingViewModel) {
    instance.pairingViewModel = pairingViewModel;
  }
}
