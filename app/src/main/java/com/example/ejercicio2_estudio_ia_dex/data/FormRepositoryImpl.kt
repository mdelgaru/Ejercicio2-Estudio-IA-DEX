package com.example.ejercicio2_estudio_ia_dex.data

class FormRepositoryImpl : FormRepository {

    private val dataSource: SupabaseDataSource = SupabaseDataSource()

    override suspend fun getMyForms(): List<Formulario> {
        return dataSource.getMyForms()
    }

    override suspend fun uploadForm(form: FormularioRequest) {
        dataSource.uploadForm(form)
    }
}