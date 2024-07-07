package com.example.patientapp.di

import android.app.Application
import androidx.room.Room
import com.example.patientapp.data.local.PatientDatabase
import com.example.patientapp.data.repository.PatientRepositoryImpl
import com.example.patientapp.domain.repository.PatientRepository
import com.example.patientapp.util.Constants.PATIENT_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providePatientDatabase(
        app:Application
    ):PatientDatabase{
        return Room.databaseBuilder(
           app,
            PatientDatabase::class.java,
            name = PATIENT_DATABASE
        ).build()
    }
    @Provides
    @Singleton
    fun provideRepository(
        db:PatientDatabase
    ):PatientRepository{
        return PatientRepositoryImpl(dao = db.patientDao)
    }
}



