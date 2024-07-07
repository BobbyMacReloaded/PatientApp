package com.example.patientapp.data.repository

import com.example.patientapp.data.local.PatientDao
import com.example.patientapp.domain.model.Patient
import com.example.patientapp.domain.repository.PatientRepository
import kotlinx.coroutines.flow.Flow

class PatientRepositoryImpl(
    private val dao: PatientDao
):PatientRepository {
    override suspend fun addOrUpDatePatient(patient: Patient) {
        dao.addOrUpdatePatient(patient)
    }

    override suspend fun deletePatient(patient: Patient) {
        dao.deletePatient(patient)
    }

    override suspend fun getPatientBytId(patientId: Int): Patient? {
     return  dao.getPatientBytId(patientId)
    }

    override fun getAllPatient(): Flow<List<Patient>> {
      return dao.getAllPatient()
    }


}