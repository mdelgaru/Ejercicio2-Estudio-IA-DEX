package com.example.ejercicio2_estudio_ia_dex.presentation

import com.example.ejercicio2_estudio_ia_dex.data.Formulario

data class FormUiState(
    val title: String,
    val description: String,
    val priority: String,
    val email: String,
    val loading: Boolean,
    val buttonEnabled: Boolean,
    val errors: List<Boolean>,
    val categories: List<String>,
    val selectedCategory: String,
    val dataSent: Boolean,
    val loadError: Boolean
)