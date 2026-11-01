package com.twogether.app.di

import android.content.Context
import android.content.pm.ApplicationInfo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val EMULATOR_HOST = "10.0.2.2"
private const val AUTH_EMULATOR_PORT = 9099
private const val FIRESTORE_EMULATOR_PORT = 8080

private fun isDebuggable(context: Context): Boolean =
    context.applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun provideAuth(@ApplicationContext context: Context): FirebaseAuth {
        val auth = FirebaseAuth.getInstance()
        if (isDebuggable(context)) {
            auth.useEmulator(EMULATOR_HOST, AUTH_EMULATOR_PORT)
        }
        return auth
    }

    @Provides
    @Singleton
    fun provideFirestore(@ApplicationContext context: Context): FirebaseFirestore {
        val firestore = FirebaseFirestore.getInstance()
        if (isDebuggable(context)) {
            firestore.useEmulator(EMULATOR_HOST, FIRESTORE_EMULATOR_PORT)
        }
        return firestore
    }
}
