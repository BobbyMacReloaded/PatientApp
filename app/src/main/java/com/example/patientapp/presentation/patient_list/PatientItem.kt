package com.example.patientapp.presentation.patient_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.patientapp.domain.model.Patient
import com.example.patientapp.presentation.components.DeleteDialog

@Composable
fun PatientItem(
patient: Patient,
onItemCLick:()->Unit,
onDeleteConfirm:()->Unit
) {
    var showDialog by remember {
        mutableStateOf(false)
    }
    if (showDialog) {
        DeleteDialog(
            title = "Delete",
            message = "Are you sure you want to delete patient \"${patient.name}\" from the patient list?",
            onConfirmButtonClicked = { onDeleteConfirm()
                                     showDialog= false},
            onDialogDismiss = { showDialog = false }
        )
    }

    Card(
        modifier = Modifier.clickable { onItemCLick() },

    ) {
        Row (
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Column (
                modifier = Modifier.weight(1f)
            ){
              Text(
                  text = patient.name,
                  style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
             overflow = TextOverflow.Ellipsis,
                  maxLines = 1)
                Text(
                    text = " Assigned to ${patient.doctorAssigned}",
                    style = MaterialTheme.typography.headlineSmall,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1)
            }
            IconButton(onClick = {onDeleteConfirm()
                showDialog = true }) {
               Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete")
            }
        }






















    }
}