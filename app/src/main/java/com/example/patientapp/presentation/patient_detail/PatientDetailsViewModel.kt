package com.example.patientapp.presentation.patient_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.patientapp.domain.model.Patient
import com.example.patientapp.domain.repository.PatientRepository
import com.example.patientapp.util.Constants.PATIENT_DETAILS_ARG_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PatientDetailsViewModel @Inject constructor(
    private val repository: PatientRepository,
    private val savedStateHandle: SavedStateHandle
):ViewModel() {

  var state by mutableStateOf(PatientDetailsUIState())
    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()
    private var currentPatientId:Int? = null
    init {
        fetchPatientsDetails()
    }
    fun onEvent(event: PatientsDetailsEvents){
        when(event){
            is PatientsDetailsEvents.EnteredAge -> {
                state = state.copy(age = event.age)
            }
            is PatientsDetailsEvents.EnteredAssignedDoctor ->{
                state = state.copy(doctorAssigned = event.doctorAssigned)
            }
            is PatientsDetailsEvents.EnteredName -> {
                state = state.copy(name = event.name)
            }
            is PatientsDetailsEvents.EnteredPrescription -> {
                state = state.copy(prescription = event.prescription)
            }
            PatientsDetailsEvents.SaveButton -> {
              viewModelScope.launch {
                  try {
                      savePatient()
                      _eventFlow.emit(UIEvent.SaveNote)


                  }catch (e:Exception){
                      _eventFlow.emit(UIEvent.ShowToast(
                          message = e.message ?: "Couldn't save patient's details"
                      ))
                  }
              }
            }
            PatientsDetailsEvents.SelectedFemale -> {
                state =state.copy(gender = 2)
            }
            PatientsDetailsEvents.SelectedMale -> {
                state = state.copy(gender = 1)
            }
        }
    }
    private fun savePatient(){
        val age= state.age.toIntOrNull()
        when{
            state.name.isEmpty()-> throw TextFieldException("Please Enter Name!")
            state.age.isEmpty()-> throw TextFieldException("Please Enter Age!")
            state.gender == 0-> throw TextFieldException("Please select gender!")
            state.doctorAssigned.isEmpty()-> throw TextFieldException("Please Enter the doctor assigned to the patient !")
            state.prescription.isEmpty()-> throw TextFieldException("Please Enter the prescription!")
            age==null|| age < 0 || age > 100 -> throw TextFieldException("Please enter a valid age")
        }
        val trimmedName= state.name.trim()
        val trimmedDoctorAssigned = state.doctorAssigned.trim()

        viewModelScope.launch {
            repository.addOrUpDatePatient(
                patient = Patient(
                    name = trimmedName,
                    age = state.age,
                    gender = state.gender,
                    doctorAssigned = trimmedDoctorAssigned,
                    prescription = state.prescription,
                    patientId = currentPatientId
                )
            )
        }
    }
    private fun fetchPatientsDetails(){
        savedStateHandle.get<Int>(key = PATIENT_DETAILS_ARG_KEY).let {patientId->
            if (patientId !=-1){
                viewModelScope.launch {
                    if (patientId != null) {
                        repository.getPatientBytId(patientId)?.apply {
                            state=state.copy(
                                name = name,
                                gender = gender,

                                prescription = prescription,
                                doctorAssigned = doctorAssigned,

                            )
                            currentPatientId=patientId
                        }
                    }
                }
            }
        }
    }
    sealed class UIEvent{
        data class ShowToast(val message: String?):UIEvent()
        object SaveNote:UIEvent()
    }

}
class TextFieldException(message:String?):Exception(message)
















