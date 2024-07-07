package com.example.patientapp.presentation.patient_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.patientapp.domain.model.Patient
import com.example.patientapp.domain.repository.PatientRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PatientListViewModel @Inject constructor(
    private val repository: PatientRepository
):ViewModel() {
private val _patientList = MutableStateFlow<List<Patient>>(emptyList())
    val patientList = _patientList.asStateFlow()
    init {
        viewModelScope.launch {
            repository.getAllPatient().collect(){
                _patientList.value = it
            }
        }
    }
    fun deletePatient(patient: Patient){
        viewModelScope.launch {
            repository.deletePatient(patient)
        }
    }
}