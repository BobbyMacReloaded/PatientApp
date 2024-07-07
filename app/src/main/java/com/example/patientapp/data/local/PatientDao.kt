package com.example.patientapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.patientapp.domain.model.Patient
import com.example.patientapp.util.Constants.PATIENT_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface PatientDao {
    @Upsert
    suspend fun addOrUpdatePatient(patient: Patient)

    @Delete
    suspend fun deletePatient(patient: Patient)

    @Query("SELECT * FROM $PATIENT_TABLE WHERE patientId = :patientId ")
    suspend fun getPatientBytId(patientId:Int):Patient?

    @Query("SELECT * FROM $PATIENT_TABLE")
    fun getAllPatient():Flow<List<Patient>>
}