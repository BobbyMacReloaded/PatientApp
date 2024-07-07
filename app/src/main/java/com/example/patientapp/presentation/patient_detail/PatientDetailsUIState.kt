package com.example.patientapp.presentation.patient_detail

data class PatientDetailsUIState(
    val name:String ="",
    val prescription:String="",
    val doctorAssigned:String="",
    val age:String="",
    val gender:Int=0,
)
