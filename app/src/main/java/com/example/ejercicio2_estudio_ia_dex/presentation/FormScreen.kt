package com.example.ejercicio2_estudio_ia_dex.presentation

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ejercicio2_estudio_ia_dex.R

@Composable
fun FormScreen(
    onGoBack: () -> Unit
) {

    val viewModel = remember { FormViewModel() }
    val uiState by viewModel.uiState.collectAsState()
    val toast = Toast.makeText(
        LocalContext.current,
        "Reporte guardado correctamente",
        Toast.LENGTH_LONG)

    FormScreen(
        title = uiState.title,
        description = uiState.description,
        priority = uiState.priority,
        email = uiState.email,
        categories = uiState.categories,
        onTitleChange = viewModel::onTitleChange,
        onDescriptionChange = viewModel::onDescriptionChange,
        onPriorityChange = viewModel::onPriorityChange,
        onEmailChange = viewModel::onEmailChange,
        selectedCategory = uiState.selectedCategory,
        onSelectCategory = viewModel::onCategoryChange,
        onSaveChanges = viewModel::onButtonClicked,
        errors = uiState.errors,
        buttonEnabled = uiState.buttonEnabled,
        loading = uiState.loading,
        onGoBack = onGoBack
    )

    if (uiState.dataSent) {
        toast.show()
    }

    if (uiState.loadError) {
        ErrorModal(
            message = "No ha podido guardarse el formulario. ¿Deseas reintentarlo?",
            onClick = viewModel::onRetrySendData,
            onClose = viewModel::onCloseErrorModal
        )
    }
}

@Composable
fun FormScreen(
    title: String,
    description: String,
    priority: String,
    email: String,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onPriorityChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    categories: List<String>,
    selectedCategory: String,
    onSelectCategory: (String) -> Unit,
    onSaveChanges: () -> Unit,
    errors: List<Boolean>,
    buttonEnabled: Boolean,
    loading: Boolean,
    onGoBack: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFE6E8F0))
                    .drawBehind {
                        val strokeWidth = 2.dp.toPx()
                        drawLine(
                            color = Color(0xFF004C76),
                            start = Offset(0f, size.height),
                            end = Offset(size.width, size.height),
                            strokeWidth = strokeWidth
                        )
                    }
                    .padding(10.dp, 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onGoBack
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_back),
                        contentDescription = ""
                    )
                }

                Spacer(modifier = Modifier.width(15.dp))

                Text(
                    text = "Formulario",
                    fontSize = 26.sp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = title,
                    onValueChange = onTitleChange,
                    label = { Text(text = "Título") },
                    supportingText = {
                        if (errors[0]) {
                            Text(
                                text = "El título debe tener entre 5 y 60 caracteres",
                                color = Color.Red
                            )
                        }
                    },
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = description,
                    onValueChange = onDescriptionChange,
                    label = { Text(text = "Descripción") },
                    minLines = 5,
                    supportingText = {
                        if (errors[1]) {
                            Text(
                                text = "La descripción debe tener entre 20 y 500 caracteres",
                                color = Color.Red
                            )
                        }
                    }
                )

                Spacer(modifier = Modifier.height(10.dp))

                DropDown(
                    modifier = Modifier
                        .fillMaxWidth(),
                    options = categories,
                    selectedOption = selectedCategory,
                    onSelectOption = onSelectCategory,
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = priority,
                    onValueChange = onPriorityChange,
                    label = { Text(text = "Prioridad") },
                    supportingText = {
                        if (errors[2]) {
                            Text(
                                text = "La prioridad sólo puede ser de 1 a 5",
                                color = Color.Red
                            )
                        }
                    },
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = email,
                    onValueChange = onEmailChange,
                    label = { Text(text = "Email") },
                    supportingText = {
                        if (errors[3]) {
                            Text(
                                text = "El formato de email no es correcto",
                                color = Color.Red
                            )
                        }
                    },
                    singleLine = true
                )


                Spacer(modifier = Modifier.weight(1f))

                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF004C76)),
                    onClick = onSaveChanges,
                    enabled = buttonEnabled
                ) {
                    Text("Guardar cambios")
                }
            }
        }
    }

    if (loading) {
        Loader()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDown(
    modifier: Modifier = Modifier,
    options: List<String>,
    selectedOption: String,
    onSelectOption: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = selectedOption,
            onValueChange = onSelectOption,
            label = { Text("Selecciona una categoría") },
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = modifier.menuAnchor()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { item ->
                DropdownMenuItem(
                    text = { Text(item) },
                    onClick = {
                        onSelectOption(item)
                        expanded = false
                    }
                )
            }
        }
    }
}