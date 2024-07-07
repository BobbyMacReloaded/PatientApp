package com.example.patientapp.presentation.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun DeleteDialog(
    title: String,
    message: String,
    onConfirmButtonClicked: () -> Unit,
    onDialogDismiss: () -> Unit
) {
    AlertDialog(
        title = {
            Text(text = title, style = MaterialTheme.typography.titleMedium)
        },
        text = {
            Text(text = message, style = MaterialTheme.typography.bodySmall)
        },
        onDismissRequest = onDialogDismiss,
        confirmButton = {
            TextButton(onClick = {
                onConfirmButtonClicked()
                onDialogDismiss()
            }) {
                Text(text = "Yes")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDialogDismiss() }) {
                Text(text = "No")
            }
        }
    )
}
