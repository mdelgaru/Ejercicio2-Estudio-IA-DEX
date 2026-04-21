package com.example.ejercicio2_estudio_ia_dex.domain

import com.example.ejercicio2_estudio_ia_dex.data.FormRepositoryImpl
import com.example.ejercicio2_estudio_ia_dex.data.Formulario
import com.example.ejercicio2_estudio_ia_dex.data.FormularioRequest

class FormUseCase {

    private val repositoryImpl: FormRepositoryImpl = FormRepositoryImpl()

    suspend fun getMyReports(): List<Formulario> {
        return repositoryImpl.getMyForms();
    }

    suspend fun uploadForm(form: FormularioRequest) {
        repositoryImpl.uploadForm(form)
    }
}