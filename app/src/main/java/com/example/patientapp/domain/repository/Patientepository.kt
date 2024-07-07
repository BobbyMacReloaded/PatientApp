package com.example.patientapp.domain.repository

import com.example.patientapp.domain.model.Patient
import kotlinx.coroutines.flow.Flow

interface PatientRepository {

    suspend fun addOrUpDatePatient(patient: Patient)

    suspend fun deletePatient(patient: Patient)

    suspend fun getPatientBytId(patientId:Int):Patient?

    fun getAllPatient(): Flow<List<Patient>>
}