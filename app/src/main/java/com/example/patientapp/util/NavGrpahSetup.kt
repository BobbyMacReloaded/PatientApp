package com.example.patientapp.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.patientapp.presentation.patient_detail.PatientDetailScreen
import com.example.patientapp.presentation.patient_list.PatientListScreen
import com.example.patientapp.util.Constants.PATIENT_DETAILS_ARG_KEY


sealed class Screen(val route:String){
object PatientList:Screen("PatientListScreen")
object PatientDetails:Screen("patient_details_screen?$PATIENT_DETAILS_ARG_KEY={$PATIENT_DETAILS_ARG_KEY}"){
    fun passPatientId(patientId:Int?=null):String{
        return "patient_details_screen?$PATIENT_DETAILS_ARG_KEY=$patientId"
    }
}
}

@Composable
fun NavGraphSetup(
    navController: NavHostController
){

    NavHost(navController = navController, startDestination = Screen.PatientList.route ){
        composable(route = "PatientListScreen"){

            PatientListScreen(onItemClicked={
                navController.navigate(Screen.PatientDetails.passPatientId(it))
            },
                onFabClicked = {
                    navController.navigate(Screen.PatientDetails.route)
                },
               )
        }
        composable(route = Screen.PatientDetails.route,
            arguments = listOf(navArgument(name = PATIENT_DETAILS_ARG_KEY){
                type=NavType.IntType
                defaultValue = -1
            })
        ){

            PatientDetailScreen(
                onBackClicked={navController.navigateUp()},
                onSuccessfulSaveClicked = { navController.navigateUp() })
        }
    }
}