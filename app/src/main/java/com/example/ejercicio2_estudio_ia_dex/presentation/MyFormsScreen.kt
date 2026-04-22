package com.example.ejercicio2_estudio_ia_dex.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Badge
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ejercicio2_estudio_ia_dex.R
import com.example.ejercicio2_estudio_ia_dex.data.Formulario

@Composable
fun MyFormsScreen(
    onNavigateToForm: () -> Unit
) {

    val viewModel = remember { MyFormsViewModel() }
    val uiState = viewModel.uiState.collectAsState()

    MyFormsScreenA(
        reports = uiState.value.myForms,
        getBadgeStyle = viewModel::getCategoryStyle,
        onNavigateToForm = onNavigateToForm
    )

    if (uiState.value.loading) {
        Loader()
    }

    if (uiState.value.loadingError) {
        ErrorModal(
            message = "No se han podido cargar los reportes. ¿Deseas reintentarlo?",
            onClick = viewModel::onRetryLoadingData,
            onClose = viewModel::onCloseErrorModal
        )
    }
}

@Composable
private fun MyFormsScreenA(
    reports: List<Formulario>,
    getBadgeStyle: (String) -> Long,
    onNavigateToForm: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .padding(20.dp, 10.dp)
            ) {
                Text("MIS REPORTES")
            }

            Box(
                modifier = Modifier.weight(1f)
            ) {
                ReportsContainer(
                    reports = reports,
                    getBadgeStyle = getBadgeStyle
                )
            }

            Row(
                modifier = Modifier
                    .padding(20.dp, 10.dp)
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF004C76)),
                    onClick = onNavigateToForm
                ) {
                    Text("Crear nuevo reporte")
                }
            }
        }
    }
}

@Composable
fun ReportsContainer(
    reports: List<Formulario>,
    getBadgeStyle: (String) -> Long
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp, 20.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            reports.forEach { item ->
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .padding(10.dp)
                    ) {
                        Row() {
                            Text(
                                text = item.title,
                                modifier = Modifier.weight(1f)
                            )
                            Badge(
                                containerColor = Color(getBadgeStyle(item.category))
                            ) {
                                Text(item.category.uppercase())
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(item.description)

                        Spacer(modifier = Modifier.height(8.dp))
                        HorizontalDivider(thickness = 2.dp)
                        Spacer(modifier = Modifier.height(8.dp))

                        Row() {
                            Text(
                                text = "Prioridad: ${item.priority}",
                                modifier = Modifier.weight(1f)
                            )

                            Badge(
                                containerColor = Color(0xFFD6EFFF)
                            ) {
                                Text("Correo: ${item.email}")
                            }
                        }
                    }
                }
            }
        }
    }
}