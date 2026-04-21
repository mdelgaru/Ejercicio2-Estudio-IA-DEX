package com.example.ejercicio2_estudio_ia_dex.data

import kotlinx.serialization.Serializable

@Serializable
data class Formulario (
    val id: Int,
    val title: String,
    val description: String,
    val category: String,
    val priority: Int,
    val email: String
)

@Serializable
data class FormularioRequest (
    val title: String,
    val description: String,
    val category: String,
    val priority: Int,
    val email: String
)