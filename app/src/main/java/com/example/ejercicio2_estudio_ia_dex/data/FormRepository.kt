package com.example.ejercicio2_estudio_ia_dex.data

interface FormRepository {

    suspend fun getMyForms(): List<Formulario>

    suspend fun uploadForm(form: FormularioRequest)
}