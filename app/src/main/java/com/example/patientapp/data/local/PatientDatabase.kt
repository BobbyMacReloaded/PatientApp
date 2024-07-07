package com.example.patientapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.patientapp.domain.model.Patient

@Database(
    entities = [Patient::class],
    version = 1
)
abstract class PatientDatabase :RoomDatabase(){
    abstract val patientDao: PatientDao
}