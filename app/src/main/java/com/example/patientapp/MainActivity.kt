package com.example.patientapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.patientapp.presentation.patient_detail.PatientDetailScreen
import com.example.patientapp.presentation.patient_detail.PatientDetailsViewModel
import com.example.patientapp.presentation.patient_list.PatientListScreen
import com.example.patientapp.presentation.theme.PatientAppTheme
import com.example.patientapp.util.NavGraphSetup
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PatientAppTheme {
                val navController = rememberNavController()
               
             NavGraphSetup(navController = navController)
            }
        }
    }
}
