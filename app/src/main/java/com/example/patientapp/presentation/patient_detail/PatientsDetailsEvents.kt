package com.example.patientapp.presentation.patient_detail

sealed class PatientsDetailsEvents (

){
    data class EnteredName(val name:String):PatientsDetailsEvents()
    data class EnteredAge(val age:String):PatientsDetailsEvents()
    data class EnteredAssignedDoctor(val doctorAssigned:String):PatientsDetailsEvents()
    data class EnteredPrescription(val prescription:String):PatientsDetailsEvents()
    object SelectedMale:PatientsDetailsEvents()
    object SelectedFemale:PatientsDetailsEvents()
    object SaveButton:PatientsDetailsEvents()
}