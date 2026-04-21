package com.example.ejercicio2_estudio_ia_dex.data

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from

class SupabaseDataSource {

    val supabase = createSupabaseClient(
        supabaseUrl = "https://agvkpeoctjleisqqpakq.supabase.co",
        supabaseKey = "sb_publishable_DN1wFA-iPW_JEnJgJyJsDw_UJmyOIif"
    ) {
        install(Postgrest)
    }

    suspend fun getMyForms() : List<Formulario> {
        return supabase
            .from("formulario")
            .select()
            .decodeList()
    }

    suspend fun uploadForm(form: FormularioRequest) {
        supabase
            .from("formulario")
            .insert(form)
    }
}